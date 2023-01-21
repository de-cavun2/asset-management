package decavun2.change.ui_actions;

import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.utils.EntityUtils;
import ua.com.fielden.platform.dao.IEntityDao;

/**
 * Companion object for entity {@link OpenChangeMasterAction}.
 *
 * @author Developers
 *
 */
public interface OpenChangeMasterActionCo extends IEntityDao<OpenChangeMasterAction> {

    static final IFetchProvider<OpenChangeMasterAction> FETCH_PROVIDER = EntityUtils.fetch(OpenChangeMasterAction.class).with(
        // key is needed to be correctly autopopulated by newly saved compound master entity (ID-based restoration of entity-typed key)
        "key");

}
