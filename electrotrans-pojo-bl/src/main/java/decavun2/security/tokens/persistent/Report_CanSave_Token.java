package decavun2.security.tokens.persistent;

import static java.lang.String.format;

import decavun2.change.Report;
import decavun2.security.tokens.ChangeModuleToken;
import ua.com.fielden.platform.security.tokens.Template;

/**
 * A security token for entity {@link Report} to guard Save.
 *
 * @author Developers
 *
 */
public class Report_CanSave_Token extends ChangeModuleToken {
    public final static String TITLE = format(Template.SAVE.forTitle(), Report.ENTITY_TITLE);
    public final static String DESC = format(Template.SAVE.forDesc(), Report.ENTITY_TITLE);
}