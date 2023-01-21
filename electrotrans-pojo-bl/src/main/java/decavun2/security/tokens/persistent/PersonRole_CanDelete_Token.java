package decavun2.security.tokens.persistent;

import static java.lang.String.format;

import decavun2.personnel.PersonRole;
import decavun2.security.tokens.UsersAndPersonnelModuleToken;
import ua.com.fielden.platform.security.tokens.Template;

/**
 * A security token for entity {@link PersonRole} to guard Delete.
 *
 * @author Developers
 *
 */
public class PersonRole_CanDelete_Token extends UsersAndPersonnelModuleToken {
    public final static String TITLE = format(Template.DELETE.forTitle(), PersonRole.ENTITY_TITLE);
    public final static String DESC = format(Template.DELETE.forDesc(), PersonRole.ENTITY_TITLE);
}