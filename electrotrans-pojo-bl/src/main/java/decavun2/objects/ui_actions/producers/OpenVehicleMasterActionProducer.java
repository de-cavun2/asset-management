package decavun2.objects.ui_actions.producers;

import com.google.inject.Inject;

import decavun2.objects.Vehicle;
import decavun2.objects.ui_actions.OpenVehicleMasterAction;
import ua.com.fielden.platform.security.Authorise;
import decavun2.security.tokens.open_compound_master.OpenVehicleMasterAction_CanOpen_Token;
import ua.com.fielden.platform.entity.AbstractProducerForOpenEntityMasterAction;
import ua.com.fielden.platform.entity.factory.EntityFactory;
import ua.com.fielden.platform.entity.factory.ICompanionObjectFinder;

/**
 * A producer for new instances of entity {@link OpenVehicleMasterAction}.
 *
 * @author Developers
 *
 */
public class OpenVehicleMasterActionProducer extends AbstractProducerForOpenEntityMasterAction<Vehicle, OpenVehicleMasterAction> {

    @Inject
    public OpenVehicleMasterActionProducer(final EntityFactory factory, final ICompanionObjectFinder companionFinder) {
        super(factory, Vehicle.class, OpenVehicleMasterAction.class, companionFinder);
    }

    @Override
    @Authorise(OpenVehicleMasterAction_CanOpen_Token.class)
    protected OpenVehicleMasterAction provideDefaultValues(OpenVehicleMasterAction openAction) {
        return super.provideDefaultValues(openAction);
    }
}
