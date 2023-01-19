package decavun2.security.tokens.persistent;

import static java.lang.String.format;

import ua.com.fielden.platform.reflection.TitlesDescsGetter;
import ua.com.fielden.platform.security.tokens.Template;
import decavun2.change.Issue;

/**
 * A security token for entity {@link Issue} to guard Save.
 *
 * @author Developers
 *
 */
public class Issue_CanSave_Token extends ???ModuleToken {
    public final static String TITLE = format(Template.SAVE.forTitle(), Issue.ENTITY_TITLE);
    public final static String DESC = format(Template.SAVE.forDesc(), Issue.ENTITY_TITLE);
}