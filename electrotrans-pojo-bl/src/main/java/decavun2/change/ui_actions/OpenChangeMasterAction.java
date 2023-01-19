package decavun2.change.ui_actions;

import decavun2.change.Change;
import decavun2.change.Issue;
import decavun2.change.Report;
import ua.com.fielden.platform.entity.AbstractFunctionalEntityToOpenCompoundMaster;
import ua.com.fielden.platform.entity.annotation.KeyType;
import ua.com.fielden.platform.entity.annotation.EntityTitle;
import ua.com.fielden.platform.entity.annotation.CompanionObject;
import ua.com.fielden.platform.reflection.TitlesDescsGetter;
import ua.com.fielden.platform.utils.Pair;

/**
 * Open Master Action entity object.
 *
 * @author Developers
 *
 */
@KeyType(Change.class)
@CompanionObject(OpenChangeMasterActionCo.class)
@EntityTitle("Change Master")
public class OpenChangeMasterAction extends AbstractFunctionalEntityToOpenCompoundMaster<Change> {

    private static final Pair<String, String> entityTitleAndDesc = TitlesDescsGetter.getEntityTitleAndDesc(OpenChangeMasterAction.class);
    public static final String ENTITY_TITLE = entityTitleAndDesc.getKey();
    public static final String ENTITY_DESC = entityTitleAndDesc.getValue();

    public static final String MAIN = "Main";
    public static final String ISSUES = Issue.ENTITY_TITLE + "s";
    public static final String REPORTS = Report.ENTITY_TITLE + "s";
}
