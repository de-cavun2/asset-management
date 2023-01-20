package decavun2.analysis;

import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.utils.EntityUtils;
import ua.com.fielden.platform.dao.IEntityDao;

/**
 * Companion object for entity {@link Statistics}.
 *
 * @author Developers
 *
 */
public interface StatisticsCo extends IEntityDao<Statistics> {
	
	static final String STATISTICS_IS_NOT_EDITABLE_ERROR = "You cannot change a Statistics entry. Create a new one";

    static final IFetchProvider<Statistics> FETCH_PROVIDER = EntityUtils.fetch(Statistics.class).with(
        // TODO: uncomment the following line and specify the properties, which are required for the UI. Then remove the line after.
        // "key", "desc");
        "Please specify the properties, which are required for the UI");

}
