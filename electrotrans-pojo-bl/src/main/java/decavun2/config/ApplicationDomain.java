package decavun2.config;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import decavun2.personnel.Person;
import ua.com.fielden.platform.basic.config.IApplicationDomainProvider;
import ua.com.fielden.platform.domain.PlatformDomainTypes;
import ua.com.fielden.platform.entity.AbstractEntity;
import decavun2.object.Route;
import decavun2.object.AssignedVehicle;
import decavun2.object.ui_actions.OpenRouteMasterAction;
import decavun2.object.master.menu.actions.RouteMaster_OpenMain_MenuItem;
import decavun2.object.master.menu.actions.RouteMaster_OpenAssignedVehicle_MenuItem;

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
		add(Route.class);
		add(AssignedVehicle.class);
		add(OpenRouteMasterAction.class);
		add(RouteMaster_OpenMain_MenuItem.class);
		add(RouteMaster_OpenAssignedVehicle_MenuItem.class);
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
