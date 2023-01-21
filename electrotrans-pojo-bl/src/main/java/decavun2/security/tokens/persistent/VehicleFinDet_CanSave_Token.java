package decavun2.security.tokens.persistent;

import static java.lang.String.format;

import decavun2.objects.VehicleFinDet;
import decavun2.security.tokens.UsersAndPersonnelModuleToken;
import ua.com.fielden.platform.security.tokens.Template;

/**
 * A security token for entity {@link VehicleFinDet} to guard Save.
 *
 * @author Developers
 *
 */
public class VehicleFinDet_CanSave_Token extends UsersAndPersonnelModuleToken {
    public final static String TITLE = format(Template.SAVE.forTitle(), VehicleFinDet.ENTITY_TITLE);
    public final static String DESC = format(Template.SAVE.forDesc(), VehicleFinDet.ENTITY_TITLE);
}