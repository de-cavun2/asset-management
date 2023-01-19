package decavun2.webapp.config.change;

import static ua.com.fielden.platform.web.PrefDim.mkDim;
import static decavun2.common.StandardScrollingConfigs.standardEmbeddedScrollingConfig;
import static decavun2.common.StandardScrollingConfigs.standardStandaloneScrollingConfig;
import static decavun2.common.StandardActions.actionAddDesc;
import static decavun2.common.StandardActions.actionEditDesc;
import static java.lang.String.format;
import static metamodels.MetaModels.Change_;
import static metamodels.MetaModels.Issue_;
import static metamodels.MetaModels.Report_;
import static ua.com.fielden.platform.dao.AbstractOpenCompoundMasterDao.enhanceEmbededCentreQuery;
import static ua.com.fielden.platform.entity_centre.review.DynamicQueryBuilder.createConditionProperty;

import java.util.Optional;

import com.google.inject.Injector;

import decavun2.change.Change;
import decavun2.change.Issue;
import decavun2.change.Report;
import decavun2.main.menu.change.MiChangeMaster_Issue;
import decavun2.main.menu.change.MiChangeMaster_Report;
import metamodels.MetaModels;
import decavun2.change.master.menu.actions.ChangeMaster_OpenIssue_MenuItem;
import decavun2.change.master.menu.actions.ChangeMaster_OpenReport_MenuItem;
import decavun2.change.ui_actions.OpenChangeMasterAction;
import decavun2.change.ui_actions.producers.OpenChangeMasterActionProducer;
import decavun2.change.master.menu.actions.ChangeMaster_OpenMain_MenuItem;
import ua.com.fielden.platform.web.interfaces.ILayout.Device;
import ua.com.fielden.platform.web.centre.api.EntityCentreConfig;
import ua.com.fielden.platform.web.centre.api.impl.EntityCentreBuilder;
import ua.com.fielden.platform.web.centre.api.actions.EntityActionConfig;
import ua.com.fielden.platform.web.view.master.api.actions.MasterActions;
import ua.com.fielden.platform.web.view.master.api.impl.SimpleMasterBuilder;
import ua.com.fielden.platform.web.view.master.api.compound.Compound;
import ua.com.fielden.platform.web.view.master.api.compound.impl.CompoundMasterBuilder;
import ua.com.fielden.platform.web.view.master.api.IMaster;
import ua.com.fielden.platform.web.app.config.IWebUiBuilder;
import ua.com.fielden.platform.web.PrefDim;
import ua.com.fielden.platform.web.PrefDim.Unit;
import decavun2.common.LayoutComposer;
import decavun2.common.StandardActions;
import ua.com.fielden.platform.web.centre.EntityCentre;
import ua.com.fielden.platform.web.action.CentreConfigurationWebUiConfig.CentreConfigActions;
import static ua.com.fielden.platform.web.centre.api.context.impl.EntityCentreContextSelector.context;

import ua.com.fielden.platform.web.centre.CentreContext;
import ua.com.fielden.platform.web.centre.IQueryEnhancer;
import ua.com.fielden.platform.entity.query.fluent.EntityQueryProgressiveInterfaces.ICompleted;
import ua.com.fielden.platform.entity.query.fluent.EntityQueryProgressiveInterfaces.IWhere0;
import decavun2.main.menu.change.MiChange;
import ua.com.fielden.platform.web.view.master.EntityMaster;
/**
 * {@link Change} Web UI configuration.
 *
 * @author Developers
 *
 */
public class ChangeWebUiConfig {

    private final Injector injector;

    public final EntityCentre<Change> centre;
    public final EntityMaster<Change> master;
    public final EntityMaster<OpenChangeMasterAction> compoundMaster;
    public final EntityActionConfig editChangeAction; // should be used on embedded centres instead of a standard EDIT action
    public final EntityActionConfig newChangeWithMasterAction; // should be used on embedded centres instead of a standrad NEW action
    private final EntityActionConfig newChangeAction;

    public static ChangeWebUiConfig register(final Injector injector, final IWebUiBuilder builder) {
        return new ChangeWebUiConfig(injector, builder);
    }

