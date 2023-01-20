package decavun2.security.tokens.open_compound_master;

import static java.lang.String.format;

import decavun2.objects.ui_actions.OpenVehicleMasterAction;
import decavun2.security.tokens.UsersAndPersonnelModuleToken;
import ua.com.fielden.platform.security.tokens.Template;

/**
 * A security token for entity {@link OpenVehicleMasterAction} to guard Open.
 *
 * @author Developers
 *
 */
public class OpenVehicleMasterAction_CanOpen_Token extends UsersAndPersonnelModuleToken {
    public final static String TITLE = format(Template.MASTER_OPEN.forTitle(), OpenVehicleMasterAction.ENTITY_TITLE);
    public final static String DESC = format(Template.MASTER_OPEN.forDesc(), OpenVehicleMasterAction.ENTITY_TITLE);
}