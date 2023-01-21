package decavun2.webapp.config.objects;

import static decavun2.common.StandardActions.actionAddDesc;
import static decavun2.common.StandardActions.actionEditDesc;
import static decavun2.common.StandardScrollingConfigs.standardStandaloneScrollingConfig;
import static java.lang.String.format;
import static metamodels.MetaModels.Person_;
import static metamodels.MetaModels.VehicleFinDet_;
import static metamodels.MetaModels.Vehicle_;
import static ua.com.fielden.platform.web.PrefDim.mkDim;

import java.util.Optional;

import com.google.inject.Injector;

import decavun2.common.LayoutComposer;
import decavun2.common.StandardActions;
import decavun2.main.menu.objects.MiVehicle;
import decavun2.objects.TransportCondition;
import decavun2.objects.Vehicle;
import decavun2.objects.VehicleFinDet;
import decavun2.objects.master.menu.actions.VehicleMaster_OpenMain_MenuItem;
import decavun2.objects.master.menu.actions.VehicleMaster_OpenVehicleFinDet_MenuItem;
import decavun2.objects.producers.VehicleFinDetProducer;
import decavun2.objects.ui_actions.OpenVehicleMasterAction;
import decavun2.objects.ui_actions.producers.OpenVehicleMasterActionProducer;
import decavun2.personnel.Person;
import ua.com.fielden.platform.web.PrefDim;
import ua.com.fielden.platform.web.PrefDim.Unit;
import ua.com.fielden.platform.web.action.CentreConfigurationWebUiConfig.CentreConfigActions;
import ua.com.fielden.platform.web.app.config.IWebUiBuilder;
import ua.com.fielden.platform.web.centre.EntityCentre;
import ua.com.fielden.platform.web.centre.api.EntityCentreConfig;
import ua.com.fielden.platform.web.centre.api.actions.EntityActionConfig;
import ua.com.fielden.platform.web.centre.api.impl.EntityCentreBuilder;
import ua.com.fielden.platform.web.interfaces.ILayout.Device;
import ua.com.fielden.platform.web.view.master.EntityMaster;
import ua.com.fielden.platform.web.view.master.api.IMaster;
import ua.com.fielden.platform.web.view.master.api.actions.MasterActions;
import ua.com.fielden.platform.web.view.master.api.compound.Compound;
import ua.com.fielden.platform.web.view.master.api.compound.impl.CompoundMasterBuilder;
import ua.com.fielden.platform.web.view.master.api.impl.SimpleMasterBuilder;
/**
 * {@link Vehicle} Web UI configuration.
 *
 * @author Developers
 *
 */
public class VehicleWebUiConfig {

    private final Injector injector;

    public final EntityCentre<Vehicle> centre;
    public final EntityMaster<Vehicle> master;
    public final EntityMaster<OpenVehicleMasterAction> compoundMaster;
    public final EntityActionConfig editVehicleAction; // should be used on embedded centres instead of a standard EDIT action
    public final EntityActionConfig newVehicleWithMasterAction; // should be used on embedded centres instead of a standrad NEW action
    private final EntityActionConfig newVehicleAction;

    public static VehicleWebUiConfig register(final Injector injector, final IWebUiBuilder builder) {
        return new VehicleWebUiConfig(injector, builder);
    }

    private VehicleWebUiConfig(final Injector injector, final IWebUiBuilder builder) {
        this.injector = injector;

        final PrefDim dims = mkDim(960, 640, Unit.PX);
        editVehicleAction = Compound.openEdit(OpenVehicleMasterAction.class, Vehicle.ENTITY_TITLE, actionEditDesc(Vehicle.ENTITY_TITLE), dims);
        newVehicleWithMasterAction = Compound.openNewWithMaster(OpenVehicleMasterAction.class, "add-circle-outline", Vehicle.ENTITY_TITLE, actionAddDesc(Vehicle.ENTITY_TITLE), dims);
        newVehicleAction = Compound.openNew(OpenVehicleMasterAction.class, "add-circle-outline", Vehicle.ENTITY_TITLE, actionAddDesc(Vehicle.ENTITY_TITLE), dims);
        builder.registerOpenMasterAction(Vehicle.class, editVehicleAction);

        centre = createVehicleCentre(builder);
        builder.register(centre);

        master = createVehicleMaster();
        builder.register(master);

        compoundMaster = CompoundMasterBuilder.<Vehicle, OpenVehicleMasterAction>create(injector, builder)
            .forEntity(OpenVehicleMasterAction.class)
            .withProducer(OpenVehicleMasterActionProducer.class)
            .addMenuItem(VehicleMaster_OpenMain_MenuItem.class)
                .icon("icons:chrome-reader-mode")
                .shortDesc(OpenVehicleMasterAction.MAIN)
                .longDesc(Vehicle.ENTITY_TITLE + " main")
                .withView(master)
            .also()
            .addMenuItem(VehicleMaster_OpenVehicleFinDet_MenuItem.class)
                .icon("editor:attach-money")
                .shortDesc(OpenVehicleMasterAction.VEHICLEFINDETS)
                .longDesc(Vehicle.ENTITY_TITLE + " " + OpenVehicleMasterAction.VEHICLEFINDETS)
                .withView(createVehicleFinDetMaster())
            .done();
        builder.register(compoundMaster);
    }

