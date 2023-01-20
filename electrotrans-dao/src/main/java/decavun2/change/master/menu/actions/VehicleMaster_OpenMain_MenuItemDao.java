package decavun2.objects.master.menu.actions;

import com.google.inject.Inject;

import ua.com.fielden.platform.security.Authorise;
import ua.com.fielden.platform.dao.annotations.SessionRequired;
import decavun2.security.tokens.compound_master_menu.VehicleMaster_OpenMain_MenuItem_CanAccess_Token;
import ua.com.fielden.platform.dao.CommonEntityDao;
import ua.com.fielden.platform.entity.query.IFilter;
import ua.com.fielden.platform.entity.annotation.EntityType;

/**
 * DAO implementation for companion object {@link VehicleMaster_OpenMain_MenuItemCo}.
 *
 * @author Developers
 *
 */
@EntityType(VehicleMaster_OpenMain_MenuItem.class)
public class VehicleMaster_OpenMain_MenuItemDao extends CommonEntityDao<VehicleMaster_OpenMain_MenuItem> implements VehicleMaster_OpenMain_MenuItemCo {

    @Inject
    public VehicleMaster_OpenMain_MenuItemDao(final IFilter filter) {
        super(filter);
    }

    @Override
    @SessionRequired
    @Authorise(VehicleMaster_OpenMain_MenuItem_CanAccess_Token.class)
    public VehicleMaster_OpenMain_MenuItem save(VehicleMaster_OpenMain_MenuItem entity) {
        return super.save(entity);
    }

}