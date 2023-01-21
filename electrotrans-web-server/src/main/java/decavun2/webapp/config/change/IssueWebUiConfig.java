<<<<<<< HEAD
package decavun2.webapp.config.objects;

import static java.lang.String.format;
import static metamodels.MetaModels.TransportCondition_;
=======
package decavun2.webapp.config.change;

import static java.lang.String.format;
import static metamodels.MetaModels.Issue_;
>>>>>>> 2c2e87e126651c335bebc6f60ce5c59d20f5c5ce
import static decavun2.common.StandardScrollingConfigs.standardStandaloneScrollingConfig;

import java.util.Optional;

import com.google.inject.Injector;

<<<<<<< HEAD
import decavun2.objects.TransportCondition;
import metamodels.MetaModels;
=======
import decavun2.change.Issue;
>>>>>>> 2c2e87e126651c335bebc6f60ce5c59d20f5c5ce
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
<<<<<<< HEAD
import decavun2.main.menu.objects.MiTransportCondition;
=======
import decavun2.main.menu.change.MiIssue;
import decavun2.personnel.Person;
>>>>>>> 2c2e87e126651c335bebc6f60ce5c59d20f5c5ce
import ua.com.fielden.platform.web.centre.EntityCentre;
import ua.com.fielden.platform.web.view.master.EntityMaster;
import static ua.com.fielden.platform.web.PrefDim.mkDim;
import ua.com.fielden.platform.web.PrefDim.Unit;

/**
<<<<<<< HEAD
 * {@link TransportCondition} Web UI configuration.
=======
 * {@link Issue} Web UI configuration.
>>>>>>> 2c2e87e126651c335bebc6f60ce5c59d20f5c5ce
 *
 * @author Developers
 *
 */
<<<<<<< HEAD
public class TransportConditionWebUiConfig {

    public final EntityCentre<TransportCondition> centre;
    public final EntityMaster<TransportCondition> master;

    public static TransportConditionWebUiConfig register(final Injector injector, final IWebUiBuilder builder) {
        return new TransportConditionWebUiConfig(injector, builder);
    }