    private ChangeWebUiConfig(final Injector injector, final IWebUiBuilder builder) {
        this.injector = injector;

        final PrefDim dims = mkDim(960, 640, Unit.PX);
        editChangeAction = Compound.openEdit(OpenChangeMasterAction.class, Change.ENTITY_TITLE, actionEditDesc(Change.ENTITY_TITLE), dims);
        newChangeWithMasterAction = Compound.openNewWithMaster(OpenChangeMasterAction.class, "add-circle-outline", Change.ENTITY_TITLE, actionAddDesc(Change.ENTITY_TITLE), dims);
        newChangeAction = Compound.openNew(OpenChangeMasterAction.class, "add-circle-outline", Change.ENTITY_TITLE, actionAddDesc(Change.ENTITY_TITLE), dims);
        builder.registerOpenMasterAction(Change.class, editChangeAction);

        centre = createChangeCentre(builder);
        builder.register(centre);

        master = createChangeMaster();
        builder.register(master);

        compoundMaster = CompoundMasterBuilder.<Change, OpenChangeMasterAction>create(injector, builder)
            .forEntity(OpenChangeMasterAction.class)
            .withProducer(OpenChangeMasterActionProducer.class)
            .addMenuItem(ChangeMaster_OpenMain_MenuItem.class)
                .icon("icons:picture-in-picture")
                .shortDesc(OpenChangeMasterAction.MAIN)
                .longDesc(Change.ENTITY_TITLE + " main")
                .withView(master)
            .also()
            .addMenuItem(ChangeMaster_OpenIssue_MenuItem.class)
                .icon("icons:view-module")
                .shortDesc(OpenChangeMasterAction.ISSUES)
                .longDesc(Change.ENTITY_TITLE + " " + OpenChangeMasterAction.ISSUES)
                .withView(createIssueCentre())
            .also()
            .addMenuItem(ChangeMaster_OpenReport_MenuItem.class)
                .icon("icons:view-module")
                .shortDesc(OpenChangeMasterAction.REPORTS)
                .longDesc(Change.ENTITY_TITLE + " " + OpenChangeMasterAction.REPORTS)
                .withView(createReportCentre())
            .done();
        builder.register(compoundMaster);
    }

    /**
     * Creates entity centre for {@link Change}.
     *
     * @return
     */
    private EntityCentre<Change> createChangeCentre(final IWebUiBuilder builder) {
        final String layout = LayoutComposer.mkGridForCentre(1, 2);
        final EntityActionConfig standardDeleteAction = StandardActions.DELETE_ACTION.mkAction(Change.class);
        final EntityActionConfig standardExportAction = StandardActions.EXPORT_ACTION.mkAction(Change.class);
        final EntityActionConfig standardSortAction = CentreConfigActions.CUSTOMISE_COLUMNS_ACTION.mkAction();

        final EntityCentreConfig<Change> ecc = EntityCentreBuilder.centreFor(Change.class)
                //.runAutomatically()
                .addFrontAction(newChangeAction)
                .addTopAction(newChangeAction).also()
                .addTopAction(standardDeleteAction).also()
                .addTopAction(standardSortAction).also()
                .addTopAction(standardExportAction)
                .addCrit("this").asMulti().autocompleter(Change.class).also()
                .addCrit("desc").asMulti().text()
                .setLayoutFor(Device.DESKTOP, Optional.empty(), layout)
                .setLayoutFor(Device.TABLET, Optional.empty(), layout)
                .setLayoutFor(Device.MOBILE, Optional.empty(), layout)
                .withScrollingConfig(standardStandaloneScrollingConfig(0))
                .addProp("this").order(1).asc().minWidth(100)
                    .withSummary("total_count_", "COUNT(SELF)", format("Count:The total number of matching %ss.", Change.ENTITY_TITLE))
                    .withAction(editChangeAction).also()
                .addProp("desc").minWidth(300)
                .addPrimaryAction(editChangeAction)
                .build();

        return new EntityCentre<>(MiChange.class, ecc, injector);
    }

    /**
     * Creates entity master for {@link Change}.
     *
     * @return
     */
    private EntityMaster<Change> createChangeMaster() {
        final String layout = LayoutComposer.mkVarGridForMasterFitWidth(2, 1);

        final IMaster<Change> masterConfig = new SimpleMasterBuilder<Change>().forEntity(Change.class)
        		.addProp(Change_.changeId()).asSinglelineText().also()
                .addProp(Change_.name()).asSinglelineText().also()
                .addProp(Change_.desc()).asMultilineText().also()
                .addAction(MasterActions.REFRESH).shortDesc("Cancel").longDesc("Cancel action")
                .addAction(MasterActions.SAVE)
                .setActionBarLayoutFor(Device.DESKTOP, Optional.empty(), LayoutComposer.mkActionLayoutForMaster())
                .setLayoutFor(Device.DESKTOP, Optional.empty(), layout)
                .setLayoutFor(Device.TABLET, Optional.empty(), layout)
                .setLayoutFor(Device.MOBILE, Optional.empty(), layout)
                .done();

        return new EntityMaster<Change>(Change.class, masterConfig, injector);
    }

