package decavun2.security.tokens.persistent;

import static java.lang.String.format;

import ua.com.fielden.platform.reflection.TitlesDescsGetter;
import ua.com.fielden.platform.security.tokens.Template;
import decavun2.change.Issue;

/**
 * A security token for entity {@link Issue} to guard Delete.
 *
 * @author Developers
 *
 */
public class Issue_CanDelete_Token extends ???ModuleToken {
    public final static String TITLE = format(Template.DELETE.forTitle(), Issue.ENTITY_TITLE);
    public final static String DESC = format(Template.DELETE.forDesc(), Issue.ENTITY_TITLE);
}