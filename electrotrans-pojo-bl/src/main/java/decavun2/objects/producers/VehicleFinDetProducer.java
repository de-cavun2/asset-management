package decavun2.objects.producers;

import com.google.inject.Inject;

import decavun2.objects.VehicleFinDet;
import decavun2.objects.VehicleFinDetCo;
import decavun2.objects.Vehicle;
import ua.com.fielden.platform.entity.DefaultEntityProducerWithContext;
import ua.com.fielden.platform.entity.factory.EntityFactory;
import ua.com.fielden.platform.entity.factory.ICompanionObjectFinder;
import ua.com.fielden.platform.error.Result;
/**
 * A producer for new instances of entity {@link VehicleFinDet}.
 *
 * @author Developers
 *
 */
public class VehicleFinDetProducer extends DefaultEntityProducerWithContext<VehicleFinDet> {

    @Inject
    public VehicleFinDetProducer(final EntityFactory factory, final ICompanionObjectFinder coFinder) {
        super(factory, VehicleFinDet.class, coFinder);
    }

    @Override
    protected VehicleFinDet provideDefaultValues(final VehicleFinDet entity) {
        if (keyOfMasterEntityInstanceOf(Vehicle.class)) {
            final Vehicle instance = keyOfMasterEntity(Vehicle.class);
            return co$(VehicleFinDet.class).findByKeyAndFetch(VehicleFinDetCo.FETCH_PROVIDER.fetchModel(), instance);
        } else {
            throw new Result("Not supported.");
        }
    }
}
