package decavun2.security.tokens.open_simple_master;

import static java.lang.String.format;

import decavun2.personnel.PersonRole;
import decavun2.security.tokens.UsersAndPersonnelModuleToken;
import ua.com.fielden.platform.security.tokens.Template;

/**
 * A security token for entity {@link PersonRole} to guard Open.
 *
 * @author Developers
 *
 */
public class PersonRoleMaster_CanOpen_Token extends UsersAndPersonnelModuleToken {
    public final static String TITLE = format(Template.MASTER_OPEN.forTitle(), PersonRole.ENTITY_TITLE + " Master");
    public final static String DESC = format(Template.MASTER_OPEN.forDesc(), PersonRole.ENTITY_TITLE);
}