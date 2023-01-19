package decavun2.change;

import com.google.inject.Inject;

import java.util.Collection;
import java.util.List;

import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.security.Authorise;
import ua.com.fielden.platform.dao.annotations.SessionRequired;
import decavun2.security.tokens.persistent.Issue_CanSave_Token;
import decavun2.security.tokens.persistent.Issue_CanDelete_Token;
import ua.com.fielden.platform.dao.CommonEntityDao;
import ua.com.fielden.platform.entity.query.IFilter;
import ua.com.fielden.platform.entity.annotation.EntityType;

/**
 * DAO implementation for companion object {@link IssueCo}.
 *
 * @author Developers
 *
 */
@EntityType(Issue.class)
public class IssueDao extends CommonEntityDao<Issue> implements IssueCo {

    @Inject
    public IssueDao(final IFilter filter) {
        super(filter);
    }

    @Override
    @SessionRequired
    @Authorise(Issue_CanSave_Token.class)
    public Issue save(Issue entity) {
        return super.save(entity);
    }

    @Override
    @SessionRequired
    @Authorise(Issue_CanDelete_Token.class)
    public int batchDelete(final Collection<Long> entitiesIds) {
        return defaultBatchDelete(entitiesIds);
    }

    @Override
    @SessionRequired
    @Authorise(Issue_CanDelete_Token.class)
    public int batchDelete(final List<Issue> entities) {
        return defaultBatchDelete(entities);
    }

    @Override
    protected IFetchProvider<Issue> createFetchProvider() {
        return FETCH_PROVIDER;
    }
}