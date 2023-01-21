package decavun2.change.master.menu.actions;

import decavun2.change.Change;
import ua.com.fielden.platform.entity.AbstractFunctionalEntityForCompoundMenuItem;
import ua.com.fielden.platform.entity.annotation.KeyType;
import ua.com.fielden.platform.entity.annotation.EntityTitle;
import ua.com.fielden.platform.entity.annotation.CompanionObject;
import ua.com.fielden.platform.reflection.TitlesDescsGetter;
import ua.com.fielden.platform.utils.Pair;

/**
 * Master entity object to model the detail menu item of the compound master entity object.
 *
 * @author Developers
 *
 */
@KeyType(Change.class)
@CompanionObject(ChangeMaster_OpenIssue_MenuItemCo.class)
@EntityTitle("Change Master Issue Menu Item")
public class ChangeMaster_OpenIssue_MenuItem extends AbstractFunctionalEntityForCompoundMenuItem<Change> {

    private static final Pair<String, String> entityTitleAndDesc = TitlesDescsGetter.getEntityTitleAndDesc(ChangeMaster_OpenIssue_MenuItem.class);
    public static final String ENTITY_TITLE = entityTitleAndDesc.getKey();
    public static final String ENTITY_DESC = entityTitleAndDesc.getValue();

}