    private EntityCentre<Issue> createIssueCentre() {
        final Class<Issue> root = Issue.class;
        final String layout = LayoutComposer.mkVarGridForCentre(2, 1);
        
        final EntityActionConfig standardEditAction = StandardActions.EDIT_ACTION.mkAction(Issue.class);
        final EntityActionConfig standardNewAction = StandardActions.NEW_WITH_MASTER_ACTION.mkAction(Issue.class);
        final EntityActionConfig standardDeleteAction = StandardActions.DELETE_ACTION.mkAction(Issue.class);
        final EntityActionConfig standardExportAction = StandardActions.EXPORT_EMBEDDED_CENTRE_ACTION.mkAction(Issue.class);
        final EntityActionConfig standardSortAction = CentreConfigActions.CUSTOMISE_COLUMNS_ACTION.mkAction();

        final EntityCentreConfig<Issue> ecc = EntityCentreBuilder.centreFor(root)
                .runAutomatically()
                .addTopAction(standardNewAction).also()
                .addTopAction(standardDeleteAction).also()
                .addTopAction(standardSortAction).also()
                .addTopAction(standardExportAction)
                .addCrit(Issue_.name()).asMulti().text()
                .setLayoutFor(Device.DESKTOP, Optional.empty(), layout)
                .setLayoutFor(Device.TABLET, Optional.empty(), layout)
                .setLayoutFor(Device.MOBILE, Optional.empty(), layout)
                .withScrollingConfig(standardEmbeddedScrollingConfig(0))
                .addProp(Issue_.name()).order(1).asc().minWidth(80)
                    .withSummary("total_count_", "COUNT(SELF)", format("Count:The total number of matching %ss.", Issue.ENTITY_TITLE))
                .addPrimaryAction(standardEditAction)
                .setQueryEnhancer(ChangeMaster_IssueCentre_QueryEnhancer.class, context().withMasterEntity().build())
                .build();

        return new EntityCentre<>(MiChangeMaster_Issue.class, ecc, injector);
    }

    private static class ChangeMaster_IssueCentre_QueryEnhancer implements IQueryEnhancer<Issue> {
        @Override
        public ICompleted<Issue> enhanceQuery(final IWhere0<Issue> where, final Optional<CentreContext<Issue, ?>> context) {
            return enhanceEmbededCentreQuery(where, createConditionProperty("change"), context.get().getMasterEntity().getKey());
        }
    }

    private EntityCentre<Report> createReportCentre() {
        final Class<Report> root = Report.class;
        final String layout = LayoutComposer.mkVarGridForCentre(2, 1);

        final EntityActionConfig standardEditAction = StandardActions.EDIT_ACTION.mkAction(Report.class);
        final EntityActionConfig standardNewAction = StandardActions.NEW_WITH_MASTER_ACTION.mkAction(Report.class);
        final EntityActionConfig standardDeleteAction = StandardActions.DELETE_ACTION.mkAction(Report.class);
        final EntityActionConfig standardExportAction = StandardActions.EXPORT_EMBEDDED_CENTRE_ACTION.mkAction(Report.class);
        final EntityActionConfig standardSortAction = CentreConfigActions.CUSTOMISE_COLUMNS_ACTION.mkAction();

        final EntityCentreConfig<Report> ecc = EntityCentreBuilder.centreFor(root)
                .runAutomatically()
                .addTopAction(standardNewAction).also()
                .addTopAction(standardDeleteAction).also()
                .addTopAction(standardSortAction).also()
                .addTopAction(standardExportAction)
                .addCrit(Report_.title()).asMulti().text().also()
                .addCrit(Report_.department()).asMulti().text()
                .setLayoutFor(Device.DESKTOP, Optional.empty(), layout)
                .setLayoutFor(Device.TABLET, Optional.empty(), layout)
                .setLayoutFor(Device.MOBILE, Optional.empty(), layout)
                .withScrollingConfig(standardEmbeddedScrollingConfig(0))
                .addProp(Report_.title()).order(1).asc().minWidth(80)
                    .withSummary("total_count_", "COUNT(SELF)", format("Count:The total number of matching %ss.", Report.ENTITY_TITLE)).also()
                .addProp(Report_.desc()).minWidth(80)
                .addPrimaryAction(standardEditAction)
                .setQueryEnhancer(ChangeMaster_ReportCentre_QueryEnhancer.class, context().withMasterEntity().build())
                .build();

        return new EntityCentre<>(MiChangeMaster_Report.class, ecc, injector);
    }

    private static class ChangeMaster_ReportCentre_QueryEnhancer implements IQueryEnhancer<Report> {
        @Override
        public ICompleted<Report> enhanceQuery(final IWhere0<Report> where, final Optional<CentreContext<Report, ?>> context) {
            return enhanceEmbededCentreQuery(where, createConditionProperty("change"), context.get().getMasterEntity().getKey());
        }
    }

}