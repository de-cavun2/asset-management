package decavun2.dev_mod.util;

import static java.lang.String.format;
import static org.apache.logging.log4j.LogManager.getLogger;

import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;

import org.apache.logging.log4j.Logger;
import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.H2Dialect;
import org.hibernate.dialect.PostgreSQL82Dialect;

import decavun2.config.ApplicationDomain;
import decavun2.data.IDomainData;
import decavun2.personnel.Person;
import decavun2.personnel.PersonRole;
import decavun2.objects.TransportCondition;
import decavun2.objects.Vehicle;
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

        setupUser(User.system_users.SU, "User");
        
        final PersonRole tramDriver = save(new_composite(PersonRole.class, "Driver-T").setDesc("Category T driver (tram and trolleybus)."));
        final PersonRole carDriver = save(new_composite(PersonRole.class, "Driver-B").setDesc("Category B driver (passenger car)"));
        final PersonRole fleetManager = save(new_composite(PersonRole.class, "Fleet manager").setDesc("Person resposible for fleet management."));
        final PersonRole hrManager = save(new_composite(PersonRole.class, "HR manager").setDesc("Person resposible for human resourse management."));
        
        setupPerson(User.system_users.SU, "decavun2", "Super", "User", hrManager);
        createAndSavePerson("Sukhorskyy@let.com", "Yuriy", "Sukhorskyy", (User) null, tramDriver);
        createAndSavePerson("Veselyy@let.com", "Vasyl", "Veselyy", (User) null, tramDriver);
        createAndSavePerson("Tsikavyy@let.com", "Bohdan", "Tsikavyy", (User) null, carDriver);
        createAndSavePerson("Kvitlyva@let.com", "Yulia", "Kvitlyva", (User) null, fleetManager);
        setupUser(User.system_users.SU, "decavun2");
        
        save(new_(TransportCondition.class).setConditionId("000001").setStage("available"));
        save(new_(TransportCondition.class).setConditionId("000002").setStage("need further inspection"));
        save(new_(TransportCondition.class).setConditionId("000003").setStage("minor issues"));
        save(new_(TransportCondition.class).setConditionId("000004").setStage("significant issues"));
        save(new_(TransportCondition.class).setConditionId("000005").setStage("unavailable"));
        
        final PersonRole driver1 = save(new_composite(PersonRole.class, "Driver-C").setDesc("Bus driver."));
        final TransportCondition available = new_(TransportCondition.class).setConditionId("000001").setStage("available");
        final Person driverPerson1 = save(new_(Person.class).setEmail("Ron@let.com").setPersonRole(driver1).setName("Ronald").setSurname("McDonald").setActive(true));
        save(new_(Vehicle.class).setLicensePlate("BC1111AH").setModel("T 802").setCurrentLocation("Depot").setDriver(driverPerson1).setActive(true).setTransportCondition(available).setDesc("Bus number 46."));
        
        final PersonRole driver2 = save(new_composite(PersonRole.class, "Driver-B").setDesc("Tram driver."));
        final Person driverPerson2 = save(new_(Person.class).setEmail("Tomas@let.com").setPersonRole(driver2).setName("Tom").setSurname("Sunshine").setActive(true));
        save(new_(Vehicle.class).setLicensePlate("BC1010KL").setModel("T 900").setCurrentLocation("Depot").setDriver(driverPerson2).setActive(true).setTransportCondition(available).setDesc("Tram number 2."));

        final PersonRole driver3 = save(new_composite(PersonRole.class, "Driver-C").setDesc("Bus driver."));
        final Person driverPerson3 = save(new_(Person.class).setEmail("Vasyl@let.com").setPersonRole(driver3).setName("Vasyl").setSurname("Petrenko").setActive(true));
        save(new_(Vehicle.class).setLicensePlate("BC1212MN").setModel("B 100").setCurrentLocation("Depot").setDriver(driverPerson3).setActive(true).setTransportCondition(available).setDesc("Bus number 61."));
        
        final PersonRole driver4 = save(new_composite(PersonRole.class, "Driver-B").setDesc("Tram driver."));
        final TransportCondition unavailable = new_(TransportCondition.class).setConditionId("000005").setStage("unavailable");
        final Person driverPerson4 = save(new_(Person.class).setEmail("Bruce@let.com").setPersonRole(driver4).setName("Bruce").setSurname("Smidth").setActive(true));
        save(new_(Vehicle.class).setLicensePlate("BC4030AH").setModel("T 900").setCurrentLocation("Depot").setDriver(driverPerson4).setActive(true).setTransportCondition(unavailable).setDesc("Tram number 8."));
        
        final PersonRole driver5 = save(new_composite(PersonRole.class, "Driver-B").setDesc("Tram driver."));
        final Person driverPerson5 = save(new_(Person.class).setEmail("Ivan@let.com").setPersonRole(driver5).setName("Ivan").setSurname("Rudyy").setActive(true));
        save(new_(Vehicle.class).setLicensePlate("BC2015MK").setModel("T 800").setCurrentLocation("Depot").setDriver(driverPerson5).setActive(true).setTransportCondition(unavailable).setDesc("Tram number 1."));

        LOGGER.info("Completed database creation and population.");
    }

    @Override
    protected List<Class<? extends AbstractEntity<?>>> domainEntityTypes() {
        return applicationDomainProvider.entityTypes();
    }

}
