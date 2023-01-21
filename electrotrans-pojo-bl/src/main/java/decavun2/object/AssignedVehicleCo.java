package decavun2.object;

import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.utils.EntityUtils;

import static metamodels.MetaModels.AssignedVehicle_;

import ua.com.fielden.platform.dao.IEntityDao;

/**
 * Companion object for entity {@link AssignedVehicle}.
 * @author Developers
 */
public interface AssignedVehicleCo extends IEntityDao<AssignedVehicle> {
	
    final String ERROR_MSG_REQUIRED_PROPERTIES = "Required property [%s] is not specified for entity [%s].".formatted("Station schedule", "Assigned vehicle");


    static final IFetchProvider<AssignedVehicle> FETCH_PROVIDER = EntityUtils.fetch(AssignedVehicle.class)
    		.with(AssignedVehicle_.route(), AssignedVehicle_.assignedDate(), AssignedVehicle_.vehicle(),
    				AssignedVehicle_.stationSchedule(), AssignedVehicle_.interval(), AssignedVehicle_.active());
       

}
