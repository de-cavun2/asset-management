package decavun2.objects;

import static metamodels.MetaModels.VehicleFinDet_;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Test;

import decavun2.personnel.Person;
import decavun2.personnel.PersonRole;
import decavun2.test_config.AbstractDomainTestCase;
import ua.com.fielden.platform.entity.meta.MetaProperty;
import ua.com.fielden.platform.test.ioc.UniversalConstantsForTesting;
import ua.com.fielden.platform.utils.IUniversalConstants;

/**
 * This is an unit test for entity Vehicle.
 */

public class VehicleFinDetTest extends AbstractDomainTestCase {
	
    @Test
    public void commissionDate_can_be_before_desposalDate() {
        final Vehicle tram = co(Vehicle.class).findByKey("BC1111AH");
        final VehicleFinDet tramFinDet = co$(VehicleFinDet.class).findByKeyAndFetch(VehicleFinDetCo.FETCH_PROVIDER.fetchModel(), tram);
        
        final Date commissionDate = dateTime("2022-12-14 11:00:00").toDate();
        final Date desposalDate = dateTime("2023-12-14 11:00:00").toDate();
        tramFinDet.setCommissionDate(commissionDate);
        tramFinDet.setDisposalDate(desposalDate);
        assertEquals(commissionDate, tramFinDet.getCommissionDate());
        assertEquals(desposalDate, tramFinDet.getDisposalDate());
    }
    
    @Test
    public void commissionDate_cannot_be_after_desposalDate() {
        final Vehicle tram = co(Vehicle.class).findByKey("BC1111AH");
        final VehicleFinDet tramFinDet = co$(VehicleFinDet.class).findByKeyAndFetch(VehicleFinDetCo.FETCH_PROVIDER.fetchModel(), tram);
        
        final Date commissionDate = dateTime("2022-12-14 11:00:00").toDate();
        tramFinDet.setCommissionDate(commissionDate);
        assertEquals(commissionDate, tramFinDet.getCommissionDate());

        final Date desposalDate = dateTime("2023-12-14 11:00:00").toDate();
        tramFinDet.setDisposalDate(desposalDate);
        assertEquals(desposalDate, tramFinDet.getDisposalDate());
        
        final Date newFutureCommissionDate = dateTime("2024-12-14 11:00:00").toDate();
        tramFinDet.setCommissionDate(newFutureCommissionDate);
        
        final MetaProperty<Date> mpCommissionDate = tramFinDet.getProperty(VehicleFinDet_.commissionDate());
        assertFalse(mpCommissionDate.isValid());
        assertEquals(commissionDate, tramFinDet.getCommissionDate());
        
        final Date newFutureDesposalDate = dateTime("2025-12-14 11:00:00").toDate();
        tramFinDet.setDisposalDate(newFutureDesposalDate);
        assertEquals(newFutureDesposalDate, tramFinDet.getDisposalDate());
        assertTrue(mpCommissionDate.isValid());
        assertEquals(newFutureCommissionDate, tramFinDet.getCommissionDate());
    }
    
    @Test
    public void desposalDate_cannot_be_before_commissionDate() {
        final Vehicle tram = co(Vehicle.class).findByKey("BC1111AH");
        final VehicleFinDet tramFinDet = co$(VehicleFinDet.class).findByKeyAndFetch(VehicleFinDetCo.FETCH_PROVIDER.fetchModel(), tram);
        
        final Date commissionDate = dateTime("2022-12-14 11:00:00").toDate();
        tramFinDet.setCommissionDate(commissionDate);
        assertEquals(commissionDate, tramFinDet.getCommissionDate());

        final Date desposalDate = dateTime("2023-12-14 11:00:00").toDate();
        tramFinDet.setDisposalDate(desposalDate);
        assertEquals(desposalDate, tramFinDet.getDisposalDate());
        
        final MetaProperty<Date> mpDesposalDate = tramFinDet.getProperty(VehicleFinDet_.disposalDate());
        final Date newFutureDesposalDate = dateTime("2021-12-14 11:00:00").toDate();
        tramFinDet.setDisposalDate(newFutureDesposalDate);
        assertFalse(mpDesposalDate.isValid());
        assertEquals(desposalDate, tramFinDet.getDisposalDate());
        
        final Date newFutureCommissionDate = dateTime("2020-12-14 11:00:00").toDate();
        tramFinDet.setCommissionDate(newFutureCommissionDate);
        assertEquals(newFutureCommissionDate, tramFinDet.getCommissionDate());
        assertTrue(mpDesposalDate.isValid());
        assertEquals(newFutureDesposalDate, tramFinDet.getDisposalDate());
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
        
        final PersonRole driver = save(new_composite(PersonRole.class, "Driver-B").setDesc("Car driver."));
        final TransportCondition transportCondition = save(new_(TransportCondition.class).setConditionId("000001").setStage("available"));
        final Person driverPerson = save(new_(Person.class).setEmail("RMD@organisation.com").setPersonRole(driver).setName("Ronald").setSurname("McDonald").setActive(true));
        save(new_(Vehicle.class).setLicensePlate("BC1111AH").setModel("T 802").setCurrentLocation("Depot").setDriver(driverPerson).setActive(true).setTransportCondition(transportCondition).setDesc("The tram number two."));
    }

}
