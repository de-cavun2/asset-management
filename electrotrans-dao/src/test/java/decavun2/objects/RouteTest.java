package decavun2.objects;

import static metamodels.MetaModels.Route_;
import static decavun2.object.RouteCo.ERROR_MSG_REQUIRED_PROPERTIES;
import static decavun2.object.RouteCo.ERROR_MSG_INUSE_REQUIRES_TRAFFICDATA;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import decavun2.object.Route;
import decavun2.test_config.AbstractDomainTestCase;
import ua.com.fielden.platform.entity.meta.MetaProperty;
import ua.com.fielden.platform.test.ioc.UniversalConstantsForTesting;
import ua.com.fielden.platform.utils.IUniversalConstants;

public class RouteTest extends AbstractDomainTestCase {

    @Test
    public void stationOrder_trafficData_routeName_routeNum_desc_is_required_for_entity_route() {
        
        final Route routeValid = co(Route.class).findByKey(10);
        final Route routeUnvalid = new_(Route.class).setName("Stryiska-Sykhiv").setRouteNum(5);
        
        
        assertNotNull(routeValid);
        assertTrue(routeValid.isPersisted());
        
        assertNotNull(routeUnvalid);
        assertFalse(routeUnvalid.isPersisted());
        assertFalse(routeUnvalid.isValid().isSuccessful());

                
        assertTrue(routeUnvalid.getProperty(Route_.stationOrder()).isRequired());
        assertTrue(routeUnvalid.getProperty(Route_.name()).isRequired());
        assertTrue(routeUnvalid.getProperty(Route_.routeNum()).isRequired());
        assertTrue(routeUnvalid.getProperty(Route_.desc()).isRequired());        
        
        try {
            save(routeUnvalid);
            } catch (final Exception ex) {
                
                assertEquals(ERROR_MSG_REQUIRED_PROPERTIES, ex.getMessage());
            }
    }
    
    @Test
    public void inUse_property_requires_property_trafficData() {
        final Route route = new_composite(Route.class, 8).setName("Pasichna-Rynok square")
        		.setStationOrder("Pasichna-Luchakivska-Mutna-Rynok square").setDesc("Route 8");

        route.setInUse(true);
        route.setTrafficData(null);

        assertNotNull(route);

        final MetaProperty<String> mpTrafficData = route.getProperty(Route_.trafficData());
        assertFalse(mpTrafficData.isValid());
        assertEquals(ERROR_MSG_INUSE_REQUIRES_TRAFFICDATA, mpTrafficData.getFirstFailure().getMessage());
    }

    /**
     * In case of a complex data population it is possible to store the data into a script by changing this method to return <code>true</code>.
     * <p>
     * This way it is possible to reuse it later in place of re-running the data population logic, which is a lot faster.
     * Please also refer method {@link #useSavedDataPopulationScript()} below -- the values returned by this and that method cannot be <code>true</code> simultaneously.
     */
    @Override
    public boolean saveDataPopulationScriptToFile() {
        return false;
    }

    /**
     * If the test data was populated and saved as a script file (hinted in method {@link #saveDataPopulationScriptToFile()} above),
     * then this method can be changed to return <code>true</code> in order to avoid execution of the data population logic and simply execute the saved script.
     * This makes the population of the test data a lot faster.
     * It is very convenient when there is a need to run the same test case multiple times interactively.
     * <p>
     * However, this method should never return <code>true</code> when running multiple test cases.
     * Therefore, it is important to change this method to return <code>false</code> before committing changes into your VCS such as Git.
     */
    @Override
    public boolean useSavedDataPopulationScript() {
        return false;
    }

    /**
     * Domain state population method.
     * <p>
     * <b>IMPORTANT:</p> this method executes only once for a Test Case. At the same time, new instances of a Test Case are created for each test method.
     * Thus, this method should not be used for initialisation of the Test Case state other than the persisted domain state.
     */
    @Override
    protected void populateDomain() {
        super.populateDomain();

        final UniversalConstantsForTesting constants = (UniversalConstantsForTesting) getInstance(IUniversalConstants.class);
        constants.setNow(dateTime("2019-10-01 11:30:00"));

        if (useSavedDataPopulationScript()) {
            return;
        }

        save(new_(Route.class).setRouteNum(10).setName("Pasichna-Mutna").setStationOrder("Pasichna-Luchakivska-Mutna").setTrafficData("At the moment there is a traffic jam on the Mutna street.").setInUse(true).setDesc("Route 102"));
    }
}
