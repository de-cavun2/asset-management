package decavun2.objects;

import static metamodels.MetaModels.VehicleFinDet_;

import ua.com.fielden.platform.dao.IEntityDao;
import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.utils.EntityUtils;

/**
 * Companion object for entity {@link VehicleFinDet}.
 *
 * @author Developers
 *
 */
public interface VehicleFinDetCo extends IEntityDao<VehicleFinDet> {

    static final IFetchProvider<VehicleFinDet> FETCH_PROVIDER = EntityUtils.fetch(VehicleFinDet.class)
            .with(VehicleFinDet_.key(), VehicleFinDet_.initCost(), VehicleFinDet_.commissionDate(), VehicleFinDet_.disposalDate());

}
