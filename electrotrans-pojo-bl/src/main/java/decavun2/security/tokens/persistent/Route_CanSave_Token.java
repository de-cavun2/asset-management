package decavun2.security.tokens.persistent;

import static java.lang.String.format;

import ua.com.fielden.platform.reflection.TitlesDescsGetter;
import ua.com.fielden.platform.security.tokens.Template;
import decavun2.object.Route;
import decavun2.security.tokens.UsersAndPersonnelModuleToken;

/**
 * A security token for entity {@link Route} to guard Save.
 *
 * @author Developers
 *
 */
public class Route_CanSave_Token extends UsersAndPersonnelModuleToken {
    public final static String TITLE = format(Template.SAVE.forTitle(), Route.ENTITY_TITLE);
    public final static String DESC = format(Template.SAVE.forDesc(), Route.ENTITY_TITLE);
}