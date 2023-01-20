package decavun2.webapp;

import static ua.com.fielden.platform.reflection.TitlesDescsGetter.getEntityTitleAndDesc;

import org.apache.commons.lang3.StringUtils;

import decavun2.config.Modules;
import decavun2.config.personnel.PersonWebUiConfig;
import decavun2.objects.Vehicle;
import decavun2.personnel.Person;
import decavun2.personnel.PersonRole;
import decavun2.webapp.config.objects.VehicleWebUiConfig;
import decavun2.webapp.config.personnel.PersonRoleWebUiConfig;
import decavun2.objects.TransportCondition;
import decavun2.personnel.Person;
import decavun2.webapp.config.objects.TransportConditionWebUiConfig;
import decavun2.object.Route;
import decavun2.webapp.config.object.RouteWebUiConfig;
import ua.com.fielden.platform.basic.config.Workflows;
import ua.com.fielden.platform.entity.AbstractEntity;
import ua.com.fielden.platform.reflection.TitlesDescsGetter;
import ua.com.fielden.platform.utils.Pair;
import ua.com.fielden.platform.web.app.config.IWebUiBuilder;
import ua.com.fielden.platform.web.interfaces.ILayout.Device;
import ua.com.fielden.platform.web.resources.webui.AbstractWebUiConfig;
import ua.com.fielden.platform.web.resources.webui.SecurityMatrixWebUiConfig;
import ua.com.fielden.platform.web.resources.webui.UserRoleWebUiConfig;
import ua.com.fielden.platform.web.resources.webui.UserWebUiConfig;

/**
 * App-specific {@link IWebApp} implementation.
 *
 * @author Generated
 */
public class WebUiConfig extends AbstractWebUiConfig {

    public static final String WEB_TIME_WITH_MILLIS_FORMAT = "HH:mm:ss.SSS";
    public static final String WEB_TIME_FORMAT = "HH:mm";
    public static final String WEB_DATE_FORMAT_JS = "DD/MM/YYYY";
    public static final String WEB_DATE_FORMAT_JAVA = fromJsToJavaDateFormat(WEB_DATE_FORMAT_JS);

    private final String domainName;
    private final String path;
    private final int port;

    public WebUiConfig(final String domainName, final int port, final Workflows workflow, final String path) {
        super("Lviv Electrotrans Asset Managment", workflow, new String[] { "decavun2/" });
        if (StringUtils.isEmpty(domainName) || StringUtils.isEmpty(path)) {
            throw new IllegalArgumentException("Both the domain name and application binding path should be specified.");
        }
        this.domainName = domainName;
        this.port = port;
        this.path = path;
    }

    @Override
    public String getDomainName() {
        return domainName;
    }

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public int getPort() {
        return port;
    }

