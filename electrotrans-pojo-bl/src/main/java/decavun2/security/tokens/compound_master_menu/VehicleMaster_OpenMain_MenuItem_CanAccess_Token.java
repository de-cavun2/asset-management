package decavun2.security.tokens.compound_master_menu;

import static java.lang.String.format;

import decavun2.objects.master.menu.actions.VehicleMaster_OpenMain_MenuItem;
import decavun2.security.tokens.UsersAndPersonnelModuleToken;
import ua.com.fielden.platform.security.tokens.Template;

/**
 * A security token for entity {@link VehicleMaster_OpenMain_MenuItem} to guard Access.
 *
 * @author Developers
 *
 */
public class VehicleMaster_OpenMain_MenuItem_CanAccess_Token extends UsersAndPersonnelModuleToken {
    public final static String TITLE = format(Template.MASTER_MENU_ITEM_ACCESS.forTitle(), VehicleMaster_OpenMain_MenuItem.ENTITY_TITLE);
    public final static String DESC = format(Template.MASTER_MENU_ITEM_ACCESS.forDesc(), VehicleMaster_OpenMain_MenuItem.ENTITY_TITLE);
}