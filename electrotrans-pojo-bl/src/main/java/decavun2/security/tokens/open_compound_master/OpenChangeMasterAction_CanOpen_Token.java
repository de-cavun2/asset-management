package decavun2.security.tokens.open_compound_master;

import static java.lang.String.format;

import ua.com.fielden.platform.reflection.TitlesDescsGetter;
import ua.com.fielden.platform.security.tokens.Template;
import decavun2.change.ui_actions.OpenChangeMasterAction;
import decavun2.security.tokens.UsersAndPersonnelModuleToken;

/**
 * A security token for entity {@link OpenChangeMasterAction} to guard Open.
 *
 * @author Developers
 *
 */
public class OpenChangeMasterAction_CanOpen_Token extends UsersAndPersonnelModuleToken {
    public final static String TITLE = format(Template.MASTER_OPEN.forTitle(), OpenChangeMasterAction.ENTITY_TITLE);
    public final static String DESC = format(Template.MASTER_OPEN.forDesc(), OpenChangeMasterAction.ENTITY_TITLE);
}