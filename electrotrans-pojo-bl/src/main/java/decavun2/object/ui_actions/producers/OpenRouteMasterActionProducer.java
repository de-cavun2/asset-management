package decavun2.object.ui_actions.producers;

import com.google.inject.Inject;

import decavun2.object.Route;
import decavun2.object.ui_actions.OpenRouteMasterAction;
import ua.com.fielden.platform.security.Authorise;
import decavun2.security.tokens.open_compound_master.OpenRouteMasterAction_CanOpen_Token;
import ua.com.fielden.platform.entity.AbstractProducerForOpenEntityMasterAction;
import ua.com.fielden.platform.entity.factory.EntityFactory;
import ua.com.fielden.platform.entity.factory.ICompanionObjectFinder;

/**
 * A producer for new instances of entity {@link OpenRouteMasterAction}.
 *
 * @author Developers
 *
 */
public class OpenRouteMasterActionProducer extends AbstractProducerForOpenEntityMasterAction<Route, OpenRouteMasterAction> {

    @Inject
    public OpenRouteMasterActionProducer(final EntityFactory factory, final ICompanionObjectFinder companionFinder) {
        super(factory, Route.class, OpenRouteMasterAction.class, companionFinder);
    }

    @Override
    @Authorise(OpenRouteMasterAction_CanOpen_Token.class)
    protected OpenRouteMasterAction provideDefaultValues(OpenRouteMasterAction openAction) {
        return super.provideDefaultValues(openAction);
    }
}
