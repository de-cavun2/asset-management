package decavun2.objects;

import static metamodels.MetaModels.Vehicle_;
import static org.junit.Assert.*;

import org.junit.Test;

import ua.com.fielden.platform.test.ioc.UniversalConstantsForTesting;
import ua.com.fielden.platform.utils.IUniversalConstants;

import decavun2.personnel.Person;
import decavun2.test_config.AbstractDomainTestCase;

/**
 * This is an unit test for entity Vehicle.
 */

public class VehicleTest extends AbstractDomainTestCase {
	
    final String ERROR_MSG_NUM_1 = "Required property [%s] is not specified for entity [%s].".formatted("Model", "Vehicle");

    @Test
    public void licensePlate_model_currentLocation_driver_desc_is_required_for_entity_vehicle() {
        
        final Vehicle vehicleValid = co(Vehicle.class).findByKey("BC1111AH");
        final Vehicle vehicleUnvalid = new_(Vehicle.class).setLicensePlate("BC2103AH").setDesc("Trolleybus nomer ten.");
        
        
        assertNotNull(vehicleValid);
        assertTrue(vehicleValid.isPersisted());
        
        assertNotNull(vehicleUnvalid);
        assertFalse(vehicleUnvalid.isPersisted());
        assertFalse(vehicleUnvalid.isValid().isSuccessful());

        assertTrue(vehicleUnvalid.getProperty(Vehicle_.licensePlate()).isRequired());        
        assertTrue(vehicleUnvalid.getProperty(Vehicle_.model()).isRequired());
        assertTrue(vehicleUnvalid.getProperty(Vehicle_.currentLocation()).isRequired());
        assertTrue(vehicleUnvalid.getProperty(Vehicle_.driver()).isRequired());
        assertTrue(vehicleUnvalid.getProperty(Vehicle_.desc()).isRequired());
        
        try {
            save(vehicleUnvalid);
            } catch (final Exception ex) {
                
                assertEquals(ERROR_MSG_NUM_1, ex.getMessage());
            }
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
        
        final Person driverPerson = save(new_(Person.class).setEmail("Ivan@electrotrans.com").setDesc("Ivan Tester").setActive(true));
        save(new_(Vehicle.class).setLicensePlate("BC1111AH").setModel("T 802").setCurrentLocation("Depot").setDriver(driverPerson).setActive(true).setDesc("The tram number two."));
    }

}
