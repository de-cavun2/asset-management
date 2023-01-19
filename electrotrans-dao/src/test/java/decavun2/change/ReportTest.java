package decavun2.change;

import static metamodels.MetaModels.Report_;
import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import ua.com.fielden.platform.entity.meta.MetaProperty;
import ua.com.fielden.platform.test.ioc.UniversalConstantsForTesting;
import ua.com.fielden.platform.utils.IUniversalConstants;

import decavun2.personnel.Person;
import decavun2.test_config.AbstractDomainTestCase;
import metamodels.MetaModels;

/**
 * This is an example unit test, which can be used as a starting point for creating application unit tests.
 *
 * @author Generated
 */
public class ReportTest extends AbstractDomainTestCase {
	
	final static String rp_title = "Profitability calculation of IS for the enterprise";
	
	@Test
	public void newly_created_instances_have_title_desc_createdAt_chnage_issue_createdBy_reauired() {
		final var rp = co(Report.class).new_();
	
		final MetaProperty<Report> mpTitle = rp.getProperty(Report_.title());
		final MetaProperty<Report> mpDesc = rp.getProperty(Report_.title());
		final MetaProperty<Report> mpCreatedAt = rp.getProperty(Report_.title());
		final MetaProperty<Report> mpChange = rp.getProperty(Report_.title());
		final MetaProperty<Report> mpIssue = rp.getProperty(Report_.title());
		
		assertTrue(mpTitle.isRequired());
		assertTrue(mpDesc.isRequired());
		assertTrue(mpCreatedAt.isRequired());
		assertTrue(mpChange.isRequired());
		assertTrue(mpIssue.isRequired());
	}
	
	@Test
	public void user_cannot_specify_more_characters_for_title_and_desc_than_max_length() {
		final var rp = co(Report.class).new_();
		final MetaProperty<Report> mpTitle = rp.getProperty(Report_.title());
		final MetaProperty<Report> mpDesc = rp.getProperty(Report_.title());
		
		String invalidTitle = "";
		for (int i=0; i < Report.TITLE_LENGTH + 1; i++) {
			invalidTitle += "c";
		}
		
		rp.setTitle(invalidTitle);
		assertFalse(mpTitle.isValid());
		
		String invalidDesc = "";
		for (int i=0; i < Report.DESC_LENGTH + 1; i++) {
			invalidTitle += "c";
		}
		
		rp.setTitle(invalidDesc);
		assertFalse(mpDesc.isValid());
	}
	
	@Test
	public void createdAt_should_be_automatically_set_to_the_time_of_creation() {
		final var rp = co(Report.class).new_();
		final Date currentDate  = new Date();
		final int eps = 100 * 60; // one minute in miliseconds
		assertTrue(currentDate.getTime() - rp.getCreatedAt().getTime() < eps);
	}
	
	@Test
	public void when_one_source_property_is_set_another_becomes_inactive() {
		final Report rp = co(Report.class).findByKey(rp_title);
		assertNotNull(rp);
		
		final MetaProperty<Report> mpChange = rp.getProperty(Report_.title());
		final MetaProperty<Report> mpIssue = rp.getProperty(Report_.title());
		assertTrue(mpChange.isRequired());
		assertTrue(mpIssue.isRequired());
		
		
		assertNull(rp.getChange());
		assertNull(rp.getIssue());
		
		rp.setChange("Some change");
		assertFalse(mpIssue.isRequired());
		
		rp.setIssue("Some issue");
		assertNull(rp.getChange());
		assertFalse(mpChange.isRequired());
	}

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
        
        final Person person = save(new_(Person.class).setEmail("RMD@organisation.com").setDesc("Ronald McDonald").setActive(true));

        final Report rp = new_(Report.class);
        rp.setTitle(rp_title);
        rp.setDesc("This description should portray pluses and minuses of proposed change");
        rp.setDepartment("Financial Department");
        
        rp.setChange("Some change");
        rp.setIssue("Some Issue");
        rp.setPerson(person);
        
        save(rp);
    }

}
