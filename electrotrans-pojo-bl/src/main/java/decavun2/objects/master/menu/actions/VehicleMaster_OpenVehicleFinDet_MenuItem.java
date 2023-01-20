package decavun2.objects.master.menu.actions;

import decavun2.objects.Vehicle;
import ua.com.fielden.platform.entity.AbstractFunctionalEntityForCompoundMenuItem;
import ua.com.fielden.platform.entity.annotation.KeyType;
import ua.com.fielden.platform.entity.annotation.EntityTitle;
import ua.com.fielden.platform.entity.annotation.CompanionObject;
import ua.com.fielden.platform.reflection.TitlesDescsGetter;
import ua.com.fielden.platform.utils.Pair;

/**
 * Master entity object to model the detail menu item of the compound master entity object.
 *
 * @author Developers
 *
 */
@KeyType(Vehicle.class)
@CompanionObject(VehicleMaster_OpenVehicleFinDet_MenuItemCo.class)
@EntityTitle("Vehicle Master Vehicle Fin Det Menu Item")
public class VehicleMaster_OpenVehicleFinDet_MenuItem extends AbstractFunctionalEntityForCompoundMenuItem<Vehicle> {

    private static final Pair<String, String> entityTitleAndDesc = TitlesDescsGetter.getEntityTitleAndDesc(VehicleMaster_OpenVehicleFinDet_MenuItem.class);
    public static final String ENTITY_TITLE = entityTitleAndDesc.getKey();
    public static final String ENTITY_DESC = entityTitleAndDesc.getValue();

}