    /**
     * Configures the {@link WebUiConfig} with custom centres and masters.
     */
    @Override
    public void initConfiguration() {
        super.initConfiguration();

        final IWebUiBuilder builder = configApp();
        builder.setDateFormat(WEB_DATE_FORMAT_JS).setTimeFormat(WEB_TIME_FORMAT).setTimeWithMillisFormat(WEB_TIME_WITH_MILLIS_FORMAT)
        .setMinTabletWidth(600);

        // Users and Personnel Module
        final PersonWebUiConfig personWebUiConfig = PersonWebUiConfig.register(injector(), builder);
        final PersonRoleWebUiConfig personRoleWebUiConfig = PersonRoleWebUiConfig.register(injector(), builder);
        final VehicleWebUiConfig vehicleWebUiConfig = VehicleWebUiConfig.register(injector(), builder);
        final TransportConditionWebUiConfig transportConditionWebUiConfig = TransportConditionWebUiConfig.register(injector(), builder);
        final RouteWebUiConfig routeWebUiConfig = RouteWebUiConfig.register(injector(), builder);
        final UserWebUiConfig userWebUiConfig = UserWebUiConfig.register(injector(), builder);
        final UserRoleWebUiConfig userRoleWebUiConfig = UserRoleWebUiConfig.register(injector(), builder);
        final SecurityMatrixWebUiConfig securityConfig = SecurityMatrixWebUiConfig.register(injector(), configApp());

        // Add user-rated masters and centres to the configuration
        configApp()
        .addMaster(userWebUiConfig.master)
        .addMaster(userWebUiConfig.rolesUpdater)
        .addMaster(userRoleWebUiConfig.master)
        .addMaster(userRoleWebUiConfig.tokensUpdater)
        .addCentre(userWebUiConfig.centre)
        .addCentre(userRoleWebUiConfig.centre);

        // Configure application menu
        configDesktopMainMenu()
        .addModule(Modules.USERS_AND_PERSONNEL.title)
            .description(Modules.USERS_AND_PERSONNEL.desc)
            .icon(Modules.USERS_AND_PERSONNEL.icon)
            .detailIcon(Modules.USERS_AND_PERSONNEL.icon)
            .bgColor(Modules.USERS_AND_PERSONNEL.bgColour)
            .captionBgColor(Modules.USERS_AND_PERSONNEL.captionBgColour)
            .menu()
                .addMenuItem(mkMenuItemTitle(Person.class)).description(mkMenuItemDesc(Person.class)).centre(personWebUiConfig.centre).done()
                .addMenuItem(mkMenuItemTitle(PersonRole.class)).description(mkMenuItemDesc(PersonRole.class)).centre(personRoleWebUiConfig.centre).done()
                .addMenuItem(mkMenuItemTitle(Vehicle.class)).description(mkMenuItemDesc(Vehicle.class)).centre(vehicleWebUiConfig.centre).done()
                .addMenuItem(mkMenuItemTitle(TransportCondition.class)).description(mkMenuItemDesc(TransportCondition.class)).centre(transportConditionWebUiConfig.centre).done()
                .addMenuItem(mkMenuItemTitle(Route.class)).description(mkMenuItemDesc(Route.class)).centre(routeWebUiConfig.centre).done()

                .addMenuItem("System Users").description("Functionality for managing system users, authorisation, etc.")
                    .addMenuItem("Users").description("User centre").centre(userWebUiConfig.centre).done()
                    .addMenuItem("User Roles").description("User roles centre").centre(userRoleWebUiConfig.centre).done()
                    .addMenuItem("Security Matrix").description("Security Matrix is used to manage application authorisations for User Roles.").master(securityConfig.master).done()
                .done()
            .done().done()
        .setLayoutFor(Device.DESKTOP, null, "[[[]]]")
        .setLayoutFor(Device.TABLET, null, "[[[]]]")
        .setLayoutFor(Device.MOBILE, null, "[[[]]]")
        .minCellWidth(100).minCellHeight(148).done();
    }

    private static String fromJsToJavaDateFormat(final String dateFormatJs) {
        return dateFormatJs.replace("DD", "dd").replace("YYYY", "yyyy"); // UPPERCASE "Y" is "week year" in Java, therefore we prefer lowercase "y"
    }

    public static String mkMenuItemTitle(final Class<? extends AbstractEntity<?>> entityType) {
        return getEntityTitleAndDesc(entityType).getKey();
    }

    public static final String CENTRE_SUFFIX = " Centre";
    public static String mkMenuItemDesc(final Class<? extends AbstractEntity<?>> entityType) {
        final Pair<String, String> titleDesc = TitlesDescsGetter.getEntityTitleAndDesc(entityType);
        // Some @EntityTitle desc are not specified, while the others are worded as whole sentence ending with "." - use value in both cases
        return titleDesc.getValue().isEmpty() || titleDesc.getValue().endsWith(".") ? titleDesc.getKey() + CENTRE_SUFFIX : titleDesc.getValue() + CENTRE_SUFFIX;
    }

}
