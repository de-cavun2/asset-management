package decavun2.security.tokens.persistent;

import static java.lang.String.format;

import ua.com.fielden.platform.security.tokens.Template;
import decavun2.object.AssignedVehicle;
import decavun2.security.tokens.UsersAndPersonnelModuleToken;

/**
 * A security token for entity {@link AssignedVehicle} to guard Delete.
 *
 * @author Developers
 *
 */
public class AssignedVehicle_CanDelete_Token extends UsersAndPersonnelModuleToken {
    public final static String TITLE = format(Template.DELETE.forTitle(), AssignedVehicle.ENTITY_TITLE);
    public final static String DESC = format(Template.DELETE.forDesc(), AssignedVehicle.ENTITY_TITLE);
}