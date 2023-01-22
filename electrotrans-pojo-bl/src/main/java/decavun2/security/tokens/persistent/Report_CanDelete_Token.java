package decavun2.security.tokens.persistent;

import static java.lang.String.format;

import decavun2.change.Report;
import decavun2.security.tokens.ChangeModuleToken;
import ua.com.fielden.platform.security.tokens.Template;

/**
 * A security token for entity {@link Report} to guard Delete.
 *
 * @author Developers
 *
 */
public class Report_CanDelete_Token extends ChangeModuleToken {
    public final static String TITLE = format(Template.DELETE.forTitle(), Report.ENTITY_TITLE);
    public final static String DESC = format(Template.DELETE.forDesc(), Report.ENTITY_TITLE);
}