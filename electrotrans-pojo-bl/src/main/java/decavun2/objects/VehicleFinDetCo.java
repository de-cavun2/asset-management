package decavun2.objects;

import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.utils.EntityUtils;
import ua.com.fielden.platform.dao.IEntityDao;

/**
 * Companion object for entity {@link VehicleFinDet}.
 *
 * @author Developers
 *
 */
public interface VehicleFinDetCo extends IEntityDao<VehicleFinDet> {

    static final IFetchProvider<VehicleFinDet> FETCH_PROVIDER = EntityUtils.fetch(VehicleFinDet.class).with(
        // TODO: uncomment the following line and specify the properties, which are required for the UI. Then remove the line after.
        // "key", "desc");
        "Please specify the properties, which are required for the UI");

}
