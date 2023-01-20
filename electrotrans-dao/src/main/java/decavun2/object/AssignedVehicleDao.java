package decavun2.object;

import com.google.inject.Inject;

import java.util.Collection;
import java.util.List;

import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.security.Authorise;
import ua.com.fielden.platform.dao.annotations.SessionRequired;
import decavun2.security.tokens.persistent.AssignedVehicle_CanSave_Token;
import decavun2.security.tokens.persistent.AssignedVehicle_CanDelete_Token;
import ua.com.fielden.platform.dao.CommonEntityDao;
import ua.com.fielden.platform.entity.query.IFilter;
import ua.com.fielden.platform.entity.annotation.EntityType;

/**
 * DAO implementation for companion object {@link AssignedVehicleCo}.
 *
 * @author Developers
 *
 */
@EntityType(AssignedVehicle.class)
public class AssignedVehicleDao extends CommonEntityDao<AssignedVehicle> implements AssignedVehicleCo {

    @Inject
    public AssignedVehicleDao(final IFilter filter) {
        super(filter);
    }
    
    @Override
    public AssignedVehicle new_() {
        return super.new_().setActive(true);
    }

    @Override
    @SessionRequired
    @Authorise(AssignedVehicle_CanSave_Token.class)
    public AssignedVehicle save(AssignedVehicle entity) {
        return super.save(entity);
    }

    @Override
    @SessionRequired
    @Authorise(AssignedVehicle_CanDelete_Token.class)
    public int batchDelete(final Collection<Long> entitiesIds) {
        return defaultBatchDelete(entitiesIds);
    }

    @Override
    @SessionRequired
    @Authorise(AssignedVehicle_CanDelete_Token.class)
    public int batchDelete(final List<AssignedVehicle> entities) {
        return defaultBatchDelete(entities);
    }

    @Override
    protected IFetchProvider<AssignedVehicle> createFetchProvider() {
        return FETCH_PROVIDER;
    }
}