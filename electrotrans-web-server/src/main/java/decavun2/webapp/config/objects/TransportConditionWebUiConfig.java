package decavun2.webapp.config.objects;

import static java.lang.String.format;
import static metamodels.MetaModels.TransportCondition_;
import static decavun2.common.StandardScrollingConfigs.standardStandaloneScrollingConfig;

import java.util.Optional;

import com.google.inject.Injector;

import decavun2.objects.TransportCondition;
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
import decavun2.main.menu.objects.MiTransportCondition;
import ua.com.fielden.platform.web.centre.EntityCentre;
import ua.com.fielden.platform.web.view.master.EntityMaster;
import static ua.com.fielden.platform.web.PrefDim.mkDim;
import ua.com.fielden.platform.web.PrefDim.Unit;

/**
 * {@link TransportCondition} Web UI configuration.
 *
 * @author Developers
 *
 */
public class TransportConditionWebUiConfig {

    public final EntityCentre<TransportCondition> centre;
    public final EntityMaster<TransportCondition> master;

    public static TransportConditionWebUiConfig register(final Injector injector, final IWebUiBuilder builder) {
        return new TransportConditionWebUiConfig(injector, builder);
    }

    private TransportConditionWebUiConfig(final Injector injector, final IWebUiBuilder builder) {
        centre = createCentre(injector, builder);
        builder.register(centre);
        master = createMaster(injector);
        builder.register(master);
    }

    /**
     * Creates entity centre for {@link TransportCondition}.
     *
     * @param injector
     * @return created entity centre
     */
    private EntityCentre<TransportCondition> createCentre(final Injector injector, final IWebUiBuilder builder) {
        final String layout = LayoutComposer.mkGridForCentre(1, 1);

        final EntityActionConfig standardNewAction = StandardActions.NEW_ACTION.mkAction(TransportCondition.class);
        final EntityActionConfig standardDeleteAction = StandardActions.DELETE_ACTION.mkAction(TransportCondition.class);
        final EntityActionConfig standardExportAction = StandardActions.EXPORT_ACTION.mkAction(TransportCondition.class);
        final EntityActionConfig standardEditAction = StandardActions.EDIT_ACTION.mkAction(TransportCondition.class);
        final EntityActionConfig standardSortAction = CentreConfigActions.CUSTOMISE_COLUMNS_ACTION.mkAction();
        builder.registerOpenMasterAction(TransportCondition.class, standardEditAction);

        final EntityCentreConfig<TransportCondition> ecc = EntityCentreBuilder.centreFor(TransportCondition.class)
                //.runAutomatically()
                .addFrontAction(standardNewAction)
                .addTopAction(standardNewAction).also()
                .addTopAction(standardDeleteAction).also()
                .addTopAction(standardSortAction).also()
                .addTopAction(standardExportAction)
                .addCrit(TransportCondition_).asMulti().autocompleter(TransportCondition.class).also()
                .addCrit(TransportCondition_.stage()).asMulti().text()
                .setLayoutFor(Device.DESKTOP, Optional.empty(), layout)
                .setLayoutFor(Device.TABLET, Optional.empty(), layout)
                .setLayoutFor(Device.MOBILE, Optional.empty(), layout)
                .withScrollingConfig(standardStandaloneScrollingConfig(0))
                .addProp(TransportCondition_).order(1).asc().width(110)
                    .withSummary("total_count_", "COUNT(SELF)", format("Count:The total number of matching %ss.", TransportCondition.ENTITY_TITLE))
                    .withAction(standardEditAction).also()
                .addProp(TransportCondition_.stage()).minWidth(100)
                //.addProp("prop").minWidth(100).withActionSupplier(builder.getOpenMasterAction(Entity.class)).also()
                .addPrimaryAction(standardEditAction)
                .build();

        return new EntityCentre<>(MiTransportCondition.class, ecc, injector);
    }

    /**
     * Creates entity master for {@link TransportCondition}.
     *
     * @param injector
     * @return created entity master
     */
    private EntityMaster<TransportCondition> createMaster(final Injector injector) {
        final String layout = LayoutComposer.mkVarGridForMasterFitWidth(1, 1);

        final IMaster<TransportCondition> masterConfig = new SimpleMasterBuilder<TransportCondition>().forEntity(TransportCondition.class)
                .addProp(TransportCondition_.conditionId()).asSinglelineText().also()
                .addProp(TransportCondition_.stage()).asSinglelineText().also()
                .addAction(MasterActions.REFRESH).shortDesc("Cancel").longDesc("Cancel action")
                .addAction(MasterActions.SAVE)
                .setActionBarLayoutFor(Device.DESKTOP, Optional.empty(), LayoutComposer.mkActionLayoutForMaster())
                .setLayoutFor(Device.DESKTOP, Optional.empty(), layout)
                .setLayoutFor(Device.TABLET, Optional.empty(), layout)
                .setLayoutFor(Device.MOBILE, Optional.empty(), layout)
                .withDimensions(mkDim(LayoutComposer.SIMPLE_ONE_COLUMN_MASTER_DIM_WIDTH, 480, Unit.PX))
                .done();

        return new EntityMaster<>(TransportCondition.class, masterConfig, injector);
    }
}