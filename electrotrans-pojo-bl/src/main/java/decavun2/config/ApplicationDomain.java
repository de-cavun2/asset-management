package decavun2.config;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import decavun2.analysis.Statistics;
import decavun2.objects.DriverReport;
import decavun2.objects.Order;
import decavun2.objects.Repair;
import decavun2.objects.Vehicle;
import decavun2.personnel.Person;
import ua.com.fielden.platform.basic.config.IApplicationDomainProvider;
import ua.com.fielden.platform.domain.PlatformDomainTypes;
import ua.com.fielden.platform.entity.AbstractEntity;

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
		add(Vehicle.class);
		add(Statistics.class);
		add(DriverReport.class);
		add(Repair.class);
        add(Order.class);
        add(DriverReport.class);
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
