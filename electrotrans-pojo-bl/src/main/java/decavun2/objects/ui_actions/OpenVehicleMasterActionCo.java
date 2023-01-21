package decavun2.objects.ui_actions;

import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.utils.EntityUtils;
import ua.com.fielden.platform.dao.IEntityDao;

/**
 * Companion object for entity {@link OpenVehicleMasterAction}.
 *
 * @author Developers
 *
 */
public interface OpenVehicleMasterActionCo extends IEntityDao<OpenVehicleMasterAction> {

    static final IFetchProvider<OpenVehicleMasterAction> FETCH_PROVIDER = EntityUtils.fetch(OpenVehicleMasterAction.class).with(
        // key is needed to be correctly autopopulated by newly saved compound master entity (ID-based restoration of entity-typed key)
        "key");

}
