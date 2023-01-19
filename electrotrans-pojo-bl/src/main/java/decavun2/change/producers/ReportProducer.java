package decavun2.change.producers;

import com.google.inject.Inject;

import decavun2.change.Change;
import decavun2.change.Report;
import ua.com.fielden.platform.entity.DefaultEntityProducerWithContext;
import ua.com.fielden.platform.entity.EntityNewAction;
import ua.com.fielden.platform.entity.factory.EntityFactory;
import ua.com.fielden.platform.entity.factory.ICompanionObjectFinder;
import ua.com.fielden.platform.error.Result;
/**
 * A producer for new instances of entity {@link Report}.
 *
 * @author Developers
 *
 */
public class ReportProducer extends DefaultEntityProducerWithContext<Report> {

    @Inject
    public ReportProducer(final EntityFactory factory, final ICompanionObjectFinder coFinder) {
        super(factory, Report.class, coFinder);
    }

    @Override
    protected Report provideDefaultValuesForStandardNew(final Report entityIn, final EntityNewAction masterEntity) {
        final Report entityOut = super.provideDefaultValuesForStandardNew(entityIn, masterEntity);
        // This producer can be invoked from two places:
        // 1. Standalone centre
        // 2. Centre embedded in Change Master
        // In the second case we want to default the change and make it read-only
        if (ofMasterEntity().keyOfMasterEntityInstanceOf(Change.class)) {
            final Change shallowChange = ofMasterEntity().keyOfMasterEntity(Change.class);
            // shallowChange has been fetched in OpenChangeMasterActionProducer with key and desc only
            // It needs to be re-fetched here using a slightly deeper fetch model, as appropriate for CocEntry
            entityOut.setChange(refetch(shallowChange, "change"));
            entityOut.getProperty("change").validationResult().ifFailure(Result::throwRuntime);
            entityOut.getProperty("change").setEditable(false);
        }
        return entityOut;
    }
}
