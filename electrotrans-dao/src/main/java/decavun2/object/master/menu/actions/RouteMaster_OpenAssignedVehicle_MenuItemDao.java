package decavun2.object.master.menu.actions;

import com.google.inject.Inject;

import ua.com.fielden.platform.security.Authorise;
import ua.com.fielden.platform.dao.annotations.SessionRequired;
import decavun2.security.tokens.compound_master_menu.RouteMaster_OpenAssignedVehicle_MenuItem_CanAccess_Token;
import ua.com.fielden.platform.dao.CommonEntityDao;
import ua.com.fielden.platform.entity.query.IFilter;
import ua.com.fielden.platform.entity.annotation.EntityType;

/**
 * DAO implementation for companion object {@link RouteMaster_OpenAssignedVehicle_MenuItemCo}.
 *
 * @author Developers
 *
 */
@EntityType(RouteMaster_OpenAssignedVehicle_MenuItem.class)
public class RouteMaster_OpenAssignedVehicle_MenuItemDao extends CommonEntityDao<RouteMaster_OpenAssignedVehicle_MenuItem> implements RouteMaster_OpenAssignedVehicle_MenuItemCo {

    @Inject
    public RouteMaster_OpenAssignedVehicle_MenuItemDao(final IFilter filter) {
        super(filter);
    }

    @Override
    @SessionRequired
    @Authorise(RouteMaster_OpenAssignedVehicle_MenuItem_CanAccess_Token.class)
    public RouteMaster_OpenAssignedVehicle_MenuItem save(RouteMaster_OpenAssignedVehicle_MenuItem entity) {
        return super.save(entity);
    }

}