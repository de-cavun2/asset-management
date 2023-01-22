package decavun2.security.tokens.open_simple_master;

import static java.lang.String.format;

import ua.com.fielden.platform.reflection.TitlesDescsGetter;
import ua.com.fielden.platform.security.tokens.Template;
import decavun2.analysis.Statistics;
import decavun2.security.tokens.AnalysisModuleToken;

/**
 * A security token for entity {@link Statistics} to guard Open.
 *
 * @author Developers
 *
 */
public class StatisticsMaster_CanOpen_Token extends AnalysisModuleToken {
    public final static String TITLE = format(Template.MASTER_OPEN.forTitle(), Statistics.ENTITY_TITLE + " Master");
    public final static String DESC = format(Template.MASTER_OPEN.forDesc(), Statistics.ENTITY_TITLE);
}