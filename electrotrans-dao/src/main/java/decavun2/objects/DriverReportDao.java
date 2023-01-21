package decavun2.objects;

import com.google.inject.Inject;

import java.util.Collection;
import java.util.List;

import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.security.Authorise;
import ua.com.fielden.platform.dao.annotations.SessionRequired;
import decavun2.security.tokens.persistent.DriverReport_CanSave_Token;
import decavun2.objects.DriverReport;
import decavun2.objects.DriverReportCo;
import decavun2.security.tokens.persistent.DriverReport_CanDelete_Token;
import ua.com.fielden.platform.dao.CommonEntityDao;
import ua.com.fielden.platform.entity.query.IFilter;
import ua.com.fielden.platform.entity.annotation.EntityType;

/**
 * DAO implementation for companion object {@link DriverReportCo}.
 *
 * @author Developers
 *
 */
@EntityType(DriverReport.class)
public class DriverReportDao extends CommonEntityDao<DriverReport> implements DriverReportCo {

    @Inject
    public DriverReportDao(final IFilter filter) {
        super(filter);
    }

    @Override
    @SessionRequired
    @Authorise(DriverReport_CanSave_Token.class)
    public DriverReport save(DriverReport entity) {
        return super.save(entity);
    }

    @Override
    @SessionRequired
    @Authorise(DriverReport_CanDelete_Token.class)
    public int batchDelete(final Collection<Long> entitiesIds) {
        return defaultBatchDelete(entitiesIds);
    }

    @Override
    @SessionRequired
    @Authorise(DriverReport_CanDelete_Token.class)
    public int batchDelete(final List<DriverReport> entities) {
        return defaultBatchDelete(entities);
    }

    @Override
    protected IFetchProvider<DriverReport> createFetchProvider() {
        return FETCH_PROVIDER;
    }
}