    private TransportConditionWebUiConfig(final Injector injector, final IWebUiBuilder builder) {
=======
public class IssueWebUiConfig {

    public final EntityCentre<Issue> centre;
    public final EntityMaster<Issue> master;

    public static IssueWebUiConfig register(final Injector injector, final IWebUiBuilder builder) {
        return new IssueWebUiConfig(injector, builder);
    }

    private IssueWebUiConfig(final Injector injector, final IWebUiBuilder builder) {
>>>>>>> 2c2e87e126651c335bebc6f60ce5c59d20f5c5ce
        centre = createCentre(injector, builder);
        builder.register(centre);
        master = createMaster(injector);
        builder.register(master);
    }

    /**
<<<<<<< HEAD
     * Creates entity centre for {@link TransportCondition}.
=======
     * Creates entity centre for {@link Issue}.
>>>>>>> 2c2e87e126651c335bebc6f60ce5c59d20f5c5ce
     *
     * @param injector
     * @return created entity centre
     */
<<<<<<< HEAD
    private EntityCentre<TransportCondition> createCentre(final Injector injector, final IWebUiBuilder builder) {
        final String layout = LayoutComposer.mkVarGridForCentre(1, 1);

        final EntityActionConfig standardNewAction = StandardActions.NEW_ACTION.mkAction(TransportCondition.class);
        final EntityActionConfig standardDeleteAction = StandardActions.DELETE_ACTION.mkAction(TransportCondition.class);
        final EntityActionConfig standardExportAction = StandardActions.EXPORT_ACTION.mkAction(TransportCondition.class);
        final EntityActionConfig standardEditAction = StandardActions.EDIT_ACTION.mkAction(TransportCondition.class);
        final EntityActionConfig standardSortAction = CentreConfigActions.CUSTOMISE_COLUMNS_ACTION.mkAction();
        builder.registerOpenMasterAction(TransportCondition.class, standardEditAction);

        final EntityCentreConfig<TransportCondition> ecc = EntityCentreBuilder.centreFor(TransportCondition.class)
=======
    private EntityCentre<Issue> createCentre(final Injector injector, final IWebUiBuilder builder) {
        final String layout = LayoutComposer.mkVarGridForCentre(2, 2, 1);

        final EntityActionConfig standardNewAction = StandardActions.NEW_ACTION.mkAction(Issue.class);
        final EntityActionConfig standardDeleteAction = StandardActions.DELETE_ACTION.mkAction(Issue.class);
        final EntityActionConfig standardExportAction = StandardActions.EXPORT_ACTION.mkAction(Issue.class);
        final EntityActionConfig standardEditAction = StandardActions.EDIT_ACTION.mkAction(Issue.class);
        final EntityActionConfig standardSortAction = CentreConfigActions.CUSTOMISE_COLUMNS_ACTION.mkAction();
        builder.registerOpenMasterAction(Issue.class, standardEditAction);

        final EntityCentreConfig<Issue> ecc = EntityCentreBuilder.centreFor(Issue.class)
>>>>>>> 2c2e87e126651c335bebc6f60ce5c59d20f5c5ce
                //.runAutomatically()
                .addFrontAction(standardNewAction)
                .addTopAction(standardNewAction).also()
                .addTopAction(standardDeleteAction).also()
                .addTopAction(standardSortAction).also()
                .addTopAction(standardExportAction)
<<<<<<< HEAD
                .addCrit(TransportCondition_).asMulti().autocompleter(TransportCondition.class).also()
                .addCrit(TransportCondition_.stage()).asMulti().text()
=======
                .addCrit(Issue_).asMulti().autocompleter(Issue.class).also()
                .addCrit(Issue_.active()).asMulti().bool().also()
                .addCrit(Issue_.responsiblePerson()).asMulti().autocompleter(Person.class).also()
                .addCrit(Issue_.date()).asRange().dateTime().also()
                .addCrit(Issue_.description()).asMulti().text()
>>>>>>> 2c2e87e126651c335bebc6f60ce5c59d20f5c5ce
                .setLayoutFor(Device.DESKTOP, Optional.empty(), layout)
                .setLayoutFor(Device.TABLET, Optional.empty(), layout)
                .setLayoutFor(Device.MOBILE, Optional.empty(), layout)
                .withScrollingConfig(standardStandaloneScrollingConfig(0))
<<<<<<< HEAD
                .addProp(TransportCondition_).order(1).asc().width(110)
                    .withSummary("total_count_", "COUNT(SELF)", format("Count:The total number of matching %ss.", TransportCondition.ENTITY_TITLE))
                    .withAction(standardEditAction).also()
                .addProp(TransportCondition_.stage()).minWidth(100)
=======
                .addProp(Issue_).order(1).asc().minWidth(100)
                    .withSummary("total_count_", "COUNT(SELF)", format("Count:The total number of matching %ss.", Issue.ENTITY_TITLE))
                    .withAction(standardEditAction).also()
                .addProp(Issue_.description()).minWidth(100)
>>>>>>> 2c2e87e126651c335bebc6f60ce5c59d20f5c5ce
                //.addProp("prop").minWidth(100).withActionSupplier(builder.getOpenMasterAction(Entity.class)).also()
                .addPrimaryAction(standardEditAction)
                .build();

<<<<<<< HEAD
        return new EntityCentre<>(MiTransportCondition.class, ecc, injector);
    }

    /**
     * Creates entity master for {@link TransportCondition}.
=======
        return new EntityCentre<>(MiIssue.class, ecc, injector);
    }

    /**
     * Creates entity master for {@link Issue}.
>>>>>>> 2c2e87e126651c335bebc6f60ce5c59d20f5c5ce
     *
     * @param injector
     * @return created entity master
     */
<<<<<<< HEAD
    private EntityMaster<TransportCondition> createMaster(final Injector injector) {
        final String layout = LayoutComposer.mkVarGridForMasterFitWidth(1, 1);

        final IMaster<TransportCondition> masterConfig = new SimpleMasterBuilder<TransportCondition>().forEntity(TransportCondition.class)
                .addProp(TransportCondition_.conditionId()).asSinglelineText().also()
                .addProp(TransportCondition_.stage()).asSinglelineText().also()
=======
    private EntityMaster<Issue> createMaster(final Injector injector) {
        final String layout = LayoutComposer.mkVarGridForMasterFitWidth(2, 2, 1);

        final IMaster<Issue> masterConfig = (new SimpleMasterBuilder<Issue>().forEntity(Issue.class))
                .addProp(Issue_.issueNumber()).asSinglelineText().also()
                .addProp(Issue_.active()).asCheckbox().also()
                .addProp(Issue_.responsiblePerson()).asAutocompleter().also()
                .addProp(Issue_.date()).asDateTimePicker().also()
                .addProp(Issue_.description()).asMultilineText().also()
>>>>>>> 2c2e87e126651c335bebc6f60ce5c59d20f5c5ce
                .addAction(MasterActions.REFRESH).shortDesc("Cancel").longDesc("Cancel action")
                .addAction(MasterActions.SAVE)
                .setActionBarLayoutFor(Device.DESKTOP, Optional.empty(), LayoutComposer.mkActionLayoutForMaster())
                .setLayoutFor(Device.DESKTOP, Optional.empty(), layout)
                .setLayoutFor(Device.TABLET, Optional.empty(), layout)
                .setLayoutFor(Device.MOBILE, Optional.empty(), layout)
                .withDimensions(mkDim(LayoutComposer.SIMPLE_ONE_COLUMN_MASTER_DIM_WIDTH, 480, Unit.PX))
                .done();

<<<<<<< HEAD
        return new EntityMaster<>(TransportCondition.class, masterConfig, injector);
=======
        return new EntityMaster<>(Issue.class, masterConfig, injector);
>>>>>>> 2c2e87e126651c335bebc6f60ce5c59d20f5c5ce
    }
}