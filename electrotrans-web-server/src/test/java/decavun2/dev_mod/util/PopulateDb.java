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

import decavun2.change.Report;
import decavun2.config.ApplicationDomain;
import decavun2.data.IDomainData;
import decavun2.objects.DriverReport;
import decavun2.objects.Vehicle;
import decavun2.personnel.Person;
import decavun2.personnel.Person;
import decavun2.personnel.PersonRole;
import decavun2.objects.TransportCondition;
import decavun2.objects.Vehicle;
import decavun2.object.AssignedVehicle;
import decavun2.object.Route;
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
        final PersonRole driver1 = save(new_composite(PersonRole.class, "Driver-C").setDesc("Tram driver."));
        final Person driverPerson1 = save(new_(Person.class).setEmail("Ron@let.com").setPersonRole(driver1).setName("Ronald").setSurname("McDonald").setActive(true));

        
        final TransportCondition cond = save(new_(TransportCondition.class).setConditionId("sdfdsf").setStage("some stage"));
        final Person driverPerson = save(new_(Person.class).setEmail("Ivan@electrotrans.com").setDesc("Ivan Tester").setActive(true).setName("Igor").setSurname("Igor").setPersonRole(driver1));
        final Vehicle veh = save(new_(Vehicle.class).setTransportCondition(cond).setLicensePlate("akjsgnlkjsdn").setModel("T 802").setCurrentLocation("Depot").setDriver(driverPerson).setActive(true).setDesc("The tram number two."));
        save(new_(DriverReport.class).setDriverReportID("sdfsdfsd").setVehicle(veh).setState("good").setDesc("desc"));
        save(new_(DriverReport.class).setDriverReportID("sdfsdf").setVehicle(veh).setState("good").setDesc("Desc"));
        
        final Report rp = new_(Report.class);
        rp.setTitle("Profitability calculation of IS for the enterprise");
        rp.setDesc("This description should portray pluses and minuses of proposed change");
        rp.setDepartment("Financial Department");
        rp.setPerson(driverPerson1);
        rp.setIssue("Find proposed information is sensible for the enterprise to implement");
        
        save(rp);
        
        final PersonRole tramDriver = save(new_composite(PersonRole.class, "Driver-T").setDesc("Category T driver (tram and trolleybus)."));
        final PersonRole carDriver = save(new_composite(PersonRole.class, "Driver-B").setDesc("Category B driver (passenger car)"));
        final PersonRole fleetManager = save(new_composite(PersonRole.class, "Fleet manager").setDesc("Person resposible for fleet management."));
        final PersonRole hrManager = save(new_composite(PersonRole.class, "HR manager").setDesc("Person resposible for human resourse management."));
        
        setupPerson(User.system_users.SU, "decavun2", "Super", "User", hrManager);
        createAndSavePerson("Sukhorskyy@let.com", "Yuriy", "Sukhorskyy", (User) null, tramDriver);
        createAndSavePerson("Veselyy@let.com", "Vasyl", "Veselyy", (User) null, tramDriver);
        createAndSavePerson("Tsikavyy@let.com", "Bohdan", "Tsikavyy", (User) null, carDriver);
        createAndSavePerson("Kvitlyva@let.com", "Yulia", "Kvitlyva", (User) null, fleetManager);
        
        final TransportCondition available = save(new_(TransportCondition.class).setConditionId("000001").setStage("available"));
        save(new_(TransportCondition.class).setConditionId("000002").setStage("need further inspection"));
        save(new_(TransportCondition.class).setConditionId("000003").setStage("minor issues"));
        save(new_(TransportCondition.class).setConditionId("000004").setStage("significant issues"));
        final TransportCondition unavailable = save(new_(TransportCondition.class).setConditionId("000005").setStage("unavailable"));
        
        
       
        final Vehicle vehicle1 = save(new_(Vehicle.class).setLicensePlate("BC1111AH").setModel("T 802").setCurrentLocation("Depot").setDriver(driverPerson1).setActive(true).setTransportCondition(available).setDesc("Tram number 1."));
        
        final PersonRole driver2 = save(new_composite(PersonRole.class, "Driver-P").setDesc("Tram driver."));
        final Person driverPerson2 = save(new_(Person.class).setEmail("Tomas@let.com").setPersonRole(driver2).setName("Tom").setSurname("Sunshine").setActive(true));
        final Vehicle vehicle2 = save(new_(Vehicle.class).setLicensePlate("BC1010KL").setModel("T 900").setCurrentLocation("Depot").setDriver(driverPerson2).setActive(true).setTransportCondition(available).setDesc("Tram number 1."));

        final PersonRole driver3 = save(new_composite(PersonRole.class, "Driver-L").setDesc("Tram driver.."));
        final Person driverPerson3 = save(new_(Person.class).setEmail("Vasyl@let.com").setPersonRole(driver3).setName("Vasyl").setSurname("Petrenko").setActive(true));
        final Vehicle vehicle3 = save(new_(Vehicle.class).setLicensePlate("BC1212MN").setModel("B 100").setCurrentLocation("Depot").setDriver(driverPerson3).setActive(true).setTransportCondition(available).setDesc("Tram number 8."));
        
        final PersonRole driver4 = save(new_composite(PersonRole.class, "Driver-K").setDesc("Trolley driver."));
        final Person driverPerson4 = save(new_(Person.class).setEmail("Bruce@let.com").setPersonRole(driver4).setName("Bruce").setSurname("Smidth").setActive(true));
        final Vehicle vehicle4 = save(new_(Vehicle.class).setLicensePlate("BC4030AH").setModel("T 900").setCurrentLocation("Depot").setDriver(driverPerson4).setActive(true).setTransportCondition(available).setDesc("Trolley number 25."));
        
        final PersonRole driver5 = save(new_composite(PersonRole.class, "Driver-X").setDesc("Trolley driver."));
        final Person driverPerson5 = save(new_(Person.class).setEmail("Ivan@let.com").setPersonRole(driver5).setName("Ivan").setSurname("Rudyy").setActive(true));
        final Vehicle vehicle5 = save(new_(Vehicle.class).setLicensePlate("BC2015MK").setModel("T 800").setCurrentLocation("Depot").setDriver(driverPerson5).setActive(true).setTransportCondition(available).setDesc("Trolley number 33."));
        
        final PersonRole driver6 = save(new_composite(PersonRole.class, "Driver-H").setDesc("Tram driver."));
        final Person driverPerson6 = save(new_(Person.class).setEmail("Igor@let.com").setPersonRole(driver6).setName("Igor").setSurname("Tester").setActive(true));
        save(new_(Vehicle.class).setLicensePlate("AB1586BH").setModel("T 767").setCurrentLocation("Depot").setDriver(driverPerson6).setActive(true).setTransportCondition(unavailable).setDesc("Tram number 6."));
        
        final PersonRole driver7 = save(new_composite(PersonRole.class, "Driver-F").setDesc("Trolley driver."));
        final Person driverPerson7 = save(new_(Person.class).setEmail("Oleg@let.com").setPersonRole(driver7).setName("Oleg").setSurname("Petrovuch").setActive(true));
        save(new_(Vehicle.class).setLicensePlate("AB9478BH").setModel("T 850").setCurrentLocation("Depot").setDriver(driverPerson7).setActive(true).setTransportCondition(unavailable).setDesc("Trolley number 8."));

        
        final Route tramRouteNum1 = save(new_composite(Route.class, 1).setName("Railway Station — Pasichna st.")
        		.setStationOrder("Railway station — Lviv Polytechnic — Doroshenko st. — Rynok Square — Lychakivska st. — Pasichna st.")
        		.setTrafficData("At the moment, there exists a big traffic jam at the beginning of the Lychakivska str. Unfortunately there is no possibility to bypass it.")
        		.setInUse(true).setDesc("Tram route №1"));
        
        final Route tramRouteNum8 = save(new_composite(Route.class, 8).setName("Soborna sq. — Vernadskyi st.")
        		.setStationOrder("Soborna sq. — Stryiskyi Park — Academy of Arts — Stadium Ukraine — Dovzhenko Center — Polyclinic No. 4 — Vernadskyi str.")
        		.setTrafficData("Unfortunately, near the Dovzhenko Center occured an road accident, to bypass it, follow the route №3(to aquapark) .")
        		.setInUse(true).setDesc("Tram route №8"));
        
        final Route trolleyRouteNum33 = save(new_composite(Route.class, 33).setName("Ivana Pidkovy sq. — Grinchenko st.")
        		.setStationOrder("Ivana Pidkovy sq. — Chornovola st. — Chemical st. — Varshavska st. — Emergency Hospital — Pluhova st. — Grinchenko st.")
        		.setTrafficData("Currently, there are no traffic jams or accidents on the route.")
        		.setInUse(true).setDesc("Trolleybus route №33"));
        
        final Route trolleyRouteNum25 = save(new_composite(Route.class, 25).setName("Shota Rustaveli st. — Bus station.")
        		.setStationOrder("Shote Rustaveli st. — Ground Forces Academy — Tax Office — Bus Plant — Naukova st. — Maksymovicha st. — Bus Station.")
        		.setTrafficData("Currently, there are no traffic jams or accidents on the route.")
        		.setInUse(true).setDesc("Trolleybus route №25"));
         
        save(new_composite(AssignedVehicle.class, tramRouteNum1, date("2022-05-10 00:00:00"), vehicle1)
        		.setStationSchedule("Starts work: 06:40; Ends work: 20:40.").setInterval(20).setActive(true));
        
        save(new_composite(AssignedVehicle.class, tramRouteNum1, date("2022-05-13 00:00:00"), vehicle2)
        		.setStationSchedule("Starts work: 07:20; Ends work: 21:20.").setInterval(15).setActive(true));
        
        save(new_composite(AssignedVehicle.class, tramRouteNum8, date("2021-11-01 00:00:00"), vehicle3)
        		.setStationSchedule("Starts work: 07:00; Ends work: 21:00.").setInterval(10).setActive(true));
        
        save(new_composite(AssignedVehicle.class, trolleyRouteNum25, date("2021-06-22 00:00:00"), vehicle4)
        		.setStationSchedule("Starts work: 07:40; Ends work: 21:50.").setInterval(13).setActive(true));
        
        save(new_composite(AssignedVehicle.class, trolleyRouteNum33, date("2020-11-01 00:00:00"), vehicle5)
        		.setStationSchedule("Starts work: 07:00; Ends work: 21:00.").setInterval(20).setActive(true));
        

        LOGGER.info("Completed database creation and population.");
    }

    @Override
    protected List<Class<? extends AbstractEntity<?>>> domainEntityTypes() {
        return applicationDomainProvider.entityTypes();
    }

}
