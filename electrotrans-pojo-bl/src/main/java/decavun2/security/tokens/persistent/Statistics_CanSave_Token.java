package decavun2.security.tokens.persistent;

import static java.lang.String.format;

import ua.com.fielden.platform.reflection.TitlesDescsGetter;
import ua.com.fielden.platform.security.tokens.Template;
import decavun2.analysis.Statistics;
import decavun2.security.tokens.AnalysisModuleToken;

/**
 * A security token for entity {@link Statistics} to guard Save.
 *
 * @author Developers
 *
 */
public class Statistics_CanSave_Token extends AnalysisModuleToken {
    public final static String TITLE = format(Template.SAVE.forTitle(), Statistics.ENTITY_TITLE);
    public final static String DESC = format(Template.SAVE.forDesc(), Statistics.ENTITY_TITLE);
}