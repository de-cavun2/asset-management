package decavun2.webapp.config.change;

import static java.lang.String.format;
import static metamodels.MetaModels.Issue_;
import static decavun2.common.StandardScrollingConfigs.standardStandaloneScrollingConfig;

import java.util.Optional;

import com.google.inject.Injector;

import decavun2.change.Issue;
import decavun2.common.LayoutComposer;
import decavun2.common.StandardActions;

import ua.com.fielden.platform.web.interfaces.ILayout.Device;
import ua.com.fielden.platform.web.action.CentreConfigurationWebUiConfig.CentreConfigActions;
import ua.com.fielden.platform.web.centre.api.EntityCentreConfig;
import ua.com.fielden.platform.web.centre.api.actions.EntityActionConfig;
import ua.com.fielden.platform.web.centre.api.impl.EntityCentreBuilder;
import ua.com.fielden.platform.web.view.master.api.actions.MasterActions;
import ua.com.fielden.platform.web.view.master.api.impl.SimpleMasterBuilder;
import ua.com.fielden.platform.web.view.master.api.IMaster;
import ua.com.fielden.platform.web.app.config.IWebUiBuilder;
import decavun2.main.menu.change.MiIssue;
import decavun2.personnel.Person;
import ua.com.fielden.platform.web.centre.EntityCentre;
import ua.com.fielden.platform.web.view.master.EntityMaster;
import static ua.com.fielden.platform.web.PrefDim.mkDim;
import ua.com.fielden.platform.web.PrefDim.Unit;

/**
 * {@link Issue} Web UI configuration.
 *
 * @author Developers
 *
 */
public class IssueWebUiConfig {

    public final EntityCentre<Issue> centre;
    public final EntityMaster<Issue> master;

    public static IssueWebUiConfig register(final Injector injector, final IWebUiBuilder builder) {
        return new IssueWebUiConfig(injector, builder);
    }

    private IssueWebUiConfig(final Injector injector, final IWebUiBuilder builder) {
        centre = createCentre(injector, builder);
        builder.register(centre);
        master = createMaster(injector);
        builder.register(master);
    }

    /**
     * Creates entity centre for {@link Issue}.
     *
     * @param injector
     * @return created entity centre
     */
    private EntityCentre<Issue> createCentre(final Injector injector, final IWebUiBuilder builder) {
        final String layout = LayoutComposer.mkVarGridForCentre(2, 2, 1);

        final EntityActionConfig standardNewAction = StandardActions.NEW_ACTION.mkAction(Issue.class);
        final EntityActionConfig standardDeleteAction = StandardActions.DELETE_ACTION.mkAction(Issue.class);
        final EntityActionConfig standardExportAction = StandardActions.EXPORT_ACTION.mkAction(Issue.class);
        final EntityActionConfig standardEditAction = StandardActions.EDIT_ACTION.mkAction(Issue.class);
        final EntityActionConfig standardSortAction = CentreConfigActions.CUSTOMISE_COLUMNS_ACTION.mkAction();
        builder.registerOpenMasterAction(Issue.class, standardEditAction);

        final EntityCentreConfig<Issue> ecc = EntityCentreBuilder.centreFor(Issue.class)
                //.runAutomatically()
                .addFrontAction(standardNewAction)
                .addTopAction(standardNewAction).also()
                .addTopAction(standardDeleteAction).also()
                .addTopAction(standardSortAction).also()
                .addTopAction(standardExportAction)
                .addCrit(Issue_).asMulti().autocompleter(Issue.class).also()
                .addCrit(Issue_.active()).asMulti().bool().also()
                .addCrit(Issue_.responsiblePerson()).asMulti().autocompleter(Person.class).also()
                .addCrit(Issue_.date()).asRange().dateTime().also()
                .addCrit(Issue_.description()).asMulti().text()
                .setLayoutFor(Device.DESKTOP, Optional.empty(), layout)
                .setLayoutFor(Device.TABLET, Optional.empty(), layout)
                .setLayoutFor(Device.MOBILE, Optional.empty(), layout)
                .withScrollingConfig(standardStandaloneScrollingConfig(0))
                .addProp(Issue_).order(1).asc().minWidth(100)
                    .withSummary("total_count_", "COUNT(SELF)", format("Count:The total number of matching %ss.", Issue.ENTITY_TITLE))
                    .withAction(standardEditAction).also()
                .addProp(Issue_.description()).minWidth(100)
                //.addProp("prop").minWidth(100).withActionSupplier(builder.getOpenMasterAction(Entity.class)).also()
                .addPrimaryAction(standardEditAction)
                .build();

        return new EntityCentre<>(MiIssue.class, ecc, injector);
    }

    /**
     * Creates entity master for {@link Issue}.
     *
     * @param injector
     * @return created entity master
     */
    private EntityMaster<Issue> createMaster(final Injector injector) {
        final String layout = LayoutComposer.mkVarGridForMasterFitWidth(2, 2, 1);

        final IMaster<Issue> masterConfig = (new SimpleMasterBuilder<Issue>().forEntity(Issue.class))
                .addProp(Issue_.issueNumber()).asSinglelineText().also()
                .addProp(Issue_.active()).asCheckbox().also()
                .addProp(Issue_.responsiblePerson()).asAutocompleter().also()
                .addProp(Issue_.date()).asDateTimePicker().also()
                .addProp(Issue_.description()).asMultilineText().also()
                .addAction(MasterActions.REFRESH).shortDesc("Cancel").longDesc("Cancel action")
                .addAction(MasterActions.SAVE)
                .setActionBarLayoutFor(Device.DESKTOP, Optional.empty(), LayoutComposer.mkActionLayoutForMaster())
                .setLayoutFor(Device.DESKTOP, Optional.empty(), layout)
                .setLayoutFor(Device.TABLET, Optional.empty(), layout)
                .setLayoutFor(Device.MOBILE, Optional.empty(), layout)
                .withDimensions(mkDim(LayoutComposer.SIMPLE_ONE_COLUMN_MASTER_DIM_WIDTH, 480, Unit.PX))
                .done();

        return new EntityMaster<>(Issue.class, masterConfig, injector);
    }
}