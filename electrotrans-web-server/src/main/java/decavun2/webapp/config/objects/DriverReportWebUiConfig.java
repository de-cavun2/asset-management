package decavun2.webapp.config.objects;

import static java.lang.String.format;
import static metamodels.MetaModels.DriverReport_;
import static decavun2.common.StandardScrollingConfigs.standardStandaloneScrollingConfig;

import java.util.Optional;

import com.google.inject.Injector;

import decavun2.objects.DriverReport;
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
import decavun2.main.menu.objects.MiDriverReport;
import ua.com.fielden.platform.web.centre.EntityCentre;
import ua.com.fielden.platform.web.view.master.EntityMaster;
import static ua.com.fielden.platform.web.PrefDim.mkDim;
import ua.com.fielden.platform.web.PrefDim.Unit;

/**
 * {@link DriverReport} Web UI configuration.
 *
 * @author Developers
 *
 */
public class DriverReportWebUiConfig {

    public final EntityCentre<DriverReport> centre;
    public final EntityMaster<DriverReport> master;

    public static DriverReportWebUiConfig register(final Injector injector, final IWebUiBuilder builder) {
        return new DriverReportWebUiConfig(injector, builder);
    }

    private DriverReportWebUiConfig(final Injector injector, final IWebUiBuilder builder) {
        centre = createCentre(injector, builder);
        builder.register(centre);
        master = createMaster(injector);
        builder.register(master);
    }

    /**
     * Creates entity centre for {@link DriverReport}.
     *
     * @param injector
     * @return created entity centre
     */
    private EntityCentre<DriverReport> createCentre(final Injector injector, final IWebUiBuilder builder) {
        final String layout = LayoutComposer.mkVarGridForCentre(2, 2, 1);

        final EntityActionConfig standardNewAction = StandardActions.NEW_ACTION.mkAction(DriverReport.class);
        final EntityActionConfig standardDeleteAction = StandardActions.DELETE_ACTION.mkAction(DriverReport.class);
        final EntityActionConfig standardExportAction = StandardActions.EXPORT_ACTION.mkAction(DriverReport.class);
        final EntityActionConfig standardEditAction = StandardActions.EDIT_ACTION.mkAction(DriverReport.class);
        final EntityActionConfig standardSortAction = CentreConfigActions.CUSTOMISE_COLUMNS_ACTION.mkAction();
        builder.registerOpenMasterAction(DriverReport.class, standardEditAction);

        final EntityCentreConfig<DriverReport> ecc = EntityCentreBuilder.centreFor(DriverReport.class)
                //.runAutomatically()
                .addFrontAction(standardNewAction)
                .addTopAction(standardNewAction).also()
                .addTopAction(standardDeleteAction).also()
                .addTopAction(standardSortAction).also()
                .addTopAction(standardExportAction)
                //row1
                .addCrit(DriverReport_).asMulti().autocompleter(DriverReport.class).also()
                .addCrit(DriverReport_.desc()).asMulti().text().also()
                //row2
                .addCrit(DriverReport_.createdAt()).asRange().dateTime().also()
                .addCrit(DriverReport_.vehicle()).asMulti().autocompleter(Vehicle.class).also()
                //row3
                .addCrit(DriverReport_.state()).asMulti().text()
                .setLayoutFor(Device.DESKTOP, Optional.empty(), layout)
                .setLayoutFor(Device.TABLET, Optional.empty(), layout)
                .setLayoutFor(Device.MOBILE, Optional.empty(), layout)
                .withScrollingConfig(standardStandaloneScrollingConfig(0))
                .addProp(DriverReport_).order(1).asc().minWidth(100)
                    .withSummary("total_count_", "COUNT(SELF)", format("Count:The total number of matching %ss.", DriverReport.ENTITY_TITLE))
                    .withAction(standardEditAction).also()
                .addProp(DriverReport_.desc()).minWidth(125).also()
                .addProp(DriverReport_.createdAt()).width(80).also()
                .addProp(DriverReport_.vehicle()).width(120).also()
                .addProp(DriverReport_.state()).minWidth(150)
                .addPrimaryAction(standardEditAction)
                .build();

        return new EntityCentre<>(MiDriverReport.class, ecc, injector);
    }

    /**
     * Creates entity master for {@link DriverReport}.
     *
     * @param injector
     * @return created entity master
     */
    private EntityMaster<DriverReport> createMaster(final Injector injector) {
        final String layout = LayoutComposer.mkVarGridForMasterFitWidth(2, 2, 1);

        final IMaster<DriverReport> masterConfig = new SimpleMasterBuilder<DriverReport>().forEntity(DriverReport.class)
                // row1
                .addProp(DriverReport_.driverReportID()).asSinglelineText().also()
                .addProp(DriverReport_.createdAt()).asDateTimePicker().also()
                // row2
                .addProp(DriverReport_.vehicle()).asAutocompleter().also()
                // row3
                .addProp(DriverReport_.desc()).asSinglelineText().also()
                .addProp(DriverReport_.state()).asSinglelineText().also()
                .addAction(MasterActions.REFRESH).shortDesc("Cancel").longDesc("Cancel action")
                .addAction(MasterActions.SAVE)
                .setActionBarLayoutFor(Device.DESKTOP, Optional.empty(), LayoutComposer.mkActionLayoutForMaster())
                .setLayoutFor(Device.DESKTOP, Optional.empty(), layout)
                .setLayoutFor(Device.TABLET, Optional.empty(), layout)
                .setLayoutFor(Device.MOBILE, Optional.empty(), layout)
                .withDimensions(mkDim(LayoutComposer.SIMPLE_ONE_COLUMN_MASTER_DIM_WIDTH, 480, Unit.PX))
                .done();

        return new EntityMaster<>(DriverReport.class, masterConfig, injector);
    }
}