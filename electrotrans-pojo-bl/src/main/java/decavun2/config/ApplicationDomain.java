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
import decavun2.change.Report;
import decavun2.change.Issue;
import decavun2.change.Change;
import decavun2.change.ui_actions.OpenChangeMasterAction;
import decavun2.change.master.menu.actions.ChangeMaster_OpenMain_MenuItem;
import decavun2.change.master.menu.actions.ChangeMaster_OpenIssue_MenuItem;
import decavun2.change.master.menu.actions.ChangeMaster_OpenReport_MenuItem;
import decavun2.analysis.Statistics;

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
		add(Report.class);
		add(Issue.class);
		add(Change.class);
		add(OpenChangeMasterAction.class);
		add(ChangeMaster_OpenMain_MenuItem.class);
		add(ChangeMaster_OpenIssue_MenuItem.class);
		add(ChangeMaster_OpenReport_MenuItem.class);
		add(Statistics.class);
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
