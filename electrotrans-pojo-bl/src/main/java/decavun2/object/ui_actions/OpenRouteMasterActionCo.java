package decavun2.object.ui_actions;

import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.utils.EntityUtils;
import ua.com.fielden.platform.dao.IEntityDao;

/**
 * Companion object for entity {@link OpenRouteMasterAction}.
 *
 * @author Developers
 *
 */
public interface OpenRouteMasterActionCo extends IEntityDao<OpenRouteMasterAction> {

    static final IFetchProvider<OpenRouteMasterAction> FETCH_PROVIDER = EntityUtils.fetch(OpenRouteMasterAction.class).with(
        // key is needed to be correctly autopopulated by newly saved compound master entity (ID-based restoration of entity-typed key)
        "key");

}
