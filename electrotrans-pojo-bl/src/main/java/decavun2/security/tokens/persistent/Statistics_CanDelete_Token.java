package decavun2.security.tokens.persistent;

import static java.lang.String.format;

import ua.com.fielden.platform.reflection.TitlesDescsGetter;
import ua.com.fielden.platform.security.tokens.Template;
import decavun2.analysis.Statistics;
import decavun2.security.tokens.AnalysisModuleToken;

/**
 * A security token for entity {@link Statistics} to guard Delete.
 *
 * @author Developers
 *
 */
public class Statistics_CanDelete_Token extends AnalysisModuleToken {
    public final static String TITLE = format(Template.DELETE.forTitle(), Statistics.ENTITY_TITLE);
    public final static String DESC = format(Template.DELETE.forDesc(), Statistics.ENTITY_TITLE);
}