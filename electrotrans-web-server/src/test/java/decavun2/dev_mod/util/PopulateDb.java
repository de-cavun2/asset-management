package decavun2.dev_mod.util;

import static java.lang.String.format;

import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;

import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.H2Dialect;
import org.hibernate.dialect.PostgreSQL82Dialect;

import static org.apache.logging.log4j.LogManager.getLogger;
import org.apache.logging.log4j.Logger;

import decavun2.change.Issue;
import decavun2.config.ApplicationDomain;
import decavun2.data.IDomainData;
import decavun2.personnel.Person;
import decavun2.utils.PostgresqlDbUtils;

import ua.com.fielden.platform.devdb_support.DomainDrivenDataPopulation;
import ua.com.fielden.platform.entity.AbstractEntity;
import ua.com.fielden.platform.persistence.HibernateUtil;
import ua.com.fielden.platform.security.user.User;
import ua.com.fielden.platform.test.IDomainDrivenTestCaseConfiguration;
import ua.com.fielden.platform.utils.DbUtils;

/**
 * This is a convenience class for (re-)creation of the development database and its population.
 *
 * It contains the <code>main</code> method and can be executed whenever the target database needs to be (re-)set.
 * <p>
 *
 * <b>IMPORTANT: </b><i>One should be careful not to run this code against the deployment or production databases, which would lead to the loss of all data.</i>
 *
 * <p>
 *
 * @author Generated
 */
public class PopulateDb extends DomainDrivenDataPopulation implements IDomainData {

    private static final Logger LOGGER = getLogger(PopulateDb.class);

    private final ApplicationDomain applicationDomainProvider = new ApplicationDomain();

    private PopulateDb(final IDomainDrivenTestCaseConfiguration config, final Properties props) {
        super(config, props);
    }

    public static void main(final String[] args) throws Exception {
        LOGGER.info("Initialising...");
        final String configFileName = args.length == 1 ? args[0] : "application.properties";
        final Properties props = new Properties();
        try (final FileInputStream in = new FileInputStream(configFileName)) {
            props.load(in);
        }

        LOGGER.info("Obtaining Hibernate dialect...");
        final Class<?> dialectType = Class.forName(props.getProperty("hibernate.dialect"));
        final Dialect dialect = (Dialect) dialectType.getDeclaredConstructor().newInstance();
        LOGGER.info(format("Running with dialect %s...", dialect));
        final DataPopulationConfig config = new DataPopulationConfig(props);
        LOGGER.info("Generating DDL and running it against the target DB...");

        // use TG DDL generation or
        // Hibernate DDL generation final List<String> createDdl = DbUtils.generateSchemaByHibernate()
        final List<String> createDdl = config.getDomainMetadata().generateDatabaseDdl(dialect);
        final List<String> ddl = dialect instanceof H2Dialect ? DbUtils.prependDropDdlForH2(createDdl) :
                                 dialect instanceof PostgreSQL82Dialect ? PostgresqlDbUtils.prependDropDdlForPostgresql(createDdl) :
                                 DbUtils.prependDropDdlForSqlServer(createDdl);

        DbUtils.execSql(ddl, config.getInstance(HibernateUtil.class).getSessionFactory().getCurrentSession());

        final PopulateDb popDb = new PopulateDb(config, props);
        popDb.populateDomain();
    }

    @Override
    protected void populateDomain() {
        LOGGER.info("Creating and populating the development database...");

        setupUser(User.system_users.SU, "decavun2");
        setupPerson(User.system_users.SU, "decavun2");
        
        final String issue1 = "During the race driver Ivan faced a problem with the exhaust. Repair is required!";
        final String issue2 = "After computing statistics there was extremely big gap between income and profit. We need take some actions!";
        final String issue3 = "Driver license was not updated. Update is required!";
        final Person driver = save(new_(Person.class).setEmail("Ivan@electrotrans.com").setDesc("Ivan Driver").setActive(true));
        final Person mechanic = save(new_(Person.class).setEmail("Vasyl@electrotrans.com").setDesc("Vasyl Mechanic").setActive(true));
        final Person fleetManager = save(new_(Person.class).setEmail("Oleh@electrotrans.com").setDesc("Oleh Manager").setActive(true));

        save(new_(Issue.class).setIssueNumber("000001").setResponsiblePerson(mechanic).setActive(true).setDescription(issue1).setDate(date("2023-10-01 11:30:00")));
        save(new_(Issue.class).setIssueNumber("000002").setResponsiblePerson(fleetManager).setActive(true).setDescription(issue2).setDate(date("2023-15-01 09:30:00")));
        save(new_(Issue.class).setIssueNumber("000003").setResponsiblePerson(driver).setActive(true).setDescription(issue3).setDate(date("2023-10-01 15:20:00")));
        
        LOGGER.info("Completed database creation and population.");
    }

    @Override
    protected List<Class<? extends AbstractEntity<?>>> domainEntityTypes() {
        return applicationDomainProvider.entityTypes();
    }

}
