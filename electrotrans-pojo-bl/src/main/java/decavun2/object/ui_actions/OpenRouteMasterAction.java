package decavun2.object.ui_actions;

import decavun2.object.Route;
import decavun2.object.AssignedVehicle;
import ua.com.fielden.platform.entity.AbstractFunctionalEntityToOpenCompoundMaster;
import ua.com.fielden.platform.entity.annotation.KeyType;
import ua.com.fielden.platform.entity.annotation.EntityTitle;
import ua.com.fielden.platform.entity.annotation.CompanionObject;
import ua.com.fielden.platform.reflection.TitlesDescsGetter;
import ua.com.fielden.platform.utils.Pair;

/**
 * Open Master Action entity object.
 *
 * @author Developers
 *
 */
@KeyType(Route.class)
@CompanionObject(OpenRouteMasterActionCo.class)
@EntityTitle("Route Master")
public class OpenRouteMasterAction extends AbstractFunctionalEntityToOpenCompoundMaster<Route> {

    private static final Pair<String, String> entityTitleAndDesc = TitlesDescsGetter.getEntityTitleAndDesc(OpenRouteMasterAction.class);
    public static final String ENTITY_TITLE = entityTitleAndDesc.getKey();
    public static final String ENTITY_DESC = entityTitleAndDesc.getValue();

    public static final String MAIN = "Main";
    public static final String ASSIGNEDVEHICLES = AssignedVehicle.ENTITY_TITLE + "s"; // Please adjust manually if the plural form is not standard
}
