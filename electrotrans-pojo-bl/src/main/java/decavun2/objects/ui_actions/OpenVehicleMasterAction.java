package decavun2.objects.ui_actions;

import decavun2.objects.Vehicle;
import decavun2.objects.VehicleFinDet;
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
@KeyType(Vehicle.class)
@CompanionObject(OpenVehicleMasterActionCo.class)
@EntityTitle("Vehicle Master")
public class OpenVehicleMasterAction extends AbstractFunctionalEntityToOpenCompoundMaster<Vehicle> {

    private static final Pair<String, String> entityTitleAndDesc = TitlesDescsGetter.getEntityTitleAndDesc(OpenVehicleMasterAction.class);
    public static final String ENTITY_TITLE = entityTitleAndDesc.getKey();
    public static final String ENTITY_DESC = entityTitleAndDesc.getValue();

    public static final String MAIN = "Main";
    public static final String VEHICLEFINDETS = VehicleFinDet.ENTITY_TITLE + "s"; // Please adjust manually if the plural form is not standard
}
