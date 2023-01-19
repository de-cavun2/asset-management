package decavun2.example;

import static org.junit.Assert.*;

import org.junit.Test;

import ua.com.fielden.platform.test.ioc.UniversalConstantsForTesting;
import ua.com.fielden.platform.utils.IUniversalConstants;

import decavun2.personnel.Person;
import decavun2.test_config.AbstractDomainTestCase;

/**
 * This is an example unit test, which can be used as a starting point for creating application unit tests.
 *
 * @author Generated
 */
public class ReportTest extends AbstractDomainTestCase {

    @Override
    public boolean saveDataPopulationScriptToFile() {
        return false;
    }


    @Override
    public boolean useSavedDataPopulationScript() {
        return false;
    }


    @Override
    protected void populateDomain() {
        super.populateDomain();

      
        if (useSavedDataPopulationScript()) {
            return;
        }

        // Here the three Person entities are persisted using the the inherited from TG testing framework methods.
        save(new_(Person.class).setEmail("RMD@organisation.com").setDesc("Ronald McDonald").setActive(true));
        save(new_(Person.class).setEmail("JC@organisation.com").setDesc("John Carmack").setActive(false));
    }

}
