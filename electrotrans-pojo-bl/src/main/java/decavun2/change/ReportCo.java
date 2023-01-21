package decavun2.change;

import static metamodels.MetaModels.Report_;

import ua.com.fielden.platform.dao.IEntityDao;
import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.utils.EntityUtils;

/**
 * Companion object for entity {@link Report}.
 *
 * @author Developers
 *
 */
public interface ReportCo extends IEntityDao<Report> {
	
	static final String ERR_ONE_OF_REPORT_SOURCE_FIELDS_SHOULD_NOT_BE_EMPTY = "Enter one of the source properties: change or issue";

	
    static final IFetchProvider<Report> FETCH_PROVIDER = EntityUtils.fetch(Report.class).with(
        Report_.title(), Report_.desc(), Report_.department(),
        Report_.issue(), Report_.change(), Report_.person());

}