    /**
     * Creates entity centre for {@link Vehicle}.
     *
     * @return
     */
    private EntityCentre<Vehicle> createVehicleCentre(final IWebUiBuilder builder) {
        final String layout = LayoutComposer.mkVarGridForCentre(2, 2, 2, 2);
        final EntityActionConfig standardDeleteAction = StandardActions.DELETE_ACTION.mkAction(Vehicle.class);
        final EntityActionConfig standardExportAction = StandardActions.EXPORT_ACTION.mkAction(Vehicle.class);
        final EntityActionConfig standardSortAction = CentreConfigActions.CUSTOMISE_COLUMNS_ACTION.mkAction();

        final EntityCentreConfig<Vehicle> ecc = EntityCentreBuilder.centreFor(Vehicle.class)
                //.runAutomatically()
                .addFrontAction(newVehicleAction)
                .addTopAction(newVehicleAction).also()
                .addTopAction(standardDeleteAction).also()
                .addTopAction(standardSortAction).also()
                .addTopAction(standardExportAction)
                
                //row 1
                .addCrit(Vehicle_).asMulti().autocompleter(Vehicle.class).also()
                .addCrit(Vehicle_.desc()).asMulti().text().also()
                //row 2
                .addCrit(Vehicle_.model()).asMulti().text().also()
                .addCrit(Vehicle_.currentLocation()).asMulti().text().also()
                //row 3
                .addCrit(Vehicle_.driver()).asMulti().autocompleter(Person.class).also()
                .addCrit(Vehicle_.active()).asMulti().bool().also()
                //row 4
                .addCrit(Vehicle_.transportCondition()).asMulti().autocompleter(TransportCondition.class).also()
                .addCrit(Vehicle_.lastRepair()).asRange().dateTime()
                
                .setLayoutFor(Device.DESKTOP, Optional.empty(), layout)
                .setLayoutFor(Device.TABLET, Optional.empty(), layout)
                .setLayoutFor(Device.MOBILE, Optional.empty(), layout)
                .withScrollingConfig(standardStandaloneScrollingConfig(0))
                .addProp(Vehicle_).order(1).asc().width(125)
                    .withSummary("total_count_", "COUNT(SELF)", format("Count:The total number of matching %ss.", Vehicle.ENTITY_TITLE)).also()
                .addProp(Vehicle_.model()).width(115).also()
                .addProp(Vehicle_.driver()).width(120).also()
                .addProp(Vehicle_.desc()).minWidth(115).also()
                .addProp(Vehicle_.currentLocation()).minWidth(115).also()
                .addProp(Vehicle_.transportCondition()).width(150).also()
                .addProp(Vehicle_.lastRepair()).width(140).also()
                .addProp(Vehicle_.active()).width(80)
                .addPrimaryAction(editVehicleAction)
                .build();

        return new EntityCentre<>(MiVehicle.class, ecc, injector);
    }

    /**
     * Creates entity master for {@link Vehicle}.
     *
     * @return
     */
    private EntityMaster<Vehicle> createVehicleMaster() {
        final String layout = LayoutComposer.mkVarGridForMasterFitWidth(2, 2, 2, 1, 1);

        final IMaster<Vehicle> masterConfig = new SimpleMasterBuilder<Vehicle>().forEntity(Vehicle.class)
                
                // row 1
                .addProp(Vehicle_.licensePlate()).asSinglelineText().also()
                .addProp(Person_.active()).asCheckbox().also()
                // row 2
                .addProp(Vehicle_.model()).asSinglelineText().also()
                .addProp(Vehicle_.driver()).asAutocompleter().also()
                // row 3
                .addProp(Vehicle_.transportCondition()).asAutocompleter().also()
                .addProp(Vehicle_.lastRepair()).asDateTimePicker().also()
                // row 4
                .addProp(Vehicle_.currentLocation()).asMultilineText().also()
                // row 5
                .addProp(Vehicle_.desc()).asMultilineText().also()
                
                .addAction(MasterActions.REFRESH).shortDesc("Cancel").longDesc("Cancel action")
                .addAction(MasterActions.SAVE)
                .setActionBarLayoutFor(Device.DESKTOP, Optional.empty(), LayoutComposer.mkActionLayoutForMaster())
                .setLayoutFor(Device.DESKTOP, Optional.empty(), layout)
                .setLayoutFor(Device.TABLET, Optional.empty(), layout)
                .setLayoutFor(Device.MOBILE, Optional.empty(), layout)
                .done();

        return new EntityMaster<Vehicle>(Vehicle.class, masterConfig, injector);
    }

    private EntityMaster<VehicleFinDet> createVehicleFinDetMaster() {

        final String layout = LayoutComposer.mkVarGridForMasterFitWidth(1, 2, 2);

        final IMaster<VehicleFinDet> config = new SimpleMasterBuilder<VehicleFinDet>().forEntity(VehicleFinDet.class)
                // row 1
                .addProp(VehicleFinDet_.initCost()).asMoney().also()
                // row 2
                .addProp(VehicleFinDet_.commissionDate()).asDateTimePicker().also()
                .addProp(VehicleFinDet_.disposalDate()).asDateTimePicker().also()
                // row 3
                .addProp(VehicleFinDet_.tenderId()).asSinglelineText().also()
                .addProp(VehicleFinDet_.tenderDate()).asDateTimePicker().also()
                // entity actions
                .addAction(MasterActions.REFRESH).shortDesc("Cancel").longDesc("Cancels current changes if any or refresh the data")
                .addAction(MasterActions.SAVE)
                .setActionBarLayoutFor(Device.DESKTOP, Optional.empty(), LayoutComposer.mkActionLayoutForMaster())
                .setLayoutFor(Device.DESKTOP, Optional.empty(), layout)
                .setLayoutFor(Device.TABLET, Optional.empty(), layout)
                .setLayoutFor(Device.MOBILE, Optional.empty(), layout)
                .done();

        return new EntityMaster<VehicleFinDet>(
                VehicleFinDet.class,
                VehicleFinDetProducer.class,
                config,
                injector);
    }
}