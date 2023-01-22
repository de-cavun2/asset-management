package decavun2.security.tokens.persistent;

import static java.lang.String.format;

import decavun2.change.Issue;
import decavun2.security.tokens.ChangeModuleToken;
import ua.com.fielden.platform.reflection.TitlesDescsGetter;
import ua.com.fielden.platform.security.tokens.Template;

/**
 * A security token for entity {@link Issue} to guard Delete.
 *
 * @author Developers
 *
 */
public class Issue_CanDelete_Token extends ChangeModuleToken {
    public final static String TITLE = format(Template.DELETE.forTitle(), Issue.ENTITY_TITLE);
    public final static String DESC = format(Template.DELETE.forDesc(), Issue.ENTITY_TITLE);
}