package decavun2.objects.master.menu.actions;

import com.google.inject.Inject;

import ua.com.fielden.platform.security.Authorise;
import ua.com.fielden.platform.dao.annotations.SessionRequired;
import decavun2.security.tokens.compound_master_menu.VehicleMaster_OpenVehicleFinDet_MenuItem_CanAccess_Token;
import ua.com.fielden.platform.dao.CommonEntityDao;
import ua.com.fielden.platform.entity.query.IFilter;
import ua.com.fielden.platform.entity.annotation.EntityType;

/**
 * DAO implementation for companion object {@link VehicleMaster_OpenVehicleFinDet_MenuItemCo}.
 *
 * @author Developers
 *
 */
@EntityType(VehicleMaster_OpenVehicleFinDet_MenuItem.class)
public class VehicleMaster_OpenVehicleFinDet_MenuItemDao extends CommonEntityDao<VehicleMaster_OpenVehicleFinDet_MenuItem> implements VehicleMaster_OpenVehicleFinDet_MenuItemCo {

    @Inject
    public VehicleMaster_OpenVehicleFinDet_MenuItemDao(final IFilter filter) {
        super(filter);
    }

    @Override
    @SessionRequired
    @Authorise(VehicleMaster_OpenVehicleFinDet_MenuItem_CanAccess_Token.class)
    public VehicleMaster_OpenVehicleFinDet_MenuItem save(VehicleMaster_OpenVehicleFinDet_MenuItem entity) {
        return super.save(entity);
    }

}