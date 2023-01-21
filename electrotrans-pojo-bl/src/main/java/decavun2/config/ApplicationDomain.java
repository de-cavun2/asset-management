package decavun2.config;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import decavun2.analysis.Statistics;
import decavun2.objects.TransportCondition;
import decavun2.objects.DriverReport;
import decavun2.objects.Order;
import decavun2.objects.Repair;
import decavun2.objects.Vehicle;
import decavun2.personnel.Person;
import ua.com.fielden.platform.basic.config.IApplicationDomainProvider;
import ua.com.fielden.platform.domain.PlatformDomainTypes;
import ua.com.fielden.platform.entity.AbstractEntity;
import decavun2.personnel.PersonRole;
import decavun2.objects.VehicleFinDet;
import decavun2.objects.ui_actions.OpenVehicleMasterAction;
import decavun2.objects.master.menu.actions.VehicleMaster_OpenMain_MenuItem;
import decavun2.objects.master.menu.actions.VehicleMaster_OpenVehicleFinDet_MenuItem;
import decavun2.object.Route;
import decavun2.object.AssignedVehicle;
import decavun2.object.ui_actions.OpenRouteMasterAction;
import decavun2.object.master.menu.actions.RouteMaster_OpenMain_MenuItem;
import decavun2.object.master.menu.actions.RouteMaster_OpenAssignedVehicle_MenuItem;
import decavun2.change.Report;
import decavun2.change.Issue;
import decavun2.change.Change;
import decavun2.change.ui_actions.OpenChangeMasterAction;
import decavun2.change.master.menu.actions.ChangeMaster_OpenMain_MenuItem;
import decavun2.change.master.menu.actions.ChangeMaster_OpenIssue_MenuItem;
import decavun2.change.master.menu.actions.ChangeMaster_OpenReport_MenuItem;

/**
 * A class to register domain entities.
 *
 * @author Generated
 */
public class ApplicationDomain implements IApplicationDomainProvider {

    private static final Set<Class<? extends AbstractEntity<?>>> entityTypes = new LinkedHashSet<>();
    private static final Set<Class<? extends AbstractEntity<?>>> domainTypes = new LinkedHashSet<>();

    static {
        entityTypes.addAll(PlatformDomainTypes.types);
        add(Person.class);
        add(PersonRole.class);
        add(Vehicle.class);
        add(VehicleFinDet.class);
        add(OpenVehicleMasterAction.class);
        add(VehicleMaster_OpenMain_MenuItem.class);
        add(VehicleMaster_OpenVehicleFinDet_MenuItem.class);
        add(TransportCondition.class);
        add(Route.class);
        add(AssignedVehicle.class);
        add(OpenRouteMasterAction.class);
        add(RouteMaster_OpenMain_MenuItem.class);
        add(RouteMaster_OpenAssignedVehicle_MenuItem.class);
		add(Person.class);
		add(Vehicle.class);
		add(Statistics.class);
		add(DriverReport.class);
		add(Repair.class);
        add(Order.class);
        add(DriverReport.class);
		add(Report.class);
		add(Issue.class);
		add(Change.class);
		add(OpenChangeMasterAction.class);
		add(ChangeMaster_OpenMain_MenuItem.class);
		add(ChangeMaster_OpenIssue_MenuItem.class);
		add(ChangeMaster_OpenReport_MenuItem.class);

    }

    private static void add(final Class<? extends AbstractEntity<?>> domainType) {
       entityTypes.add(domainType);
       domainTypes.add(domainType);
    }

    @Override
    public List<Class<? extends AbstractEntity<?>>> entityTypes() {
       return Collections.unmodifiableList(entityTypes.stream().collect(Collectors.toList()));
    }

    public List<Class<? extends AbstractEntity<?>>> domainTypes() {
       return Collections.unmodifiableList(domainTypes.stream().collect(Collectors.toList()));
    }

}
