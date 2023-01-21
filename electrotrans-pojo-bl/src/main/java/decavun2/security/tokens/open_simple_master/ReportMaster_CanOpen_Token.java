package decavun2.security.tokens.open_simple_master;

import static java.lang.String.format;

import decavun2.change.Report;
import decavun2.security.tokens.ChangeModuleToken;
import ua.com.fielden.platform.security.tokens.Template;

/**
 * A security token for entity {@link Report} to guard Open.
 *
 * @author Developers
 *
 */
public class ReportMaster_CanOpen_Token extends ChangeModuleToken {
    public final static String TITLE = format(Template.MASTER_OPEN.forTitle(), Report.ENTITY_TITLE + " Master");
    public final static String DESC = format(Template.MASTER_OPEN.forDesc(), Report.ENTITY_TITLE);
}