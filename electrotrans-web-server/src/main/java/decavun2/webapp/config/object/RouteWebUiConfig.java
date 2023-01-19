package decavun2.webapp.config.object;

import static java.lang.String.format;
import static metamodels.MetaModels.Route_;
import static decavun2.common.StandardScrollingConfigs.standardStandaloneScrollingConfig;

import java.util.Optional;

import com.google.inject.Injector;

import decavun2.object.Route;
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
import decavun2.main.menu.object.MiRoute;
import ua.com.fielden.platform.web.centre.EntityCentre;
import ua.com.fielden.platform.web.view.master.EntityMaster;
import static ua.com.fielden.platform.web.PrefDim.mkDim;
import ua.com.fielden.platform.web.PrefDim.Unit;

/**
 * {@link Route} Web UI configuration.
 *
 * @author Developers
 *
 */
public class RouteWebUiConfig {

    public final EntityCentre<Route> centre;
    public final EntityMaster<Route> master;

    public static RouteWebUiConfig register(final Injector injector, final IWebUiBuilder builder) {
        return new RouteWebUiConfig(injector, builder);
    }

    private RouteWebUiConfig(final Injector injector, final IWebUiBuilder builder) {
        centre = createCentre(injector, builder);
        builder.register(centre);
        master = createMaster(injector);
        builder.register(master);
    }

    /**
     * Creates entity centre for {@link Route}.
     *
     * @param injector
     * @return created entity centre
     */
    private EntityCentre<Route> createCentre(final Injector injector, final IWebUiBuilder builder) {
        final String layout = LayoutComposer.mkVarGridForCentre(2, 2, 1, 1);

        final EntityActionConfig standardNewAction = StandardActions.NEW_ACTION.mkAction(Route.class);
        final EntityActionConfig standardDeleteAction = StandardActions.DELETE_ACTION.mkAction(Route.class);
        final EntityActionConfig standardExportAction = StandardActions.EXPORT_ACTION.mkAction(Route.class);
        final EntityActionConfig standardEditAction = StandardActions.EDIT_ACTION.mkAction(Route.class);
        final EntityActionConfig standardSortAction = CentreConfigActions.CUSTOMISE_COLUMNS_ACTION.mkAction();
        builder.registerOpenMasterAction(Route.class, standardEditAction);

        final EntityCentreConfig<Route> ecc = EntityCentreBuilder.centreFor(Route.class)
                //.runAutomatically()
                .addFrontAction(standardNewAction)
                .addTopAction(standardNewAction).also()
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
                .addProp(Route_).order(1).asc().width(135)
                    .withSummary("total_count_", "COUNT(SELF)", format("Count:The total number of matching %ss.", Route.ENTITY_TITLE))
                    .withAction(standardEditAction).also()
                .addProp(Route_.name()).width(135).also()
                .addProp(Route_.desc()).minWidth(135).also()
                .addProp(Route_.stationOrder()).minWidth(135).also()
                .addProp(Route_.trafficData()).minWidth(135).also()
                .addProp(Route_.inUse()).width(80)



                //.addProp("prop").minWidth(100).withActionSupplier(builder.getOpenMasterAction(Entity.class)).also()
                .addPrimaryAction(standardEditAction)
                .build();

        return new EntityCentre<>(MiRoute.class, ecc, injector);
    }

    /**
     * Creates entity master for {@link Route}.
     *
     * @param injector
     * @return created entity master
     */
    private EntityMaster<Route> createMaster(final Injector injector) {
        final String layout = LayoutComposer.mkVarGridForMasterFitWidth(2, 2, 1, 1);

        final IMaster<Route> masterConfig = new SimpleMasterBuilder<Route>().forEntity(Route.class)
                .addProp(Route_.routeNum()).asInteger().also()
                .addProp(Route_.inUse()).asCheckbox().also()
                .addProp(Route_.name()).asSinglelineText().also()
                .addProp(Route_.stationOrder()).asMultilineText().also()
                .addProp(Route_.trafficData()).asMultilineText().also()
                .addProp(Route_.desc()).asMultilineText().also()
                .addAction(MasterActions.REFRESH).shortDesc("Cancel").longDesc("Cancel action")
                .addAction(MasterActions.SAVE)
                .setActionBarLayoutFor(Device.DESKTOP, Optional.empty(), LayoutComposer.mkActionLayoutForMaster())
                .setLayoutFor(Device.DESKTOP, Optional.empty(), layout)
                .setLayoutFor(Device.TABLET, Optional.empty(), layout)
                .setLayoutFor(Device.MOBILE, Optional.empty(), layout)
                .withDimensions(mkDim(LayoutComposer.SIMPLE_ONE_COLUMN_MASTER_DIM_WIDTH, 480, Unit.PX))
                .done();

        return new EntityMaster<>(Route.class, masterConfig, injector);
    }
}