package decavun2.object.ui_actions;

import static metamodels.MetaModels.AssignedVehicle_;

import com.google.inject.Inject;

import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.dao.AbstractOpenCompoundMasterDao;
import ua.com.fielden.platform.dao.IEntityAggregatesOperations;
import decavun2.object.AssignedVehicle;
import ua.com.fielden.platform.entity.query.IFilter;
import ua.com.fielden.platform.entity.annotation.EntityType;

/**
 * DAO implementation for companion object {@link OpenRouteMasterActionCo}.
 *
 * @author Developers
 *
 */
@EntityType(OpenRouteMasterAction.class)
public class OpenRouteMasterActionDao extends AbstractOpenCompoundMasterDao<OpenRouteMasterAction> implements OpenRouteMasterActionCo {

    @Inject
    public OpenRouteMasterActionDao(final IFilter filter, final IEntityAggregatesOperations coAggregates) {
        super(filter, coAggregates);
        this.addViewBinding(OpenRouteMasterAction.ASSIGNEDVEHICLES, AssignedVehicle.class, AssignedVehicle_.route());
    }

    @Override
    protected IFetchProvider<OpenRouteMasterAction> createFetchProvider() {
        return FETCH_PROVIDER;
    }

}