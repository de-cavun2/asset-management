package decavun2.security.tokens.compound_master_menu;

import static java.lang.String.format;

import decavun2.change.master.menu.actions.ChangeMaster_OpenIssue_MenuItem;
import decavun2.security.tokens.ChangeModuleToken;
import ua.com.fielden.platform.security.tokens.Template;

/**
 * A security token for entity {@link ChangeMaster_OpenIssue_MenuItem} to guard Access.
 *
 * @author Developers
 *
 */
public class ChangeMaster_OpenIssue_MenuItem_CanAccess_Token extends ChangeModuleToken {
    public final static String TITLE = format(Template.MASTER_MENU_ITEM_ACCESS.forTitle(), ChangeMaster_OpenIssue_MenuItem.ENTITY_TITLE);
    public final static String DESC = format(Template.MASTER_MENU_ITEM_ACCESS.forDesc(), ChangeMaster_OpenIssue_MenuItem.ENTITY_TITLE);
}