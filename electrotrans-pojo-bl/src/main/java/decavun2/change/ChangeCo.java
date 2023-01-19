package decavun2.change;

import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.utils.EntityUtils;

import static metamodels.MetaModels.Change_;

import ua.com.fielden.platform.dao.IEntityDao;

/**
 * Companion object for entity {@link Change}.
 *
 * @author Developers
 *
 */
public interface ChangeCo extends IEntityDao<Change> {

    static final IFetchProvider<Change> FETCH_PROVIDER = EntityUtils.fetch(Change.class).with(
    		Change_.name(), Change_.desc());

}
