package decavun2.security.tokens.persistent;

import static java.lang.String.format;

import decavun2.change.Issue;
import decavun2.security.tokens.ChangeModuleToken;
import ua.com.fielden.platform.reflection.TitlesDescsGetter;
import ua.com.fielden.platform.security.tokens.Template;

/**
 * A security token for entity {@link Issue} to guard Save.
 *
 * @author Developers
 *
 */
public class Issue_CanSave_Token extends ChangeModuleToken {
    public final static String TITLE = format(Template.SAVE.forTitle(), Issue.ENTITY_TITLE);
    public final static String DESC = format(Template.SAVE.forDesc(), Issue.ENTITY_TITLE);
}