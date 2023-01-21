package decavun2.security.tokens.open_simple_master;

import static java.lang.String.format;
import decavun2.objects.DriverReport;

import ua.com.fielden.platform.security.tokens.Template;
import decavun2.security.tokens.UsersAndPersonnelModuleToken;

/**
 * A security token for entity {@link DriverReport} to guard Open.
 *
 * @author Developers
 *
 */
public class DriverReportMaster_CanOpen_Token extends UsersAndPersonnelModuleToken {
    public final static String TITLE = format(Template.MASTER_OPEN.forTitle(), DriverReport.ENTITY_TITLE + " Master");
    public final static String DESC = format(Template.MASTER_OPEN.forDesc(), DriverReport.ENTITY_TITLE);
}
