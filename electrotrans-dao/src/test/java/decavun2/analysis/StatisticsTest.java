package decavun2.analysis;

import static metamodels.MetaModels.Statistics_;
import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import ua.com.fielden.platform.entity.meta.MetaProperty;
import ua.com.fielden.platform.error.Result;
import ua.com.fielden.platform.test.ioc.UniversalConstantsForTesting;
import ua.com.fielden.platform.utils.IUniversalConstants;
import decavun2.objects.DriverReport;
import decavun2.objects.Repair;
import decavun2.objects.TransportCondition;
import decavun2.objects.Vehicle;
import decavun2.personnel.Person;
import decavun2.test_config.AbstractDomainTestCase;
import metamodels.MetaModels;

/**
 * This is an example unit test, which can be used as a starting point for
 * creating application unit tests.
 *
 * @author Generated
 */
public class StatisticsTest extends AbstractDomainTestCase {

	static final String vehiclePlate = "BC1111AH";
	static final String driverReportID = "someid";

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
		final var stat = save(co(Statistics.class).new_().setName("Some name").setVehicle(vehicle)
				.setStartDate(startDate).setEndDate(endDate));

		assertNotNull(stat.getVehicle());
		final MetaProperty<Statistics> mpVehicle = stat.getProperty(Statistics_.vehicle());
		assertFalse(mpVehicle.isEditable());

		try {
			stat.setVehicle(vehicle);
			fail("Vehicle should become readonly after the value is set");
		} catch (final Result ex) {
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

	@Test
	public void statistical_properties_should_be_in_between_startDate_and_endDate() {
		final var vehicle = co(Vehicle.class).findByKey(vehiclePlate);
		createRepair("id2", vehicle, "2023-01-10 10:00:00");
		createRepair("id3", vehicle, "2023-01-10 12:00:00");

		createReport("id4", vehicle, "2023-01-11 10:00:00");

		final Date startDate = dateTime("2023-01-10 00:00:00").toDate();
		final Date endDate = dateTime("2023-01-11 12:00:00").toDate();
		final var stat = save(co(Statistics.class).new_().setName("Some name").setVehicle(vehicle)
				.setStartDate(startDate).setEndDate(endDate));

		assertEquals(1, stat.getIssuesCount().intValue());
		assertEquals(2, stat.getRepairsCount().intValue());

	}

	@Override
	public boolean saveDataPopulationScriptToFile() {
		return false;
	}

	@Override
	public boolean useSavedDataPopulationScript() {
		return false;
	}

	public Repair createRepair(String Id, Vehicle vehicle, String createdAtDatetime) {
		final Date createdAt = dateTime(createdAtDatetime).toDate();
		return save(new_(Repair.class).setRepairID(Id).setVehicle(vehicle).setCreatedAt(createdAt));
	}

	public DriverReport createReport(String Id, Vehicle vehicle, String createdAtDatetime) {
		final Date createdAt = dateTime(createdAtDatetime).toDate();
		return save(new_(DriverReport.class).setDriverReportID(Id).setVehicle(vehicle).setCreatedAt(createdAt));
	}

	@Override
	protected void populateDomain() {
		super.populateDomain();

		if (useSavedDataPopulationScript()) {
			return;
		}
		
		final TransportCondition cond = save(new_(TransportCondition.class).setConditionId("sdfdsf").setStage("some stage"));

		final Person driverPerson = save(
				new_(Person.class).setEmail("Ivan@electrotrans.com").setDesc("Ivan Tester").setActive(true));
		final Vehicle veh = save(new_(Vehicle.class).setTransportCondition(cond).setLicensePlate(vehiclePlate)
				.setModel("T 802").setCurrentLocation("Depot").setDriver(driverPerson).setActive(true)
				.setDesc("The tram number two."));
		save(new_(DriverReport.class).setDriverReportID(driverReportID).setVehicle(veh));
		save(new_(DriverReport.class).setDriverReportID("sdfsdf").setVehicle(veh));

		createRepair("id1", veh, "2023-01-20 10:00:00");

	}

}