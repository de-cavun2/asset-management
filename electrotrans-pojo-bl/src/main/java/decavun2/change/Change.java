package decavun2.change;

import ua.com.fielden.platform.entity.DynamicEntityKey;

import java.util.Date;

import decavun2.personnel.Person;
import ua.com.fielden.platform.entity.ActivatableAbstractEntity;
import ua.com.fielden.platform.entity.annotation.KeyType;
import ua.com.fielden.platform.entity.annotation.KeyTitle;
import ua.com.fielden.platform.entity.annotation.CompanionObject;
import ua.com.fielden.platform.entity.annotation.CompositeKeyMember;
import ua.com.fielden.platform.entity.annotation.MapEntityTo;
import ua.com.fielden.platform.entity.annotation.MapTo;
import ua.com.fielden.platform.entity.annotation.Observable;
import ua.com.fielden.platform.entity.annotation.Title;
import ua.com.fielden.platform.entity.annotation.mutator.BeforeChange;
import ua.com.fielden.platform.entity.annotation.mutator.Handler;
import ua.com.fielden.platform.entity.validation.MaxLengthValidator;
import ua.com.fielden.platform.entity.annotation.DescTitle;
import ua.com.fielden.platform.entity.annotation.DisplayDescription;
import ua.com.fielden.platform.entity.annotation.IsProperty;
import ua.com.fielden.platform.entity.annotation.DescRequired;
import ua.com.fielden.platform.reflection.TitlesDescsGetter;
import ua.com.fielden.platform.utils.Pair;

/**
 * Master entity object.
 *
 * @author Developers
 *
 */
@KeyType(DynamicEntityKey.class)
@KeyTitle("Change")
@CompanionObject(ChangeCo.class)
@MapEntityTo
@DescTitle("Change - a change that is to be implemented on the enterprise")
@DisplayDescription
@DescRequired
public class Change extends ActivatableAbstractEntity<DynamicEntityKey> {

    private static final Pair<String, String> entityTitleAndDesc = TitlesDescsGetter.getEntityTitleAndDesc(Change.class);
    public static final String ENTITY_TITLE = entityTitleAndDesc.getKey();
    public static final String ENTITY_DESC = entityTitleAndDesc.getValue();
    
    public static final int CHANGE_ID_LENGTH = 255;
    public static final int NAME_LENGTH = 100;
    public static final int DESC_LENGTH = 2000;
    
    @IsProperty(length = CHANGE_ID_LENGTH)
	@MapTo
	@Title(value = "Change Id", desc = "An id of the change")
	@CompositeKeyMember(1)
	@BeforeChange(@Handler(MaxLengthValidator.class))
	private String changeId;
    
    @IsProperty(length = NAME_LENGTH)
	@MapTo
	@Title(value = "Name", desc = "Name for this change")
    @BeforeChange(@Handler(MaxLengthValidator.class))
	private String name;
    
    @IsProperty(length = DESC_LENGTH)
	@MapTo
	@Title(value = "Desc", desc = "Description for this change")
    @BeforeChange(@Handler(MaxLengthValidator.class))
	private String desc;
    
    @IsProperty
	@MapTo
	@Title(value = "Managed By", desc = "A person who is managing the change")
	private Person managedBy;
    
    @IsProperty
	@MapTo
	@Title(value = "Created At", desc = "The date at which this change entry was created")
	private Date createAt;
    
    @IsProperty
	@MapTo
	@Title(value = "Status", desc = "Status of the current change")
	private String status;

	@Observable
	public Change setStatus(final String status) {
		this.status = status;
		return this;
	}

	public String getStatus() {
		return status;
	}

	


	@Observable
	public Change setCreatedAt(final Date createAt) {
		this.createAt = createAt;
		return this;
	}

	public Date getCreatedAt() {
		return createAt;
	}

	


	@Observable
	public Change setManagedBy(final Person managedBy) {
		this.managedBy = managedBy;
		return this;
	}

	public Person getManagedBy() {
		return managedBy;
	}

	


	@Observable
	public Change setDesc(final String desc) {
		this.desc = desc;
		return this;
	}

	public String getDesc() {
		return desc;
	}

	


	@Observable
	public Change setName(final String name) {
		this.name = name;
		return this;
	}

	public String getName() {
		return name;
	}

	


	@Observable
	public Change setChangeId(final String changeId) {
		this.changeId = changeId;
		return this;
	}

	public String getChangeId() {
		return changeId;
	}

	


}
