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

import decavun2.config.ApplicationDomain;
import decavun2.data.IDomainData;
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

        setupUser(User.system_users.SU, "decavun2");
        setupPerson(User.system_users.SU, "decavun2");
        
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
         
        save(new_composite(AssignedVehicle.class, tramRouteNum1, date("2022-05-10 00:00:00"), "Tram_1")
        		.setStationSchedule("Starts work: 06:40; Ends work: 20:40.").setInterval(20).setActive(true));
        
        save(new_composite(AssignedVehicle.class, tramRouteNum1, date("2022-05-13 00:00:00"), "Tram_2")
        		.setStationSchedule("Starts work: 07:20; Ends work: 21:20.").setInterval(15).setActive(true));
        
        save(new_composite(AssignedVehicle.class, tramRouteNum8, date("2021-11-01 00:00:00"), "Tram_3")
        		.setStationSchedule("Starts work: 07:00; Ends work: 21:00.").setInterval(10).setActive(true));
        
        save(new_composite(AssignedVehicle.class, trolleyRouteNum25, date("2021-06-22 00:00:00"), "Tram_4")
        		.setStationSchedule("Starts work: 07:40; Ends work: 21:50.").setInterval(13).setActive(true));
        
        save(new_composite(AssignedVehicle.class, trolleyRouteNum33, date("2020-11-01 00:00:00"), "Tram_5")
        		.setStationSchedule("Starts work: 07:00; Ends work: 21:00.").setInterval(20).setActive(true));
        
        save(new_composite(AssignedVehicle.class, trolleyRouteNum25, date("2023-01-10 00:00:00"), "Tram_6")
        		.setStationSchedule("Starts work: 06:30; Ends work: 20:30.").setInterval(20).setActive(true));
//        save(new_composite(AssetType.class, "Hovercraft").setAssetClass(acVehicle).setDesc("Hovercraft equipment"));
//
//        final AssetCo coAsset = co(Asset.class);
//        final var generator = save(coAsset.new_().setAssetType(generators).setDesc("Some description"));
//        save(new_composite(AssetOwnership.class, generator, date("2020-10-11 00:00:00")).setRole("Role 1"));
//        save(new_composite(AssetOwnership.class, generator, date("2021-10-11 00:00:00")).setBusinessUnit("Business unit 1"));
//        save(new_composite(AssetOwnership.class, generator, date("2022-10-11 00:00:00")).setBusinessUnit("Business unit 2"));

        LOGGER.info("Completed database creation and population.");
    }

    @Override
    protected List<Class<? extends AbstractEntity<?>>> domainEntityTypes() {
        return applicationDomainProvider.entityTypes();
    }

}
