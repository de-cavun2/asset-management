package decavun2.webapp.config.object;

import static ua.com.fielden.platform.web.PrefDim.mkDim;
import static decavun2.common.StandardScrollingConfigs.standardEmbeddedScrollingConfig;
import static decavun2.common.StandardScrollingConfigs.standardStandaloneScrollingConfig;
import static decavun2.common.StandardActions.actionAddDesc;
import static decavun2.common.StandardActions.actionEditDesc;
import static java.lang.String.format;
import static metamodels.MetaModels.AssignedVehicle_;
import static metamodels.MetaModels.Route_;
import static ua.com.fielden.platform.dao.AbstractOpenCompoundMasterDao.enhanceEmbededCentreQuery;
import static ua.com.fielden.platform.entity_centre.review.DynamicQueryBuilder.createConditionProperty;

import java.util.Optional;

import com.google.inject.Injector;

import decavun2.object.Route;
import decavun2.object.AssignedVehicle;
import decavun2.main.menu.object.MiRouteMaster_AssignedVehicle;
import decavun2.object.master.menu.actions.RouteMaster_OpenAssignedVehicle_MenuItem;
import decavun2.object.ui_actions.OpenRouteMasterAction;
import decavun2.object.ui_actions.producers.OpenRouteMasterActionProducer;
import metamodels.MetaModels;
import decavun2.object.master.menu.actions.RouteMaster_OpenMain_MenuItem;
import decavun2.object.producers.AssignedVehicleProducer;
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
import decavun2.main.menu.object.MiRoute;
import ua.com.fielden.platform.web.view.master.EntityMaster;
/**
 * {@link Route} Web UI configuration.
 *
 * @author Developers
 *
 */
public class RouteWebUiConfig {

    private final Injector injector;

    public final EntityCentre<Route> centre;
    public final EntityMaster<Route> master;
    public final EntityMaster<OpenRouteMasterAction> compoundMaster;
    public final EntityActionConfig editRouteAction; // should be used on embedded centres instead of a standard EDIT action
    public final EntityActionConfig newRouteWithMasterAction; // should be used on embedded centres instead of a standrad NEW action
    private final EntityActionConfig newRouteAction;

    public static RouteWebUiConfig register(final Injector injector, final IWebUiBuilder builder) {
        return new RouteWebUiConfig(injector, builder);
    }

    private RouteWebUiConfig(final Injector injector, final IWebUiBuilder builder) {
        this.injector = injector;

        final PrefDim dims = mkDim(960, 640, Unit.PX);
        editRouteAction = Compound.openEdit(OpenRouteMasterAction.class, Route.ENTITY_TITLE, actionEditDesc(Route.ENTITY_TITLE), dims);
        newRouteWithMasterAction = Compound.openNewWithMaster(OpenRouteMasterAction.class, "add-circle-outline", Route.ENTITY_TITLE, actionAddDesc(Route.ENTITY_TITLE), dims);
        newRouteAction = Compound.openNew(OpenRouteMasterAction.class, "add-circle-outline", Route.ENTITY_TITLE, actionAddDesc(Route.ENTITY_TITLE), dims);
        builder.registerOpenMasterAction(Route.class, editRouteAction);

        centre = createRouteCentre(builder);
        builder.register(centre);

        master = createRouteMaster();
        builder.register(master);
        
        builder.register(createAssignedVehicleMaster(injector));

        compoundMaster = CompoundMasterBuilder.<Route, OpenRouteMasterAction>create(injector, builder)
            .forEntity(OpenRouteMasterAction.class)
            .withProducer(OpenRouteMasterActionProducer.class)
            .addMenuItem(RouteMaster_OpenMain_MenuItem.class)
                .icon("icons:picture-in-picture")
                .shortDesc(OpenRouteMasterAction.MAIN)
                .longDesc(Route.ENTITY_TITLE + " main")
                .withView(master)
            .also()
            .addMenuItem(RouteMaster_OpenAssignedVehicle_MenuItem.class)
                .icon("maps:directions-railway")
                .shortDesc(OpenRouteMasterAction.ASSIGNEDVEHICLES)
                .longDesc(Route.ENTITY_TITLE + " " + OpenRouteMasterAction.ASSIGNEDVEHICLES)
                .withView(createAssignedVehicleCentre())
            .done();
        builder.register(compoundMaster);
    }

