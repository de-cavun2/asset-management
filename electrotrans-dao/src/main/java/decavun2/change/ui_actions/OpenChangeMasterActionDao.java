package decavun2.change.ui_actions;

import com.google.inject.Inject;

import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.dao.AbstractOpenCompoundMasterDao;
import ua.com.fielden.platform.dao.IEntityAggregatesOperations;
import decavun2.change.Issue;
import decavun2.change.Report;
import ua.com.fielden.platform.entity.query.IFilter;
import ua.com.fielden.platform.entity.annotation.EntityType;

/**
 * DAO implementation for companion object {@link OpenChangeMasterActionCo}.
 *
 * @author Developers
 *
 */
@EntityType(OpenChangeMasterAction.class)
public class OpenChangeMasterActionDao extends AbstractOpenCompoundMasterDao<OpenChangeMasterAction> implements OpenChangeMasterActionCo {

    @Inject
    public OpenChangeMasterActionDao(final IFilter filter, final IEntityAggregatesOperations coAggregates) {
        super(filter, coAggregates);
        addViewBinding(OpenChangeMasterAction.ISSUES, Issue.class, "change");
        addViewBinding(OpenChangeMasterAction.REPORTS, Report.class, "change");
    }

    @Override
    protected IFetchProvider<OpenChangeMasterAction> createFetchProvider() {
        return FETCH_PROVIDER;
    }

}