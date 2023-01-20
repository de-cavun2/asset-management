package decavun2.objects;

import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.utils.EntityUtils;

import static metamodels.MetaModels.Order_;
import ua.com.fielden.platform.dao.IEntityDao;

/**
 * Companion object for entity {@link Order}.
 *
 * @author Developers
 *
 */
public interface OrderCo extends IEntityDao<Order> {

    static final IFetchProvider<Order> FETCH_PROVIDER = EntityUtils.fetch(Order.class)
            .with(Order_.orderID(), Order_.date(), Order_.parts());

}