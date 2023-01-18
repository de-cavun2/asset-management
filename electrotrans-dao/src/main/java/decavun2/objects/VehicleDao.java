package decavun2.objects;

import com.google.inject.Inject;

import java.util.Collection;
import java.util.List;

import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.security.Authorise;
import ua.com.fielden.platform.dao.annotations.SessionRequired;
import decavun2.security.tokens.persistent.Vehicle_CanSave_Token;
import decavun2.security.tokens.persistent.Vehicle_CanDelete_Token;
import ua.com.fielden.platform.dao.CommonEntityDao;
import ua.com.fielden.platform.entity.query.IFilter;
import ua.com.fielden.platform.entity.annotation.EntityType;

/**
 * DAO implementation for companion object {@link VehicleCo}.
 *
 * @author Developers
 *
 */
@EntityType(Vehicle.class)
public class VehicleDao extends CommonEntityDao<Vehicle> implements VehicleCo {

    @Inject
    public VehicleDao(final IFilter filter) {
        super(filter);
    }
    
    @Override
    public Vehicle new_() {
        return super.new_().setActive(true);
    }

    @Override
    @SessionRequired
    @Authorise(Vehicle_CanSave_Token.class)
    public Vehicle save(Vehicle entity) {
        return super.save(entity);
    }

    @Override
    @SessionRequired
    @Authorise(Vehicle_CanDelete_Token.class)
    public int batchDelete(final Collection<Long> entitiesIds) {
        return defaultBatchDelete(entitiesIds);
    }

    @Override
    @SessionRequired
    @Authorise(Vehicle_CanDelete_Token.class)
    public int batchDelete(final List<Vehicle> entities) {
        return defaultBatchDelete(entities);
    }

    @Override
    protected IFetchProvider<Vehicle> createFetchProvider() {
        return FETCH_PROVIDER;
    }
}