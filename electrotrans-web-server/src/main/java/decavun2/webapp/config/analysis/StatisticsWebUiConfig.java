package decavun2.webapp.config.analysis;

import static java.lang.String.format;
import static metamodels.MetaModels.Statistics_;
import static decavun2.common.StandardScrollingConfigs.standardStandaloneScrollingConfig;

import java.util.Optional;

import com.google.inject.Injector;

import decavun2.analysis.Statistics;
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
import decavun2.main.menu.analysis.MiStatistics;
import decavun2.objects.Vehicle;
import metamodels.MetaModels;
import ua.com.fielden.platform.web.centre.EntityCentre;
import ua.com.fielden.platform.web.view.master.EntityMaster;
import static ua.com.fielden.platform.web.PrefDim.mkDim;
import ua.com.fielden.platform.web.PrefDim.Unit;

/**
 * {@link Statistics} Web UI configuration.
 *
 * @author Developers
 *
 */
public class StatisticsWebUiConfig {

    public final EntityCentre<Statistics> centre;
    public final EntityMaster<Statistics> master;

    public static StatisticsWebUiConfig register(final Injector injector, final IWebUiBuilder builder) {
        return new StatisticsWebUiConfig(injector, builder);
    }

    private StatisticsWebUiConfig(final Injector injector, final IWebUiBuilder builder) {
        centre = createCentre(injector, builder);
        builder.register(centre);
        master = createMaster(injector);
        builder.register(master);
    }

    /**
     * Creates entity centre for {@link Statistics}.
     *
     * @param injector
     * @return created entity centre
     */
    private EntityCentre<Statistics> createCentre(final Injector injector, final IWebUiBuilder builder) {
        final String layout = LayoutComposer.mkVarGridForCentre(2, 2);

        final EntityActionConfig standardNewAction = StandardActions.NEW_ACTION.mkAction(Statistics.class);
        final EntityActionConfig standardDeleteAction = StandardActions.DELETE_ACTION.mkAction(Statistics.class);
        final EntityActionConfig standardExportAction = StandardActions.EXPORT_ACTION.mkAction(Statistics.class);
        final EntityActionConfig standardEditAction = StandardActions.EDIT_ACTION.mkAction(Statistics.class);
        final EntityActionConfig standardSortAction = CentreConfigActions.CUSTOMISE_COLUMNS_ACTION.mkAction();
        builder.registerOpenMasterAction(Statistics.class, standardEditAction);

        final EntityCentreConfig<Statistics> ecc = EntityCentreBuilder.centreFor(Statistics.class)
                //.runAutomatically()
                .addFrontAction(standardNewAction)
                .addTopAction(standardNewAction).also()
                .addTopAction(standardDeleteAction).also()
                .addTopAction(standardSortAction).also()
                .addTopAction(standardExportAction)
                .addCrit(Statistics_.name()).asMulti().text().also()
                .addCrit(Statistics_.vehicle()).asMulti().autocompleter(Vehicle.class).also()
                .addCrit(Statistics_.startDate()).asRange().dateTime().also()
                .addCrit(Statistics_.endDate()).asRange().dateTime()         
                .setLayoutFor(Device.DESKTOP, Optional.empty(), layout)
                .setLayoutFor(Device.TABLET, Optional.empty(), layout)
                .setLayoutFor(Device.MOBILE, Optional.empty(), layout)
                .withScrollingConfig(standardStandaloneScrollingConfig(0))
                .addProp(Statistics_.name()).order(1).asc().minWidth(100)
                    .withSummary("total_count_", "COUNT(SELF)", format("Count:The total number of matching %ss.", Statistics.ENTITY_TITLE))
                    .withAction(standardEditAction).also()
                .addProp(Statistics_.vehicle()).minWidth(100).also()
                .addProp(Statistics_.repairsCount()).width(100).also()
                .addProp(Statistics_.issuesCount()).width(100).also()
                .addProp(Statistics_.startDate()).width(200).also()
                .addProp(Statistics_.endDate()).width(200)
                //.addProp("prop").minWidth(100).withActionSupplier(builder.getOpenMasterAction(Entity.class)).also()
                .addPrimaryAction(standardEditAction)
                .build();

        return new EntityCentre<>(MiStatistics.class, ecc, injector);
    }

    /**
     * Creates entity master for {@link Statistics}.
     *
     * @param injector
     * @return created entity master
     */
    private EntityMaster<Statistics> createMaster(final Injector injector) {
        final String layout = LayoutComposer.mkGridForMasterFitWidth(2, 2);

        final IMaster<Statistics> masterConfig = new SimpleMasterBuilder<Statistics>().forEntity(Statistics.class)
                .addProp(Statistics_.name()).asSinglelineText().also()
                .addProp(Statistics_.vehicle()).asAutocompleter().also()
                .addProp(Statistics_.startDate()).asDateTimePicker().also()
                .addProp(Statistics_.endDate()).asDateTimePicker().also()
                .addAction(MasterActions.REFRESH).shortDesc("Cancel").longDesc("Cancel action")
                .addAction(MasterActions.SAVE)
                .setActionBarLayoutFor(Device.DESKTOP, Optional.empty(), LayoutComposer.mkActionLayoutForMaster())
                .setLayoutFor(Device.DESKTOP, Optional.empty(), layout)
                .setLayoutFor(Device.TABLET, Optional.empty(), layout)
                .setLayoutFor(Device.MOBILE, Optional.empty(), layout)
                .withDimensions(mkDim(LayoutComposer.SIMPLE_ONE_COLUMN_MASTER_DIM_WIDTH, 480, Unit.PX))
                .done();

        return new EntityMaster<>(Statistics.class, masterConfig, injector);
    }
}