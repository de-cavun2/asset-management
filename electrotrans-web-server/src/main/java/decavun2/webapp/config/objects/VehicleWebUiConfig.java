package decavun2.webapp.config.objects;

import static java.lang.String.format;
import static metamodels.MetaModels.Person_;
import static metamodels.MetaModels.Vehicle_;
import static decavun2.common.StandardScrollingConfigs.standardStandaloneScrollingConfig;

import java.util.Optional;

import com.google.inject.Injector;

import decavun2.objects.Vehicle;
import decavun2.personnel.Person;
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
import decavun2.main.menu.objects.MiVehicle;
import ua.com.fielden.platform.web.centre.EntityCentre;
import ua.com.fielden.platform.web.view.master.EntityMaster;
import static ua.com.fielden.platform.web.PrefDim.mkDim;
import ua.com.fielden.platform.web.PrefDim.Unit;

/**
 * {@link Vehicle} Web UI configuration.
 *
 * @author Developers
 *
 */
public class VehicleWebUiConfig {

    public final EntityCentre<Vehicle> centre;
    public final EntityMaster<Vehicle> master;

    public static VehicleWebUiConfig register(final Injector injector, final IWebUiBuilder builder) {
        return new VehicleWebUiConfig(injector, builder);
    }

    private VehicleWebUiConfig(final Injector injector, final IWebUiBuilder builder) {
        centre = createCentre(injector, builder);
        builder.register(centre);
        master = createMaster(injector);
        builder.register(master);
    }

    /**
     * Creates entity centre for {@link Vehicle}.
     *
     * @param injector
     * @return created entity centre
     */
    private EntityCentre<Vehicle> createCentre(final Injector injector, final IWebUiBuilder builder) {
        final String layout = LayoutComposer.mkVarGridForCentre(2, 2, 2, 2);

        final EntityActionConfig standardNewAction = StandardActions.NEW_ACTION.mkAction(Vehicle.class);
        final EntityActionConfig standardDeleteAction = StandardActions.DELETE_ACTION.mkAction(Vehicle.class);
        final EntityActionConfig standardExportAction = StandardActions.EXPORT_ACTION.mkAction(Vehicle.class);
        final EntityActionConfig standardEditAction = StandardActions.EDIT_ACTION.mkAction(Vehicle.class);
        final EntityActionConfig standardSortAction = CentreConfigActions.CUSTOMISE_COLUMNS_ACTION.mkAction();
        builder.registerOpenMasterAction(Vehicle.class, standardEditAction);

        final EntityCentreConfig<Vehicle> ecc = EntityCentreBuilder.centreFor(Vehicle.class)
                //.runAutomatically()
                .addFrontAction(standardNewAction)
                .addTopAction(standardNewAction).also()
                .addTopAction(standardDeleteAction).also()
                .addTopAction(standardSortAction).also()
                .addTopAction(standardExportAction)
                //row1
                .addCrit(Vehicle_).asMulti().autocompleter(Vehicle.class).also()
                .addCrit(Vehicle_.desc()).asMulti().text().also()
                //row2
                .addCrit(Vehicle_.model()).asMulti().text().also()
                .addCrit(Vehicle_.currentLocation()).asMulti().text().also()
                //row3
                .addCrit(Vehicle_.driver()).asMulti().autocompleter(Person.class).also()
                .addCrit(Vehicle_.active()).asMulti().bool().also()
                //row4
                .addCrit(Vehicle_.transportCondition()).asMulti().text().also()
                .addCrit(Vehicle_.lastRepair()).asRange().dateTime()

                .setLayoutFor(Device.DESKTOP, Optional.empty(), layout)
                .setLayoutFor(Device.TABLET, Optional.empty(), layout)
                .setLayoutFor(Device.MOBILE, Optional.empty(), layout)
                .withScrollingConfig(standardStandaloneScrollingConfig(0))
                .addProp(Vehicle_).order(1).asc().width(125)
                    .withSummary("total_count_", "COUNT(SELF)", format("Count:The total number of matching %ss.", Vehicle.ENTITY_TITLE))
                    .withAction(standardEditAction).also()
                .addProp(Vehicle_.model()).width(115).also()
                .addProp(Vehicle_.driver()).width(120).also()
                .addProp(Vehicle_.desc()).minWidth(115).also()
                .addProp(Vehicle_.currentLocation()).minWidth(115).also()
                .addProp(Vehicle_.transportCondition()).width(150).also()
                .addProp(Vehicle_.lastRepair()).width(140).also()
                .addProp(Vehicle_.active()).width(80)
                //.addProp("prop").minWidth(100).withActionSupplier(builder.getOpenMasterAction(Entity.class)).also()
                .addPrimaryAction(standardEditAction)
                .build();

        return new EntityCentre<>(MiVehicle.class, ecc, injector);
    }

    /**
     * Creates entity master for {@link Vehicle}.
     *
     * @param injector
     * @return created entity master
     */
    private EntityMaster<Vehicle> createMaster(final Injector injector) {
        final String layout = LayoutComposer.mkVarGridForMasterFitWidth(2, 2, 2, 1, 1);

        final IMaster<Vehicle> masterConfig = new SimpleMasterBuilder<Vehicle>().forEntity(Vehicle.class)
                // row1
                .addProp(Vehicle_.licensePlate()).asSinglelineText().also()
                .addProp(Person_.active()).asCheckbox().also()
                // row2
                .addProp(Vehicle_.model()).asSinglelineText().also()
                .addProp(Vehicle_.driver()).asAutocompleter().also()
                // row3
                .addProp(Vehicle_.transportCondition()).asSinglelineText().also()
                .addProp(Vehicle_.lastRepair()).asDateTimePicker().also()
                // row4
                .addProp(Vehicle_.currentLocation()).asMultilineText().also()
                // row5
                .addProp(Vehicle_.desc()).asMultilineText().also()
                
                .addAction(MasterActions.REFRESH).shortDesc("Cancel").longDesc("Cancel action")
                .addAction(MasterActions.SAVE)
                .setActionBarLayoutFor(Device.DESKTOP, Optional.empty(), LayoutComposer.mkActionLayoutForMaster())
                .setLayoutFor(Device.DESKTOP, Optional.empty(), layout)
                .setLayoutFor(Device.TABLET, Optional.empty(), layout)
                .setLayoutFor(Device.MOBILE, Optional.empty(), layout)
                .withDimensions(mkDim(LayoutComposer.SIMPLE_ONE_COLUMN_MASTER_DIM_WIDTH, 480, Unit.PX))
                .done();

        return new EntityMaster<>(Vehicle.class, masterConfig, injector);
    }
}