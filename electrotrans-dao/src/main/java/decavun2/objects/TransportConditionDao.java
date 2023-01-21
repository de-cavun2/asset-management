package decavun2.objects;

import com.google.inject.Inject;

//import static decavun2.objects.TransportCondition.ASSET_NO_LENGTH;

import java.util.Collection;
import java.util.List;

import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.security.Authorise;
import ua.com.fielden.platform.dao.annotations.SessionRequired;
import decavun2.security.tokens.persistent.TransportCondition_CanSave_Token;
import decavun2.security.tokens.persistent.TransportCondition_CanDelete_Token;
import ua.com.fielden.platform.dao.CommonEntityDao;
import ua.com.fielden.platform.entity.query.IFilter;
import ua.com.fielden.platform.error.Result;
import ua.com.fielden.platform.keygen.IKeyNumber;
import ua.com.fielden.platform.keygen.KeyNumber;
import ua.com.fielden.platform.entity.annotation.EntityType;

/**
 * DAO implementation for companion object {@link IssueCo}.
 *
 * @author Developers
 *
 */
@EntityType(TransportCondition.class)
public class TransportConditionDao extends CommonEntityDao<TransportCondition> implements TransportConditionCo {
    
    public static final String TransportCondition_NO = "ISSUE";
    public static final String DEFAULT_TransportCondition_NO = "TBD";

    @Inject
    public TransportConditionDao(final IFilter filter) {
        super(filter);
    }
    
    @Override
    public TransportCondition new_() {
        return super.new_().setConditionId(DEFAULT_TransportCondition_NO);
    }

    @Override
    @SessionRequired
    @Authorise(TransportCondition_CanSave_Token.class)
    public TransportCondition save(TransportCondition issue) {
        issue.isValid().ifFailure(Result::throwRuntime);
        
        final boolean wasPersisted = issue.isPersisted();
        try {
            if (!wasPersisted) {
                final IKeyNumber coKeyNumber = co(KeyNumber.class);
                final var nextAssetNo = coKeyNumber.nextNumber(TransportCondition_NO).toString(); 
                issue.setConditionId((String) nextAssetNo);
            }
            
            final var savedAsset = super.save(issue);
            
            return savedAsset;

        } catch (final Exception ex) {
            if (!wasPersisted) {
                issue.setConditionId(DEFAULT_TransportCondition_NO);
            }
            throw ex;
        } 
    }

    @Override
    @SessionRequired
    @Authorise(TransportCondition_CanDelete_Token.class)
    public int batchDelete(final Collection<Long> entitiesIds) {
        return defaultBatchDelete(entitiesIds);
    }

    @Override
    @SessionRequired
    @Authorise(TransportCondition_CanDelete_Token.class)
    public int batchDelete(final List<TransportCondition> entities) {
        return defaultBatchDelete(entities);
    }

    @Override
    protected IFetchProvider<TransportCondition> createFetchProvider() {
        return FETCH_PROVIDER;
    }
}