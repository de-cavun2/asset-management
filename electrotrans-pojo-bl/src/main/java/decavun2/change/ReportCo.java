package decavun2.change;

import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.utils.EntityUtils;
import metamodels.MetaModels;
import ua.com.fielden.platform.dao.IEntityDao;

/**
 * Companion object for entity {@link Report}.
 *
 * @author Developers
 *
 */
public interface ReportCo extends IEntityDao<Report> {
	
	static final String ERR_ONE_OF_REPORT_SOURCE_FIELDS_SHOULD_NOT_BE_EMPTY = "Enter one of the source properties: change or issue";

	
    static final IFetchProvider<Report> FETCH_PROVIDER = EntityUtils.fetch(Report.class).with(
        MetaModels.Report_.title(), MetaModels.Report_.desc(), MetaModels.Report_.department(),
        MetaModels.Report_.issue(), MetaModels.Report_.change());

}
