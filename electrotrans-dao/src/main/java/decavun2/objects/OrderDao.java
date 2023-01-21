package decavun2.objects;

import com.google.inject.Inject;
import java.util.Collection;
import java.util.List;


import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.security.Authorise;
import ua.com.fielden.platform.dao.annotations.SessionRequired;
import decavun2.security.tokens.persistent.Order_CanSave_Token;
import decavun2.objects.Order;
import decavun2.objects.OrderCo;
import decavun2.security.tokens.persistent.Order_CanDelete_Token;
import ua.com.fielden.platform.dao.CommonEntityDao;
import ua.com.fielden.platform.entity.query.IFilter;
import ua.com.fielden.platform.entity.annotation.EntityType;

/**
 * DAO implementation for companion object {@link OrderCo}.
 *
 * @author Developers
 *
 */
@EntityType(Order.class)
public class OrderDao extends CommonEntityDao<Order> implements OrderCo {
    @Inject
    public OrderDao(final IFilter filter) {
        super(filter);
    }

    @Override
    @SessionRequired
    @Authorise(Order_CanSave_Token.class)
    public Order save(Order entity) {
        return super.save(entity);
    }
    @Override
    @SessionRequired
    @Authorise(Order_CanDelete_Token.class)
    public int batchDelete(final Collection<Long> entitiesIds) {
        return defaultBatchDelete(entitiesIds);
    }
    @Override
    @SessionRequired
    @Authorise(Order_CanDelete_Token.class)
    public int batchDelete(final List<Order> entities) {
        return defaultBatchDelete(entities);
    }
    @Override
    protected IFetchProvider<Order> createFetchProvider() {
        return FETCH_PROVIDER;
    }
}