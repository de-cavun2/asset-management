package decavun2.security.tokens.persistent;

import static java.lang.String.format;

import ua.com.fielden.platform.reflection.TitlesDescsGetter;
import ua.com.fielden.platform.security.tokens.Template;
import decavun2.objects.Vehicle;
import decavun2.security.tokens.UsersAndPersonnelModuleToken;

/**
 * A security token for entity {@link Vehicle} to guard Delete.
 *
 * @author Developers
 *
 */
public class Vehicle_CanDelete_Token extends UsersAndPersonnelModuleToken {
    public final static String TITLE = format(Template.DELETE.forTitle(), Vehicle.ENTITY_TITLE);
    public final static String DESC = format(Template.DELETE.forDesc(), Vehicle.ENTITY_TITLE);
}