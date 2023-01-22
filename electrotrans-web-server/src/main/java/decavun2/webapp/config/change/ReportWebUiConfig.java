package decavun2.webapp.config.change;

import static java.lang.String.format;
import static metamodels.MetaModels.Report_;
import static decavun2.common.StandardScrollingConfigs.standardStandaloneScrollingConfig;

import java.util.Optional;

import com.google.inject.Injector;

import decavun2.change.Change;
import decavun2.change.Report;
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
import decavun2.main.menu.change.MiReport;
import decavun2.personnel.Person;
import metamodels.MetaModels;
import ua.com.fielden.platform.web.centre.EntityCentre;
import ua.com.fielden.platform.web.view.master.EntityMaster;
import static ua.com.fielden.platform.web.PrefDim.mkDim;
import ua.com.fielden.platform.web.PrefDim.Unit;

/**
 * {@link Report} Web UI configuration.
 *
 * @author Stefan Malyk
 *
 */
public class ReportWebUiConfig {

    public final EntityCentre<Report> centre;
    public final EntityMaster<Report> master;

    public static ReportWebUiConfig register(final Injector injector, final IWebUiBuilder builder) {
        return new ReportWebUiConfig(injector, builder);
    }

    private ReportWebUiConfig(final Injector injector, final IWebUiBuilder builder) {
        centre = createCentre(injector, builder);
        builder.register(centre);
        master = createMaster(injector);
        builder.register(master);
    }

    /**
     * Creates entity centre for {@link Report}.
     *
     * @param injector
     * @return created entity centre
     */
    private EntityCentre<Report> createCentre(final Injector injector, final IWebUiBuilder builder) {
        final String layout = LayoutComposer.mkVarGridForCentre(2, 3);

        final EntityActionConfig standardNewAction = StandardActions.NEW_ACTION.mkAction(Report.class);
        final EntityActionConfig standardDeleteAction = StandardActions.DELETE_ACTION.mkAction(Report.class);
        final EntityActionConfig standardExportAction = StandardActions.EXPORT_ACTION.mkAction(Report.class);
        final EntityActionConfig standardEditAction = StandardActions.EDIT_ACTION.mkAction(Report.class);
        final EntityActionConfig standardSortAction = CentreConfigActions.CUSTOMISE_COLUMNS_ACTION.mkAction();
        builder.registerOpenMasterAction(Report.class, standardEditAction);

        final EntityCentreConfig<Report> ecc = EntityCentreBuilder.centreFor(Report.class)
                //.runAutomatically()
                .addFrontAction(standardNewAction)
                .addTopAction(standardNewAction).also()
                .addTopAction(standardDeleteAction).also()
                .addTopAction(standardSortAction).also()
                .addTopAction(standardExportAction)
                .addCrit(Report_).asMulti().autocompleter(Report.class).also()
                .addCrit(Report_.department()).asMulti().text().also()
                .addCrit(Report_.issue()).asMulti().text().also()
                .addCrit(Report_.change()).asMulti().autocompleter(Change.class).also()
                .addCrit(Report_.person()).asMulti().autocompleter(Person.class)
                .setLayoutFor(Device.DESKTOP, Optional.empty(), layout)
                .setLayoutFor(Device.TABLET, Optional.empty(), layout)
                .setLayoutFor(Device.MOBILE, Optional.empty(), layout)
                .withScrollingConfig(standardStandaloneScrollingConfig(0))
                .addProp(Report_).order(1).asc().minWidth(100)
                    .withSummary("total_count_", "COUNT(SELF)", format("Count:The total number of matching %ss.", Report.ENTITY_TITLE))
                    .withAction(standardEditAction).also()
                .addProp(Report_.department()).width(200).also()
                .addProp(Report_.createdAt()).width(200).also()
                .addProp(Report_.person()).width(200)
                .addPrimaryAction(standardEditAction)
                .build();

        return new EntityCentre<>(MiReport.class, ecc, injector);
    }

    /**
     * Creates entity master for {@link Report}.
     *
     * @param injector
     * @return created entity master
     */
    private EntityMaster<Report> createMaster(final Injector injector) {
        final String layout = LayoutComposer.mkVarGridForMasterFitWidth(1,1,2, 2);

        final IMaster<Report> masterConfig = new SimpleMasterBuilder<Report>().forEntity(Report.class)
                .addProp(Report_.title()).asSinglelineText().also()
                .addProp(Report_.desc()).asMultilineText().also()
                .addProp(Report_.department()).asSinglelineText().also()
                .addProp(Report_.person()).asAutocompleter().also()
                .addProp(Report_.issue()).asSinglelineText().also()
                .addProp(Report_.change()).asAutocompleter().also()
                .addAction(MasterActions.REFRESH).shortDesc("Cancel").longDesc("Cancel action")
                .addAction(MasterActions.SAVE)
                .setActionBarLayoutFor(Device.DESKTOP, Optional.empty(), LayoutComposer.mkActionLayoutForMaster())
                .setLayoutFor(Device.DESKTOP, Optional.empty(), layout)
                .setLayoutFor(Device.TABLET, Optional.empty(), layout)
                .setLayoutFor(Device.MOBILE, Optional.empty(), layout)
                .withDimensions(mkDim(LayoutComposer.SIMPLE_ONE_COLUMN_MASTER_DIM_WIDTH, 480, Unit.PX))
                .done();

        return new EntityMaster<>(Report.class, masterConfig, injector);
    }
}