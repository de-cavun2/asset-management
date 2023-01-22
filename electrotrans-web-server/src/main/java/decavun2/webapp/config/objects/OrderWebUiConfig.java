package decavun2.webapp.config.objects;

import static java.lang.String.format;
import static metamodels.MetaModels.Order_;
import static decavun2.common.StandardScrollingConfigs.standardStandaloneScrollingConfig;

import java.util.Optional;

import com.google.inject.Injector;

import decavun2.objects.Order;
import metamodels.MetaModels;
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
import decavun2.main.menu.objects.MiOrder;
import ua.com.fielden.platform.web.centre.EntityCentre;
import ua.com.fielden.platform.web.view.master.EntityMaster;
import static ua.com.fielden.platform.web.PrefDim.mkDim;
import ua.com.fielden.platform.web.PrefDim.Unit;

/**
 * {@link Order} Web UI configuration.
 *
 * @author Developers
 *
 */
public class OrderWebUiConfig {

    public final EntityCentre<Order> centre;
    public final EntityMaster<Order> master;

    public static OrderWebUiConfig register(final Injector injector, final IWebUiBuilder builder) {
        return new OrderWebUiConfig(injector, builder);
    }

    private OrderWebUiConfig(final Injector injector, final IWebUiBuilder builder) {
        centre = createCentre(injector, builder);
        builder.register(centre);
        master = createMaster(injector);
        builder.register(master);
    }

    /**
     * Creates entity centre for {@link Order}.
     *
     * @param injector
     * @return created entity centre
     */
    private EntityCentre<Order> createCentre(final Injector injector, final IWebUiBuilder builder) {
        final String layout = LayoutComposer.mkVarGridForCentre(2, 3);

        final EntityActionConfig standardNewAction = StandardActions.NEW_ACTION.mkAction(Order.class);
        final EntityActionConfig standardDeleteAction = StandardActions.DELETE_ACTION.mkAction(Order.class);
        final EntityActionConfig standardExportAction = StandardActions.EXPORT_ACTION.mkAction(Order.class);
        final EntityActionConfig standardEditAction = StandardActions.EDIT_ACTION.mkAction(Order.class);
        final EntityActionConfig standardSortAction = CentreConfigActions.CUSTOMISE_COLUMNS_ACTION.mkAction();
        builder.registerOpenMasterAction(Order.class, standardEditAction);

        final EntityCentreConfig<Order> ecc = EntityCentreBuilder.centreFor(Order.class)
                //.runAutomatically()
                .addFrontAction(standardNewAction)
                .addTopAction(standardNewAction).also()
                .addTopAction(standardDeleteAction).also()
                .addTopAction(standardSortAction).also()
                .addTopAction(standardExportAction)
                //row1
                .addCrit(Order_).asMulti().autocompleter(Order.class).also()
                .addCrit(Order_.desc()).asMulti().text().also()
                //row2
                .addCrit(Order_.date()).asRange().dateTime().also()
                .addCrit(Order_.parts()).asMulti().text().also()
                .addCrit(Order_.price()).asMulti().text()
                .setLayoutFor(Device.DESKTOP, Optional.empty(), layout)
                .setLayoutFor(Device.TABLET, Optional.empty(), layout)
                .setLayoutFor(Device.MOBILE, Optional.empty(), layout)
                .withScrollingConfig(standardStandaloneScrollingConfig(0))
                .addProp(Order_).order(1).asc().minWidth(100)
                    .withSummary("total_count_", "COUNT(SELF)", format("Count:The total number of matching %ss.", Order.ENTITY_TITLE))
                    .withAction(standardEditAction).also()
                .addProp(Order_.desc()).minWidth(125).also()
                .addProp(Order_.date()).width(80).also()
                .addProp(Order_.parts()).width(120).also()
                .addProp(Order_.price()).width(80)
                //.addProp("prop").minWidth(100).withActionSupplier(builder.getOpenMasterAction(Entity.class)).also()
                .addPrimaryAction(standardEditAction)
                .build();

        return new EntityCentre<>(MiOrder.class, ecc, injector);
    }

    /**
     * Creates entity master for {@link Order}.
     *
     * @param injector
     * @return created entity master
     */
    private EntityMaster<Order> createMaster(final Injector injector) {
        final String layout = LayoutComposer.mkVarGridForMasterFitWidth(2, 3);

        final IMaster<Order> masterConfig = new SimpleMasterBuilder<Order>().forEntity(Order.class)
                // row1
                .addProp(Order_.orderID()).asSinglelineText().also()
                .addProp(Order_.date()).asDateTimePicker().also()
                // row2
                .addProp(Order_.parts()).asSinglelineText().also()
                .addProp(Order_.desc()).asSinglelineText().also()
                .addProp(Order_.price()).asMoney().also()
                .addAction(MasterActions.REFRESH).shortDesc("Cancel").longDesc("Cancel action")
                .addAction(MasterActions.SAVE)
                .setActionBarLayoutFor(Device.DESKTOP, Optional.empty(), LayoutComposer.mkActionLayoutForMaster())
                .setLayoutFor(Device.DESKTOP, Optional.empty(), layout)
                .setLayoutFor(Device.TABLET, Optional.empty(), layout)
                .setLayoutFor(Device.MOBILE, Optional.empty(), layout)
                .withDimensions(mkDim(LayoutComposer.SIMPLE_ONE_COLUMN_MASTER_DIM_WIDTH, 480, Unit.PX))
                .done();

        return new EntityMaster<>(Order.class, masterConfig, injector);
    }
}