package decavun2.security.tokens.compound_master_menu;

import static java.lang.String.format;

import ua.com.fielden.platform.reflection.TitlesDescsGetter;
import ua.com.fielden.platform.security.tokens.Template;
import decavun2.change.master.menu.actions.ChangeMaster_OpenReport_MenuItem;
import decavun2.security.tokens.UsersAndPersonnelModuleToken;

/**
 * A security token for entity {@link ChangeMaster_OpenReport_MenuItem} to guard Access.
 *
 * @author Developers
 *
 */
public class ChangeMaster_OpenReport_MenuItem_CanAccess_Token extends UsersAndPersonnelModuleToken {
    public final static String TITLE = format(Template.MASTER_MENU_ITEM_ACCESS.forTitle(), ChangeMaster_OpenReport_MenuItem.ENTITY_TITLE);
    public final static String DESC = format(Template.MASTER_MENU_ITEM_ACCESS.forDesc(), ChangeMaster_OpenReport_MenuItem.ENTITY_TITLE);
}