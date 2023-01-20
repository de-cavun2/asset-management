package decavun2.change;

import com.google.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.List;

import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.security.Authorise;
import ua.com.fielden.platform.dao.annotations.SessionRequired;
import decavun2.security.tokens.persistent.Issue_CanSave_Token;
import decavun2.security.tokens.persistent.Issue_CanDelete_Token;
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
@EntityType(Issue.class)
public class IssueDao extends CommonEntityDao<Issue> implements IssueCo {
	
	public static final String ISSUE_NO = "ISSUE";
    public static final String DEFAULT_ISSUE_NO = "TBD";

    @Inject
    public IssueDao(final IFilter filter) {
        super(filter);
    }

    @Override
    public Issue new_() {
        return super.new_().setIssueNumber(DEFAULT_ISSUE_NO).setActive(true);
    }

    @Override
    @SessionRequired
    @Authorise(Issue_CanSave_Token.class)
    public Issue save(Issue issue) {
        issue.isValid().ifFailure(Result::throwRuntime);

        final boolean wasPersisted = issue.isPersisted();
        try {
            if (!wasPersisted) {
                final IKeyNumber coKeyNumber = co(KeyNumber.class);
                final var nextAssetNo = StringUtils.leftPad(coKeyNumber.nextNumber(ISSUE_NO).toString(), 6, "0"); 
                issue.setIssueNumber((String) nextAssetNo);
            }

            final var savedAsset = super.save(issue);

            return savedAsset;

        } catch (final Exception ex) {
            if (!wasPersisted) {
                issue.setIssueNumber(DEFAULT_ISSUE_NO);
            }
            throw ex;
        } 
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