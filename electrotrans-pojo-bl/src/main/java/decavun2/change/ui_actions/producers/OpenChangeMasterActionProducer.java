package decavun2.change.ui_actions.producers;

import com.google.inject.Inject;

import decavun2.change.Change;
import decavun2.change.ui_actions.OpenChangeMasterAction;
import ua.com.fielden.platform.security.Authorise;
import decavun2.security.tokens.open_compound_master.OpenChangeMasterAction_CanOpen_Token;
import ua.com.fielden.platform.entity.AbstractProducerForOpenEntityMasterAction;
import ua.com.fielden.platform.entity.factory.EntityFactory;
import ua.com.fielden.platform.entity.factory.ICompanionObjectFinder;

/**
 * A producer for new instances of entity {@link OpenChangeMasterAction}.
 *
 * @author Developers
 *
 */
public class OpenChangeMasterActionProducer extends AbstractProducerForOpenEntityMasterAction<Change, OpenChangeMasterAction> {

    @Inject
    public OpenChangeMasterActionProducer(final EntityFactory factory, final ICompanionObjectFinder companionFinder) {
        super(factory, Change.class, OpenChangeMasterAction.class, companionFinder);
    }

    @Override
    @Authorise(OpenChangeMasterAction_CanOpen_Token.class)
    protected OpenChangeMasterAction provideDefaultValues(OpenChangeMasterAction openAction) {
        return super.provideDefaultValues(openAction);
    }
}
