package decavun2.object.master.menu.actions;

import decavun2.object.Route;
import ua.com.fielden.platform.entity.AbstractFunctionalEntityForCompoundMenuItem;
import ua.com.fielden.platform.entity.annotation.KeyType;
import ua.com.fielden.platform.entity.annotation.EntityTitle;
import ua.com.fielden.platform.entity.annotation.CompanionObject;
import ua.com.fielden.platform.reflection.TitlesDescsGetter;
import ua.com.fielden.platform.utils.Pair;

/**
 * Master entity object to model the main menu item of the compound master entity object.
 *
 * @author Developers
 *
 */
@KeyType(Route.class)
@CompanionObject(RouteMaster_OpenMain_MenuItemCo.class)
@EntityTitle("Route Master Main Menu Item")
public class RouteMaster_OpenMain_MenuItem extends AbstractFunctionalEntityForCompoundMenuItem<Route> {

    private static final Pair<String, String> entityTitleAndDesc = TitlesDescsGetter.getEntityTitleAndDesc(RouteMaster_OpenMain_MenuItem.class);
    public static final String ENTITY_TITLE = entityTitleAndDesc.getKey();
    public static final String ENTITY_DESC = entityTitleAndDesc.getValue();

}
