package decavun2.objects.ui_actions;

import com.google.inject.Inject;

import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.dao.AbstractOpenCompoundMasterDao;
import ua.com.fielden.platform.dao.IEntityAggregatesOperations;
import decavun2.objects.VehicleFinDet;
import ua.com.fielden.platform.entity.query.IFilter;
import ua.com.fielden.platform.entity.annotation.EntityType;

/**
 * DAO implementation for companion object {@link OpenVehicleMasterActionCo}.
 *
 * @author Developers
 *
 */
@EntityType(OpenVehicleMasterAction.class)
public class OpenVehicleMasterActionDao extends AbstractOpenCompoundMasterDao<OpenVehicleMasterAction> implements OpenVehicleMasterActionCo {

    @Inject
    public OpenVehicleMasterActionDao(final IFilter filter, final IEntityAggregatesOperations coAggregates) {
        super(filter, coAggregates);
    }

    @Override
    protected IFetchProvider<OpenVehicleMasterAction> createFetchProvider() {
        return FETCH_PROVIDER;
    }

}