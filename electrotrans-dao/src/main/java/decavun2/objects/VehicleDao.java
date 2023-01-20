package decavun2.objects;

import com.google.inject.Inject;

import java.util.Collection;
import java.util.List;

import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.security.Authorise;
import ua.com.fielden.platform.dao.annotations.SessionRequired;
import decavun2.security.tokens.persistent.Vehicle_CanSave_Token;
import decavun2.security.tokens.persistent.Vehicle_CanDelete_Token;
import ua.com.fielden.platform.dao.CommonEntityDao;
import ua.com.fielden.platform.entity.query.IFilter;
import ua.com.fielden.platform.error.Result;
import ua.com.fielden.platform.entity.annotation.EntityType;

/**
 * DAO implementation for companion object {@link VehicleCo}.
 *
 * @author Developers
 *
 */
@EntityType(Vehicle.class)
public class VehicleDao extends CommonEntityDao<Vehicle> implements VehicleCo {

    @Inject
    public VehicleDao(final IFilter filter) {
        super(filter);
    }
    
    @Override
    public Vehicle new_() {
        return super.new_().setActive(true);
    }

    @Override
    @SessionRequired
    @Authorise(Vehicle_CanSave_Token.class)
    public Vehicle save(Vehicle vehicle) {
        
        vehicle.isValid().ifFailure(Result::throwRuntime);
        final boolean wasPersisted = vehicle.isPersisted();
        
        try {
            final var savedVehicle = super.save(vehicle);

            if (!wasPersisted) {
                final var co$VehicleFinDet = co$(VehicleFinDet.class);
                final var vehicleFinDet = co$VehicleFinDet.new_().setKey(savedVehicle);
                co$VehicleFinDet.save(vehicleFinDet);
            }

            return savedVehicle;
        } catch (final Exception ex) {
            throw ex;
        }
    }

    @Override
    @SessionRequired
    @Authorise(Vehicle_CanDelete_Token.class)
    public int batchDelete(final Collection<Long> entitiesIds) {
        final VehicleFinDetCo vehicleFinDetCo = co(VehicleFinDet.class);
        vehicleFinDetCo.batchDelete(entitiesIds);
        return defaultBatchDelete(entitiesIds);
    }

    @Override
    @SessionRequired
    @Authorise(Vehicle_CanDelete_Token.class)
    public int batchDelete(final List<Vehicle> entities) {
        final VehicleFinDetCo vehicleFinDetCo = co(VehicleFinDet.class);
        vehicleFinDetCo.batchDelete(entities.stream().map(Vehicle::getId).toList());
        return defaultBatchDelete(entities);
    }

    @Override
    protected IFetchProvider<Vehicle> createFetchProvider() {
        return FETCH_PROVIDER;
    }
}