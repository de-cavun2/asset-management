package decavun2.security.tokens.compound_master_menu;

import static java.lang.String.format;

import ua.com.fielden.platform.reflection.TitlesDescsGetter;
import ua.com.fielden.platform.security.tokens.Template;
import decavun2.object.master.menu.actions.RouteMaster_OpenMain_MenuItem;
import decavun2.security.tokens.UsersAndPersonnelModuleToken;

/**
 * A security token for entity {@link RouteMaster_OpenMain_MenuItem} to guard Access.
 *
 * @author Developers
 *
 */
public class RouteMaster_OpenMain_MenuItem_CanAccess_Token extends UsersAndPersonnelModuleToken {
    public final static String TITLE = format(Template.MASTER_MENU_ITEM_ACCESS.forTitle(), RouteMaster_OpenMain_MenuItem.ENTITY_TITLE);
    public final static String DESC = format(Template.MASTER_MENU_ITEM_ACCESS.forDesc(), RouteMaster_OpenMain_MenuItem.ENTITY_TITLE);
}