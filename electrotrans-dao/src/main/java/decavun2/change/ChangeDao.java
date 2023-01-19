package decavun2.change;

import com.google.inject.Inject;

import java.util.Collection;
import java.util.List;

import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.security.Authorise;
import ua.com.fielden.platform.dao.annotations.SessionRequired;
import decavun2.security.tokens.persistent.Change_CanSave_Token;
import decavun2.security.tokens.persistent.Change_CanDelete_Token;
import ua.com.fielden.platform.dao.CommonEntityDao;
import ua.com.fielden.platform.entity.query.IFilter;
import ua.com.fielden.platform.entity.annotation.EntityType;

/**
 * DAO implementation for companion object {@link ChangeCo}.
 *
 * @author Developers
 *
 */
@EntityType(Change.class)
public class ChangeDao extends CommonEntityDao<Change> implements ChangeCo {

    @Inject
    public ChangeDao(final IFilter filter) {
        super(filter);
    }

    @Override
    @SessionRequired
    @Authorise(Change_CanSave_Token.class)
    public Change save(Change entity) {
        return super.save(entity);
    }

    @Override
    @SessionRequired
    @Authorise(Change_CanDelete_Token.class)
    public int batchDelete(final Collection<Long> entitiesIds) {
        return defaultBatchDelete(entitiesIds);
    }

    @Override
    @SessionRequired
    @Authorise(Change_CanDelete_Token.class)
    public int batchDelete(final List<Change> entities) {
        return defaultBatchDelete(entities);
    }

    @Override
    protected IFetchProvider<Change> createFetchProvider() {
        return FETCH_PROVIDER;
    }
}