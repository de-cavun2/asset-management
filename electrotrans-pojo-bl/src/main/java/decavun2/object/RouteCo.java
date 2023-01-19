package decavun2.object;

import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.utils.EntityUtils;

import static metamodels.MetaModels.Route_;

import metamodels.MetaModels;
import ua.com.fielden.platform.dao.IEntityDao;

/**
 * Companion object for entity {@link Route}.
 *
 * @author Developers
 *
 */
public interface RouteCo extends IEntityDao<Route> {

    static final IFetchProvider<Route> FETCH_PROVIDER = EntityUtils.fetch(Route.class)
    		.with(Route_.routeNum(), Route_.name(), Route_.stationOrder(), Route_.inUse(), Route_.trafficData());
     

}
