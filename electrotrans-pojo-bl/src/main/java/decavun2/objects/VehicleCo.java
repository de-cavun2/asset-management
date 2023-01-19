package decavun2.objects;

import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.utils.EntityUtils;

import static metamodels.MetaModels.Vehicle_;
import ua.com.fielden.platform.dao.IEntityDao;

/**
 * Companion object for entity {@link Vehicle}.
 *
 * @author Developers
 *
 */
public interface VehicleCo extends IEntityDao<Vehicle> {

    static final IFetchProvider<Vehicle> FETCH_PROVIDER = EntityUtils.fetch(Vehicle.class)
    		.with(Vehicle_.licensePlate(), Vehicle_.model(), Vehicle_.currentLocation(),
    			  Vehicle_.driver(), Vehicle_.desc(), Vehicle_.active(), Vehicle_.lastRepair(), Vehicle_.transportCondition());
     
}