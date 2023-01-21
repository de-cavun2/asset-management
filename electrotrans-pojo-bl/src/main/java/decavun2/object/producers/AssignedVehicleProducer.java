package decavun2.object.producers;

import static metamodels.MetaModels.AssignedVehicle_;

import com.google.inject.Inject;

import decavun2.object.Route;
import decavun2.object.AssignedVehicle;
import ua.com.fielden.platform.entity.DefaultEntityProducerWithContext;
import ua.com.fielden.platform.entity.EntityNewAction;
import ua.com.fielden.platform.entity.factory.EntityFactory;
import ua.com.fielden.platform.entity.factory.ICompanionObjectFinder;
import ua.com.fielden.platform.error.Result;
/**
 * A producer for new instances of entity {@link AssignedVehicle}.
 *
 * @author Developers
 *
 */
public class AssignedVehicleProducer extends DefaultEntityProducerWithContext<AssignedVehicle> {

    @Inject
    public AssignedVehicleProducer(final EntityFactory factory, final ICompanionObjectFinder coFinder) {
        super(factory, AssignedVehicle.class, coFinder);
    }

    @Override
    protected AssignedVehicle provideDefaultValuesForStandardNew(final AssignedVehicle avIn, final EntityNewAction masterEntity) {
        final AssignedVehicle avOut = super.provideDefaultValuesForStandardNew(avIn, masterEntity);
        // This producer can be invoked from two places:
        // 1. Standalone centre -- not our case, because we don't have a standalone cantre for AssignedVehicle.
        // 2. Centre embedded in Route Master
        // In the second case we want to default the route and make it read-only
        if (ofMasterEntity().keyOfMasterEntityInstanceOf(Route.class)) {
            final Route shallowRoute = ofMasterEntity().keyOfMasterEntity(Route.class);
            // shallowRoute has been fetched in OpenRouteMasterActionProducer with key and desc only
            // It needs to be re-fetched here using a slightly deeper fetch model, as appropriate for AssignedVehicle
            avOut.setRoute(refetch(shallowRoute, AssignedVehicle_.route()));
            avOut.getProperty(AssignedVehicle_.route()).validationResult().ifFailure(Result::throwRuntime);
            avOut.getProperty(AssignedVehicle_.route()).setEditable(false);
        }
        return avOut;
    }
}
