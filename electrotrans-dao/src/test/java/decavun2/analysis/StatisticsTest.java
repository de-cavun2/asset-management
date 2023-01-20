package decavun2.analysis;


import static metamodels.MetaModels.Statistics_;
import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import ua.com.fielden.platform.entity.meta.MetaProperty;
import ua.com.fielden.platform.error.Result;
import ua.com.fielden.platform.test.ioc.UniversalConstantsForTesting;
import ua.com.fielden.platform.utils.IUniversalConstants;
import decavun2.objects.Vehicle;
import decavun2.personnel.Person;
import decavun2.test_config.AbstractDomainTestCase;
import metamodels.MetaModels;

/**
 * This is an example unit test, which can be used as a starting point for creating application unit tests.
 *
 * @author Generated
 */
public class StatisticsTest extends AbstractDomainTestCase {
	
	static final String vehiclePlate = "BC1111AH";
	
	@Test
	public void properties_name_vehicle_startDate_endDate_are_required() {
		final var stat = co(Statistics.class).new_();
		
		final MetaProperty<Statistics> mpName = stat.getProperty(Statistics_.name());
		final MetaProperty<Statistics> mpVehicle = stat.getProperty(Statistics_.vehicle());
		final MetaProperty<Statistics> mpStartDate = stat.getProperty(Statistics_.startDate());
		final MetaProperty<Statistics> mpEndDate = stat.getProperty(Statistics_.endDate());
		
		assertTrue(mpName.isRequired());
		assertTrue(mpVehicle.isRequired());
		assertTrue(mpStartDate.isRequired());
		assertTrue(mpEndDate.isRequired());
		
	}
	
	@Test
	public void property_vehicle_becomes_readonly_after_instance_is_saved() {
		final var vehicle = co(Vehicle.class).findByKey(vehiclePlate);
        final Date startDate = dateTime("2022-12-19 00:00:00").toDate();
        final Date endDate = dateTime("2023-12-19 00:00:00").toDate();
		final var stat = save(co(Statistics.class).new_()
				.setName("Some name")
				.setVehicle(vehicle)
				.setStartDate(startDate)
				.setEndDate(endDate));
		
		assertNotNull(stat.getVehicle());
		final MetaProperty<Statistics> mpVehicle = stat.getProperty(Statistics_.vehicle());
		assertFalse(mpVehicle.isEditable());
		
		try {
			stat.setVehicle(vehicle);
			fail("Vehicle should become readonly after the value is set");
		}catch(final Result ex) {
			assertEquals(StatisticsCo.STATISTICS_IS_NOT_EDITABLE_ERROR, ex.getMessage());
		}
	}
	
	@Test
	public void propeties_createdAt_repairCount_issuesCount_are_readonly() {
		final var stat = co(Statistics.class).new_();
		
		final MetaProperty<Statistics> mpCreatedAt = stat.getProperty(Statistics_.createdAt());
		final MetaProperty<Statistics> mpRepairCount = stat.getProperty(Statistics_.repairsCount());
		final MetaProperty<Statistics> mpIssuesCount = stat.getProperty(Statistics_.issuesCount());
		
		assertFalse(mpCreatedAt.isEditable());
		assertFalse(mpRepairCount.isEditable());
		assertFalse(mpIssuesCount.isEditable());

	}
	

    @Override
    public boolean saveDataPopulationScriptToFile() {
        return false;
    }


    @Override
    public boolean useSavedDataPopulationScript() {
        return false;
    }


    @Override
    protected void populateDomain() {
        super.populateDomain();

      
        if (useSavedDataPopulationScript()) {
            return;
        }
        
        final Person driverPerson = save(new_(Person.class).setEmail("Ivan@electrotrans.com").setDesc("Ivan Tester").setActive(true));
        save(new_(Vehicle.class).setTransportCondition("good").setLicensePlate(vehiclePlate).setModel("T 802").setCurrentLocation("Depot").setDriver(driverPerson).setActive(true).setDesc("The tram number two."));
        

    }

}