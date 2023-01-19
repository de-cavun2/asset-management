package decavun2.objects;

import ua.com.fielden.platform.entity.DynamicEntityKey;
import ua.com.fielden.platform.entity.AbstractPersistentEntity;
import ua.com.fielden.platform.entity.annotation.KeyType;
import ua.com.fielden.platform.entity.annotation.KeyTitle;
import ua.com.fielden.platform.entity.annotation.CompanionObject;
import ua.com.fielden.platform.entity.annotation.CompositeKeyMember;
import ua.com.fielden.platform.entity.annotation.DescTitle;
import ua.com.fielden.platform.entity.annotation.EntityTitle;
import ua.com.fielden.platform.entity.annotation.IsProperty;
import ua.com.fielden.platform.entity.annotation.MapEntityTo;
import ua.com.fielden.platform.entity.annotation.MapTo;
import ua.com.fielden.platform.entity.annotation.Observable;
import ua.com.fielden.platform.entity.annotation.Required;
import ua.com.fielden.platform.entity.annotation.Title;
import ua.com.fielden.platform.reflection.TitlesDescsGetter;
import ua.com.fielden.platform.utils.Pair;

/**
 * Master entity object.
 *
 * @author Developers
 *
 */
@KeyType(DynamicEntityKey.class)
@KeyTitle("Condition Id")
@CompanionObject(TransportConditionCo.class)
@EntityTitle(value = "Transport condition", desc = "Describes the condition state of the transport.")
@DescTitle(value = "Transport condition's description", desc = "The extended description for the entity TransportCondition.")
@MapEntityTo
public class TransportCondition extends AbstractPersistentEntity<DynamicEntityKey> {

    private static final Pair<String, String> entityTitleAndDesc = TitlesDescsGetter.getEntityTitleAndDesc(TransportCondition.class);
    public static final String ENTITY_TITLE = entityTitleAndDesc.getKey();
    public static final String ENTITY_DESC = entityTitleAndDesc.getValue();

    @IsProperty
    @MapTo
    @Title(value = "Condition Id", desc = "The unique transportConditon's identifier.")
    @CompositeKeyMember(1)
    private String conditionId;
    
    @IsProperty
    @MapTo
    @Required
    @Title(value = "Stage", desc = "Describes the specific condition state of the vehicle.")
    private String stage;

    @Observable
    public TransportCondition setStage(final String stage) {
       this.stage = stage;
       return this;
    }

    public String getStage() {
       return stage;
    }

    @Observable
    public TransportCondition setConditionId(final String conditionId) {
       this.conditionId = conditionId;
       return this;
	}	

    public String getConditionId() {
       return conditionId;
    }

}
