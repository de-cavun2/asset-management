package decavun2.analysis;

import com.google.inject.Inject;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.security.Authorise;
import ua.com.fielden.platform.dao.annotations.SessionRequired;
import decavun2.security.tokens.persistent.Statistics_CanSave_Token;
import metamodels.MetaModels;
import decavun2.security.tokens.persistent.Statistics_CanDelete_Token;
import ua.com.fielden.platform.dao.CommonEntityDao;
import ua.com.fielden.platform.entity.query.IFilter;
import ua.com.fielden.platform.entity.annotation.EntityType;

/**
 * DAO implementation for companion object {@link StatisticsCo}.
 *
 * @author Developers
 *
 */
@EntityType(Statistics.class)
public class StatisticsDao extends CommonEntityDao<Statistics> implements StatisticsCo {

    @Inject
    public StatisticsDao(final IFilter filter) {
        super(filter);
    }
    
    @Override
	public Statistics new_() {
		final Statistics stat = super.new_();
		
		stat.setcreatedAt(new Date());
		return stat;
		
	}

    @Override
    @SessionRequired
    @Authorise(Statistics_CanSave_Token.class)
    public Statistics save(Statistics entity) {
    	final boolean isPersisted = entity.isPersisted();
    	if (isPersisted) {
    		return entity;
    	}
    	
    	

    	// get repairs count
    	// get issues count
    	
        return super.save(entity);
    }

    @Override
    @SessionRequired
    @Authorise(Statistics_CanDelete_Token.class)
    public int batchDelete(final Collection<Long> entitiesIds) {
        return defaultBatchDelete(entitiesIds);
    }

    @Override
    @SessionRequired
    @Authorise(Statistics_CanDelete_Token.class)
    public int batchDelete(final List<Statistics> entities) {
        return defaultBatchDelete(entities);
    }

    @Override
    protected IFetchProvider<Statistics> createFetchProvider() {
        return FETCH_PROVIDER;
    }
}