package decavun2.change;

import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.utils.EntityUtils;

import static metamodels.MetaModels.Issue_;

import metamodels.MetaModels;
import ua.com.fielden.platform.dao.IEntityDao;

/**
 * Companion object for entity {@link Issue}.
 *
 * @author Developers
 *
 */
public interface IssueCo extends IEntityDao<Issue> {

    static final IFetchProvider<Issue> FETCH_PROVIDER = EntityUtils.fetch(Issue.class)
    		.with(Issue_.issueNumber(), Issue_.active(), Issue_.date(), Issue_.description(), Issue_.responsiblePerson());
        
}
