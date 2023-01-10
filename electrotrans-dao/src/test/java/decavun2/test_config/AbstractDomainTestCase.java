package decavun2.test_config;

import static java.lang.String.format;
import static org.apache.commons.lang.StringUtils.isEmpty;
import static org.apache.logging.log4j.LogManager.getLogger;
import static ua.com.fielden.platform.utils.DbUtils.batchExecSql;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;
import org.junit.runner.RunWith;

import ua.com.fielden.platform.test_data.EnsureData;
import ua.com.fielden.platform.test_data.ITestCaseWithEnsureData;
import decavun2.config.ApplicationDomain;
import decavun2.data.IDomainData;
import decavun2.personnel.Person;
import decavun2.personnel.PersonCo;
import ua.com.fielden.platform.dao.annotations.SessionRequired;
import ua.com.fielden.platform.entity.AbstractEntity;
import ua.com.fielden.platform.keygen.KeyNumber;
import ua.com.fielden.platform.security.user.IUser;
import ua.com.fielden.platform.security.user.IUserProvider;
import ua.com.fielden.platform.security.user.User;
import ua.com.fielden.platform.test.AbstractDomainDrivenTestCase;
import ua.com.fielden.platform.test.DbCreator;
import ua.com.fielden.platform.test.exceptions.DomainDriventTestException;
import ua.com.fielden.platform.test.ioc.UniversalConstantsForTesting;
import ua.com.fielden.platform.utils.IUniversalConstants;

/**
 * This class should be used as the base type for all domain-driven test cases.
 * Its main purpose is to be a common layer for providing functionality common to most if not all test cases, and to be easily extensible if application changes are needed.
 *
 * @author Generated
 */
@RunWith(H2OrPostgresqlContextSelector.class)
public abstract class AbstractDomainTestCase extends AbstractDomainDrivenTestCase implements IDomainData, ITestCaseWithEnsureData {

    public final DateTime prePopulatedNow = dateTime("2022-01-01 08:00:00");

    public static final String TEST_PERSON_KEY = User.system_users.UNIT_TEST_USER.name();

    protected final Logger logger = getLogger(AbstractDomainTestCase.class);

    private final ApplicationDomain applicationDomainProvider = new ApplicationDomain();

    private static boolean PRE_POPULATED = false;

    // External clean up routine gets set in EnsureDataInterceptor to perform its cleaning when setDbCreator completes.
    private Runnable externalCleanupRoutine;

    private final boolean loadDataScriptFromFile;

    public AbstractDomainTestCase() {
        if (isEmpty(System.getProperty("loadDataScriptFromFile"))) {
            loadDataScriptFromFile = false;
        } else {
            loadDataScriptFromFile = Boolean.parseBoolean(System.getProperty("loadDataScriptFromFile"));
        }
    }

    public DateTime now() {
        return this.getInstance(IUniversalConstants.class).now();
    }

    @Override
    protected final List<Class<? extends AbstractEntity<?>>> domainEntityTypes() {
        return applicationDomainProvider.entityTypes();
    }

