package decavun2.security.tokens.persistent;

import static java.lang.String.format;

import ua.com.fielden.platform.reflection.TitlesDescsGetter;
import ua.com.fielden.platform.security.tokens.Template;
import decavun2.change.Change;
import decavun2.security.tokens.UsersAndPersonnelModuleToken;

/**
 * A security token for entity {@link Change} to guard Delete.
 *
 * @author Developers
 *
 */
public class Change_CanDelete_Token extends UsersAndPersonnelModuleToken {
    public final static String TITLE = format(Template.DELETE.forTitle(), Change.ENTITY_TITLE);
    public final static String DESC = format(Template.DELETE.forDesc(), Change.ENTITY_TITLE);
}