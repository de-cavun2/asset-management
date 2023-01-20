package decavun2.security.tokens.persistent;

import static java.lang.String.format;

import ua.com.fielden.platform.security.tokens.Template;
import decavun2.objects.DriverReport;
import decavun2.security.tokens.UsersAndPersonnelModuleToken;

/**
 * A security token for entity {@link DriverReport} to guard Save.
 *
 * @author Developers
 *
 */
public class DriverReport_CanSave_Token extends UsersAndPersonnelModuleToken {
    public final static String TITLE = format(Template.SAVE.forTitle(), DriverReport.ENTITY_TITLE);
    public final static String DESC = format(Template.SAVE.forDesc(), DriverReport.ENTITY_TITLE);
}