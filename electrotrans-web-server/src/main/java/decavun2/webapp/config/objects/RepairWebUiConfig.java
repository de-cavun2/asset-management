package decavun2.webapp.config.objects;

import static java.lang.String.format;
import static metamodels.MetaModels.Repair_;
import static decavun2.common.StandardScrollingConfigs.standardStandaloneScrollingConfig;

import java.util.Optional;

import com.google.inject.Injector;

import decavun2.objects.Repair;
import decavun2.objects.Vehicle;
import decavun2.personnel.Person;
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
import decavun2.main.menu.objects.MiRepair;
import ua.com.fielden.platform.web.centre.EntityCentre;
import ua.com.fielden.platform.web.view.master.EntityMaster;
import static ua.com.fielden.platform.web.PrefDim.mkDim;
import ua.com.fielden.platform.web.PrefDim.Unit;

/**
 * {@link Repair} Web UI configuration.
 *
 * @author Developers
 *
 */
public class RepairWebUiConfig {

    public final EntityCentre<Repair> centre;
    public final EntityMaster<Repair> master;

    public static RepairWebUiConfig register(final Injector injector, final IWebUiBuilder builder) {
        return new RepairWebUiConfig(injector, builder);
    }

    private RepairWebUiConfig(final Injector injector, final IWebUiBuilder builder) {
        centre = createCentre(injector, builder);
        builder.register(centre);
        master = createMaster(injector);
        builder.register(master);
    }

    /**
     * Creates entity centre for {@link Repair}.
     *
     * @param injector
     * @return created entity centre
     */
    private EntityCentre<Repair> createCentre(final Injector injector, final IWebUiBuilder builder) {
        final String layout = LayoutComposer.mkVarGridForCentre(2, 2, 2);

        final EntityActionConfig standardNewAction = StandardActions.NEW_ACTION.mkAction(Repair.class);
        final EntityActionConfig standardDeleteAction = StandardActions.DELETE_ACTION.mkAction(Repair.class);
        final EntityActionConfig standardExportAction = StandardActions.EXPORT_ACTION.mkAction(Repair.class);
        final EntityActionConfig standardEditAction = StandardActions.EDIT_ACTION.mkAction(Repair.class);
        final EntityActionConfig standardSortAction = CentreConfigActions.CUSTOMISE_COLUMNS_ACTION.mkAction();
        builder.registerOpenMasterAction(Repair.class, standardEditAction);

        final EntityCentreConfig<Repair> ecc = EntityCentreBuilder.centreFor(Repair.class)
                //.runAutomatically()
                .addFrontAction(standardNewAction)
                .addTopAction(standardNewAction).also()
                .addTopAction(standardDeleteAction).also()
                .addTopAction(standardSortAction).also()
                .addTopAction(standardExportAction)
                //row1
                .addCrit(Repair_).asMulti().autocompleter(Repair.class).also()
                .addCrit(Repair_.desc()).asMulti().text().also()
                //row2
                .addCrit(Repair_.createdAt()).asRange().dateTime().also()
                .addCrit(Repair_.vehicle()).asMulti().autocompleter(Vehicle.class).also()
                //row3
                .addCrit(Repair_.issue()).asMulti().text().also()
                .addCrit(Repair_.repairman()).asMulti().autocompleter(Person.class)
                .setLayoutFor(Device.DESKTOP, Optional.empty(), layout)
                .setLayoutFor(Device.TABLET, Optional.empty(), layout)
                .setLayoutFor(Device.MOBILE, Optional.empty(), layout)
                .withScrollingConfig(standardStandaloneScrollingConfig(0))
                .addProp(Repair_).order(1).asc().minWidth(100)
                    .withSummary("total_count_", "COUNT(SELF)", format("Count:The total number of matching %ss.", Repair.ENTITY_TITLE))
                    .withAction(standardEditAction).also()
                .addProp(Repair_.desc()).minWidth(125).also()
                .addProp(Repair_.createdAt()).width(80).also()
                .addProp(Repair_.vehicle()).width(120).also()
                .addProp(Repair_.issue()).minWidth(150).also()
                .addProp(Repair_.repairman()).width(80)
                .addPrimaryAction(standardEditAction)
                .build();

        return new EntityCentre<>(MiRepair.class, ecc, injector);
    }

    /**
     * Creates entity master for {@link Repair}.
     *
     * @param injector
     * @return created entity master
     */
    private EntityMaster<Repair> createMaster(final Injector injector) {
        final String layout = LayoutComposer.mkVarGridForMasterFitWidth(2, 2, 2);

        final IMaster<Repair> masterConfig = new SimpleMasterBuilder<Repair>().forEntity(Repair.class)
                // row1
                .addProp(Repair_.repairID()).asSinglelineText().also()
                .addProp(Repair_.createdAt()).asDateTimePicker().also()
                // row2
                .addProp(Repair_.vehicle()).asSinglelineText().also()
                .addProp(Repair_.repairman()).asAutocompleter().also()
                // row3
                .addProp(Repair_.desc()).asSinglelineText().also()
                .addProp(Repair_.issue()).asSinglelineText().also()
                .addAction(MasterActions.REFRESH).shortDesc("Cancel").longDesc("Cancel action")
                .addAction(MasterActions.SAVE)
                .setActionBarLayoutFor(Device.DESKTOP, Optional.empty(), LayoutComposer.mkActionLayoutForMaster())
                .setLayoutFor(Device.DESKTOP, Optional.empty(), layout)
                .setLayoutFor(Device.TABLET, Optional.empty(), layout)
                .setLayoutFor(Device.MOBILE, Optional.empty(), layout)
                .withDimensions(mkDim(LayoutComposer.SIMPLE_ONE_COLUMN_MASTER_DIM_WIDTH, 480, Unit.PX))
                .done();

        return new EntityMaster<>(Repair.class, masterConfig, injector);
    }
}