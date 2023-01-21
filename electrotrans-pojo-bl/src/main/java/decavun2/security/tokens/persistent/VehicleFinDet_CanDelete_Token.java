package decavun2.security.tokens.persistent;

import static java.lang.String.format;

import decavun2.objects.VehicleFinDet;
import decavun2.security.tokens.UsersAndPersonnelModuleToken;
import ua.com.fielden.platform.security.tokens.Template;

/**
 * A security token for entity {@link VehicleFinDet} to guard Delete.
 *
 * @author Developers
 *
 */
public class VehicleFinDet_CanDelete_Token extends UsersAndPersonnelModuleToken {
    public final static String TITLE = format(Template.DELETE.forTitle(), VehicleFinDet.ENTITY_TITLE);
    public final static String DESC = format(Template.DELETE.forDesc(), VehicleFinDet.ENTITY_TITLE);
}