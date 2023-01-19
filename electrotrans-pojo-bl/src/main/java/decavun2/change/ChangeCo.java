package decavun2.change;

import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.utils.EntityUtils;
import ua.com.fielden.platform.dao.IEntityDao;

/**
 * Companion object for entity {@link Change}.
 *
 * @author Developers
 *
 */
public interface ChangeCo extends IEntityDao<Change> {

    static final IFetchProvider<Change> FETCH_PROVIDER = EntityUtils.fetch(Change.class).with(
        // TODO: uncomment the following line and specify the properties, which are required for the UI. Then remove the line after.
        // "key", "desc");
        "Please specify the properties, which are required for the UI");

}
