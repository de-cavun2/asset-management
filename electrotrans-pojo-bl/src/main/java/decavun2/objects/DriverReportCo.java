package decavun2.objects;

import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.utils.EntityUtils;

import static metamodels.MetaModels.DriverReport_;
import ua.com.fielden.platform.dao.IEntityDao;

/**
 * Companion object for entity {@link DriverReport}.
 *
 * @author Developers
 *
 */
public interface DriverReportCo extends IEntityDao<DriverReport> {

    static final IFetchProvider<DriverReport> FETCH_PROVIDER = EntityUtils.fetch(DriverReport.class)
            .with(DriverReport_.driverReportID(), DriverReport_.date(), DriverReport_.vehicle(), DriverReport_.state());

}