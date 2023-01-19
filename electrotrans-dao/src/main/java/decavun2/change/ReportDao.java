package decavun2.change;

import com.google.inject.Inject;

import static metamodels.MetaModels.Report_;

import java.util.Collection;
import java.util.List;

import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.security.Authorise;
import ua.com.fielden.platform.dao.annotations.SessionRequired;
import decavun2.security.tokens.persistent.Report_CanSave_Token;
import metamodels.MetaModels;
import decavun2.security.tokens.persistent.Report_CanDelete_Token;
import ua.com.fielden.platform.dao.CommonEntityDao;
import ua.com.fielden.platform.entity.query.IFilter;
import ua.com.fielden.platform.entity.annotation.EntityType;

/**
 * DAO implementation for companion object {@link ReportCo}.
 *
 * @author Developers
 *
 */
@EntityType(Report.class)
public class ReportDao extends CommonEntityDao<Report> implements ReportCo {

    @Inject
    public ReportDao(final IFilter filter) {
        super(filter);
    }
    
    @Override
    	public Report new_() {
    		Report report =  super.new_();
    		
    		report.getProperty(Report_.change()).setRequired(true);
    		report.getProperty(Report_.issue()).setRequired(true);
    		
    		return report;
    	}

    @Override
    @SessionRequired
    @Authorise(Report_CanSave_Token.class)
    public Report save(Report entity) {
        return super.save(entity);
    }

    @Override
    @SessionRequired
    @Authorise(Report_CanDelete_Token.class)
    public int batchDelete(final Collection<Long> entitiesIds) {
        return defaultBatchDelete(entitiesIds);
    }

    @Override
    @SessionRequired
    @Authorise(Report_CanDelete_Token.class)
    public int batchDelete(final List<Report> entities) {
        return defaultBatchDelete(entities);
    }

    @Override
    protected IFetchProvider<Report> createFetchProvider() {
        return FETCH_PROVIDER;
    }
}