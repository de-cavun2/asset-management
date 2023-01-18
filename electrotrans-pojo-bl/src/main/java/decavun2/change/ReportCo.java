package decavun2.change;

import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.utils.EntityUtils;
import ua.com.fielden.platform.dao.IEntityDao;

/**
 * Companion object for entity {@link Report}.
 *
 * @author Developers
 *
 */
public interface ReportCo extends IEntityDao<Report> {

    static final IFetchProvider<Report> FETCH_PROVIDER = EntityUtils.fetch(Report.class).with(
        // TODO: uncomment the following line and specify the properties, which are required for the UI. Then remove the line after.
        // "key", "desc");
        "Please specify the properties, which are required for the UI");

}