    /**
     * Creates entity centre for {@link Route}.
     *
     * @return
     */
    private EntityCentre<Route> createRouteCentre(final IWebUiBuilder builder) {
        final String layout = LayoutComposer.mkVarGridForCentre(2, 2, 1, 1);
        final EntityActionConfig standardDeleteAction = StandardActions.DELETE_ACTION.mkAction(Route.class);
        final EntityActionConfig standardExportAction = StandardActions.EXPORT_ACTION.mkAction(Route.class);
        final EntityActionConfig standardSortAction = CentreConfigActions.CUSTOMISE_COLUMNS_ACTION.mkAction();

        final EntityCentreConfig<Route> ecc = EntityCentreBuilder.centreFor(Route.class)
                //.runAutomatically()
                .addFrontAction(newRouteAction)
                .addTopAction(newRouteAction).also()
                .addTopAction(standardDeleteAction).also()
                .addTopAction(standardSortAction).also()
                .addTopAction(standardExportAction)
                // row1
                .addCrit(Route_).asMulti().autocompleter(Route.class).also()
                .addCrit(Route_.inUse()).asMulti().bool().also()
                // row2
                .addCrit(Route_.name()).asMulti().text().also()
                .addCrit(Route_.stationOrder()).asMulti().text().also()
                // row3
                .addCrit(Route_.trafficData()).asMulti().text().also()
                // row4
                .addCrit(Route_.desc()).asMulti().text()
                .setLayoutFor(Device.DESKTOP, Optional.empty(), layout)
                .setLayoutFor(Device.TABLET, Optional.empty(), layout)
                .setLayoutFor(Device.MOBILE, Optional.empty(), layout)
                .withScrollingConfig(standardStandaloneScrollingConfig(0))
                .addProp(Route_).order(1).asc().width(130)
            		.withSummary("total_count_", "COUNT(SELF)", format("Count:The total number of matching %ss.", Route.ENTITY_TITLE))
            		.withAction(editRouteAction).also()
	            .addProp(Route_.name()).width(135).also()
	            .addProp(Route_.desc()).width(140).also()
	            .addProp(Route_.stationOrder()).minWidth(135).also()
	            .addProp(Route_.trafficData()).minWidth(135).also()
	            .addProp(Route_.inUse()).width(80)
                .addPrimaryAction(editRouteAction)
                .build();

        return new EntityCentre<>(MiRoute.class, ecc, injector);
    }

    /**
     * Creates entity master for {@link Route}.
     *
     * @return
     */
    private EntityMaster<Route> createRouteMaster() {
        final String layout = LayoutComposer.mkVarGridForMasterFitWidth(2, 2, 1, 1);

        final IMaster<Route> masterConfig = new SimpleMasterBuilder<Route>().forEntity(Route.class)
        		.addProp(Route_.routeNum()).asInteger().also()
                .addProp(Route_.inUse()).asCheckbox().also()
                .addProp(Route_.name()).asSinglelineText().also()
                .addProp(Route_.desc()).asMultilineText().also()
                .addProp(Route_.stationOrder()).asMultilineText().also()
                .addProp(Route_.trafficData()).asMultilineText().also()
                
                .addAction(MasterActions.REFRESH).shortDesc("Cancel").longDesc("Cancel action")
                .addAction(MasterActions.SAVE)
                .setActionBarLayoutFor(Device.DESKTOP, Optional.empty(), LayoutComposer.mkActionLayoutForMaster())
                .setLayoutFor(Device.DESKTOP, Optional.empty(), layout)
                .setLayoutFor(Device.TABLET, Optional.empty(), layout)
                .setLayoutFor(Device.MOBILE, Optional.empty(), layout)
                .done();

        return new EntityMaster<Route>(Route.class, masterConfig, injector);
    }

