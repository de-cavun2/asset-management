package decavun2.object;

import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.utils.EntityUtils;

import static metamodels.MetaModels.Route_;
import ua.com.fielden.platform.dao.IEntityDao;

/**
 * Companion object for entity {@link Route}.
 *
 * @author Developers
 *
 */
public interface RouteCo extends IEntityDao<Route> {
	
    final String ERROR_MSG_REQUIRED_PROPERTIES = "Required property [%s] is not specified for entity [%s].".formatted("Station order", "Route");
    final String ERROR_MSG_INUSE_REQUIRES_TRAFFICDATA = "Required property [%s] is not specified for entity [%s].".formatted("Traffic data", "Route");

    static final IFetchProvider<Route> FETCH_PROVIDER = EntityUtils.fetch(Route.class)
    		.with(Route_.routeNum(), Route_.name(), Route_.stationOrder(), Route_.inUse(), Route_.trafficData());
     
}