    @Override
    public final AbstractDomainDrivenTestCase setDbCreator(final DbCreator dbCreator) {
        super.setDbCreator(dbCreator);

        // Pre-population should only occur if it was not performed already (static info)
        // and if the current test case does not skip data caching.
        if (loadDataScriptFromFile || PRE_POPULATED || skipCaching()) {
            logger.info(format("Pre-population and scripting is not performed for [%s] due to [load from file: [%s], already pre-populated: [%s], skipping: [%s]].", getClass().getSimpleName(), loadDataScriptFromFile, PRE_POPULATED, skipCaching()));
            return this;
        }

        logger.info(format("Pre-populating, scripting and caching initiated in [%s].", getClass().getSimpleName()));

        // Let's use non-strict mode for scripting.
        AbstractEntity.useNonStrictModelVerification();

        // Need the basic data and initialisation as in populateDomain().
        basicDataAndInitialisation(prePopulatedNow);

        // Now we can start populating and caching scripts for various populate* methods.
        // TODO Provide all other necessary populate* methods, as they become available.
        //      Only the "minimum" populate???() methods need to be called. That is the leaves the will unrevel the dependency upwards automatically.
        // populate???();

        // Reset model verification mode to strict after scripting.
        AbstractEntity.useStrictModelVerification();

        logger.info(format("Completed pre-population, scripting and caching that was initiated in [%s]. Cleaning up...", getClass().getSimpleName()));
        // Once data is populated it is critical to clean the current test database as it would interfere with the test case.
        try {
            if (externalCleanupRoutine != null) {
                externalCleanupRoutine.run();
            }
            final Connection conn = dbCreator.conn;
            final List<String> script = dbCreator.genTruncStmt(dbCreator.entityMetadatas, conn);
            batchExecSql(script, conn, 100);
            conn.commit();
        } catch (final SQLException ex) {
            final String msg = format("Failed to perform cleaning after pre-population in [%s].", getClass().getSimpleName());
            logger.fatal(msg, ex);
            throw new DomainDriventTestException(msg, ex);
        }

        // Mark the fact of successful pre-population.
        PRE_POPULATED = true;
        return this;
    }

    @Override
    public void setCleanUpAfterPrepopulation(final Runnable clean) {
        externalCleanupRoutine = clean;
    }

    private void basicDataAndInitialisation(final DateTime now) {
        final IUniversalConstants consts = getInstance(IUniversalConstants.class);
        // Initialisation runs from either tests or PopulateDb ... in case of tests we need to initialise the modelling time.
        if (consts instanceof UniversalConstantsForTesting) {
            final UniversalConstantsForTesting constants = (UniversalConstantsForTesting) getInstance(IUniversalConstants.class);
            constants.setNow(now);
            // Set an initial time supplier so time will tick to avoid auditing undergoing upgrade.
            constants.setTimeSupplier(constants.mkMillisTicker(1000));
        }

        startupData();

        // Set User.system_users.UNIT_TEST_USER as the current user.
        final IUser coUser = co$(User.class);
        final User su = coUser.findUser(User.system_users.UNIT_TEST_USER.name());
        final IUserProvider up = getInstance(IUserProvider.class);
        up.setUser(su);
    }

    @EnsureData({})
    @SessionRequired(allowNestedScope = false)
    public void startupData() {
        // check self
        if (co(User.class).entityWithKeyExists(User.system_users.UNIT_TEST_USER)) {
            return;
        }

        setupUser(User.system_users.UNIT_TEST_USER, "tg.test");
        setupPerson(User.system_users.UNIT_TEST_USER, "tg.test");

        final String virtualKey = "VIRTUAL_FOR_TESTING";
        if (!co(KeyNumber.class).entityWithKeyExists(virtualKey)) {
            save(new_(KeyNumber.class, virtualKey).setValue("1")); // used as a workaround for selecting values with no table present
        }
    }

    /**
     * Initialises a test user.  Needs to be invoked in descendant classes.
     */
    @Override
    protected void populateDomain() {
        resetIdGenerator();
        basicDataAndInitialisation(prePopulatedNow);
    }

    @Override
    public Person setupPerson(final User.system_users defaultUser, final String emailDomain) {
        if (!useSavedDataPopulationScript()) {
            return IDomainData.super.setupPerson(defaultUser, emailDomain);
        } else {
            return co$(Person.class).findByKeyAndFetch(PersonCo.FETCH_PROVIDER.fetchModel(), defaultUser.name());
        }
    }

    /**
     * Controls caching of data that is produced by methods with {@link EnsureData} annotation.
     *
     * @return
     */
    @Override
    public boolean skipCaching() {
        return saveDataPopulationScriptToFile() || useSavedDataPopulationScript();
    }

}