    /**
     * Creates entity master for {@link AssignedVehicle}.
     *
     * @param injector
     * @return created entity master
     */
    private EntityMaster<AssignedVehicle> createAssignedVehicleMaster(final Injector injector) {
        final String layout = LayoutComposer.mkVarGridForMasterFitWidth(2, 2, 2);

        final IMaster<AssignedVehicle> masterConfig = new SimpleMasterBuilder<AssignedVehicle>().forEntity(AssignedVehicle.class)
                .addProp(AssignedVehicle_.route()).asAutocompleter().also()
                .addProp(AssignedVehicle_.active()).asCheckbox().also()
                .addProp(AssignedVehicle_.vehicle()).asSinglelineText().also()
                .addProp(AssignedVehicle_.assignedDate()).asDateTimePicker().also()
                .addProp(AssignedVehicle_.stationSchedule()).asMultilineText().also()
                .addProp(AssignedVehicle_.interval()).asInteger().also()

                .addAction(MasterActions.REFRESH).shortDesc("Cancel").longDesc("Cancel action")
                .addAction(MasterActions.SAVE)
                .setActionBarLayoutFor(Device.DESKTOP, Optional.empty(), LayoutComposer.mkActionLayoutForMaster())
                .setLayoutFor(Device.DESKTOP, Optional.empty(), layout)
                .setLayoutFor(Device.TABLET, Optional.empty(), layout)
                .setLayoutFor(Device.MOBILE, Optional.empty(), layout)
                .withDimensions(mkDim(LayoutComposer.SIMPLE_ONE_COLUMN_MASTER_DIM_WIDTH, 480, Unit.PX))
                .done();

        return new EntityMaster<>(AssignedVehicle.class, AssignedVehicleProducer.class, masterConfig, injector);
    }
    
    private EntityCentre<AssignedVehicle> createAssignedVehicleCentre() {
        final Class<AssignedVehicle> root = AssignedVehicle.class;
        final String layout = LayoutComposer.mkVarGridForCentre(2, 2, 1);

        final EntityActionConfig standardEditAction = StandardActions.EDIT_ACTION.mkAction(AssignedVehicle.class);
        final EntityActionConfig standardNewAction = StandardActions.NEW_WITH_MASTER_ACTION.mkAction(AssignedVehicle.class);
        final EntityActionConfig standardDeleteAction = StandardActions.DELETE_ACTION.mkAction(AssignedVehicle.class);
        final EntityActionConfig standardExportAction = StandardActions.EXPORT_EMBEDDED_CENTRE_ACTION.mkAction(AssignedVehicle.class);
        final EntityActionConfig standardSortAction = CentreConfigActions.CUSTOMISE_COLUMNS_ACTION.mkAction();

        final EntityCentreConfig<AssignedVehicle> ecc = EntityCentreBuilder.centreFor(root)
                .runAutomatically()
                .addTopAction(standardNewAction).also()
                .addTopAction(standardDeleteAction).also()
                .addTopAction(standardSortAction).also()
                .addTopAction(standardExportAction)
                .addCrit(AssignedVehicle_.vehicle()).asMulti().text().also()
                .addCrit(AssignedVehicle_.assignedDate()).asRange().dateTime().also()
                .addCrit(AssignedVehicle_.interval()).asRange().integer().also()
                .addCrit(AssignedVehicle_.active()).asMulti().bool().also()
                .addCrit(AssignedVehicle_.stationSchedule()).asMulti().text()


                .setLayoutFor(Device.DESKTOP, Optional.empty(), layout)
                .setLayoutFor(Device.TABLET, Optional.empty(), layout)
                .setLayoutFor(Device.MOBILE, Optional.empty(), layout)
                .withScrollingConfig(standardEmbeddedScrollingConfig(0))
                .addProp(AssignedVehicle_.vehicle()).order(1).asc().minWidth(80)
                    .withSummary("total_count_", "COUNT(SELF)", format("Count:The total number of matching %ss.", AssignedVehicle.ENTITY_TITLE)).also()
                .addProp(AssignedVehicle_.assignedDate()).minWidth(80).also()
                .addProp(AssignedVehicle_.stationSchedule()).minWidth(140).also()
                .addProp(AssignedVehicle_.interval()).width(70).also()
                .addProp(AssignedVehicle_.active()).minWidth(80)

                .addPrimaryAction(standardEditAction)
                .setQueryEnhancer(RouteMaster_AssignedVehicleCentre_QueryEnhancer.class, context().withMasterEntity().build())
                .build();

        return new EntityCentre<>(MiRouteMaster_AssignedVehicle.class, ecc, injector);
    }

    private static class RouteMaster_AssignedVehicleCentre_QueryEnhancer implements IQueryEnhancer<AssignedVehicle> {
        @Override
        public ICompleted<AssignedVehicle> enhanceQuery(final IWhere0<AssignedVehicle> where, final Optional<CentreContext<AssignedVehicle, ?>> context) {
            return enhanceEmbededCentreQuery(where, createConditionProperty(AssignedVehicle_.route()), context.get().getMasterEntity().getKey());
        }
    }

}