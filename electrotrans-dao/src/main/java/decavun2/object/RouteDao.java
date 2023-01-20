package decavun2.object;

import com.google.inject.Inject;

import java.util.Collection;
import java.util.List;

import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.security.Authorise;
import ua.com.fielden.platform.dao.annotations.SessionRequired;
import decavun2.security.tokens.persistent.Route_CanSave_Token;
import decavun2.security.tokens.persistent.Route_CanDelete_Token;
import ua.com.fielden.platform.dao.CommonEntityDao;
import ua.com.fielden.platform.entity.query.IFilter;
import ua.com.fielden.platform.entity.annotation.EntityType;

/**
 * DAO implementation for companion object {@link RouteCo}.
 *
 * @author Developers
 *
 */
@EntityType(Route.class)
public class RouteDao extends CommonEntityDao<Route> implements RouteCo {

    @Inject
    public RouteDao(final IFilter filter) {
        super(filter);
    }
    
    @Override
    public Route new_() {
        return super.new_().setInUse(true);
    }

    @Override
    @SessionRequired
    @Authorise(Route_CanSave_Token.class)
    public Route save(Route entity) {
        return super.save(entity);
    }

    @Override
    @SessionRequired
    @Authorise(Route_CanDelete_Token.class)
    public int batchDelete(final Collection<Long> entitiesIds) {
        return defaultBatchDelete(entitiesIds);
    }

    @Override
    @SessionRequired
    @Authorise(Route_CanDelete_Token.class)
    public int batchDelete(final List<Route> entities) {
        return defaultBatchDelete(entities);
    }

    @Override
    protected IFetchProvider<Route> createFetchProvider() {
        return FETCH_PROVIDER;
    }
}