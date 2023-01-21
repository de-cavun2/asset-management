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
import decavun2.objects.DriverReport;
import decavun2.objects.Repair;
import decavun2.objects.Vehicle;
import decavun2.security.tokens.persistent.Statistics_CanDelete_Token;
import ua.com.fielden.platform.dao.CommonEntityDao;
import ua.com.fielden.platform.entity.query.IFilter;
import ua.com.fielden.platform.entity.annotation.EntityType;


import static ua.com.fielden.platform.entity.query.fluent.EntityQueryUtils.cond;
import static ua.com.fielden.platform.entity.query.fluent.EntityQueryUtils.expr;
import static ua.com.fielden.platform.entity.query.fluent.EntityQueryUtils.fetchAggregates;
import static ua.com.fielden.platform.entity.query.fluent.EntityQueryUtils.fetchAll;
import static ua.com.fielden.platform.entity.query.fluent.EntityQueryUtils.fetchAllAndInstrument;
import static ua.com.fielden.platform.entity.query.fluent.EntityQueryUtils.fetchAllInclCalc;
import static ua.com.fielden.platform.entity.query.fluent.EntityQueryUtils.fetchAllInclCalcAndInstrument;
import static ua.com.fielden.platform.entity.query.fluent.EntityQueryUtils.fetchAndInstrument;
import static ua.com.fielden.platform.entity.query.fluent.EntityQueryUtils.fetchIdOnly;
import static ua.com.fielden.platform.entity.query.fluent.EntityQueryUtils.fetchKeyAndDescOnly;
import static ua.com.fielden.platform.entity.query.fluent.EntityQueryUtils.fetchKeyAndDescOnlyAndInstrument;
import static ua.com.fielden.platform.entity.query.fluent.EntityQueryUtils.fetchOnly;
import static ua.com.fielden.platform.entity.query.fluent.EntityQueryUtils.fetchOnlyAndInstrument;
import static ua.com.fielden.platform.entity.query.fluent.EntityQueryUtils.from;
import static ua.com.fielden.platform.entity.query.fluent.EntityQueryUtils.orderBy;
import static ua.com.fielden.platform.entity.query.fluent.EntityQueryUtils.select;
import static ua.com.fielden.platform.utils.EntityUtils.fetch;


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
		
		stat.setCreatedAt(new Date());
		stat.setIssuesCount(0);
		stat.setRepairsCount(0);
		return stat;
		
	}

    @Override
    @SessionRequired
    @Authorise(Statistics_CanSave_Token.class)
    public Statistics save(Statistics entity) {

    	if (entity.isPersisted()) {
    		return super.save(entity); 
    	}
    	
    	final Vehicle vehicle = entity.getVehicle();
    	
    	final var repairQuery = select(Repair.class).where().prop(MetaModels.Repair_.vehicle()).in().values(vehicle).model();
    	final int repairsCount = co$(Repair.class).count(repairQuery);
    	
    	final var issueQuery = select(DriverReport.class).where().prop(MetaModels.DriverReport_.vehicle()).in().values(vehicle).model();
    	final int issueCount = co$(DriverReport.class).count(issueQuery);
    	
    	entity.setIssuesCount(issueCount);
    	entity.setRepairsCount(repairsCount);
    	
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