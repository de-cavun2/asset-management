package decavun2.test_config;

import static org.apache.logging.log4j.LogManager.getLogger;

import org.apache.logging.log4j.Logger;
import org.junit.Test;

import ua.com.fielden.platform.test_data.EnsureData;
import decavun2.data.IDomainData;

/**
 * A test-case that is responsible for building the test environment for other tests, which includes:
 * <ul>
 * <li> Generating DDL and persisting it into a file for reuse by test cases.
 * <li> Scripting data diffs for various data-population methods, those annotated with {@link EnsureData}, and saving of those scripts into files for reuse by test cases.
 * </ul>
 *
 * @author Generated
 */
public class BuildEnvironment extends AbstractDomainTestCase implements IDomainData {

    private final Logger logger = getLogger(BuildEnvironment.class);

    public BuildEnvironment() {
        logger.info("BUILD-TEST-ENVIRONMENT... BUILDING...");
    }

    @Test
    public void toKickInTheBuild() {
        logger.info("BUILD-TEST-ENVIRONMENT... DONE!");
    }

    @Override
    protected void populateDomain() {
        // no need to call anything
    }

}
