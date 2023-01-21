package decavun2.objects;


import java.util.Date;

import decavun2.personnel.Person;
import ua.com.fielden.platform.entity.ActivatableAbstractEntity;
import ua.com.fielden.platform.entity.DynamicEntityKey;
import ua.com.fielden.platform.entity.annotation.CompanionObject;
import ua.com.fielden.platform.entity.annotation.CompositeKeyMember;
import ua.com.fielden.platform.entity.annotation.DescRequired;
import ua.com.fielden.platform.entity.annotation.DescTitle;
import ua.com.fielden.platform.entity.annotation.DisplayDescription;
import ua.com.fielden.platform.entity.annotation.IsProperty;
import ua.com.fielden.platform.entity.annotation.KeyTitle;
import ua.com.fielden.platform.entity.annotation.KeyType;
import ua.com.fielden.platform.entity.annotation.MapEntityTo;
import ua.com.fielden.platform.entity.annotation.MapTo;
import ua.com.fielden.platform.entity.annotation.Observable;
import ua.com.fielden.platform.entity.annotation.Readonly;
import ua.com.fielden.platform.entity.annotation.Required;
import ua.com.fielden.platform.entity.annotation.Title;
import ua.com.fielden.platform.entity.annotation.mutator.BeforeChange;
import ua.com.fielden.platform.entity.annotation.mutator.Handler;
import ua.com.fielden.platform.entity.validation.MaxLengthValidator;
import ua.com.fielden.platform.reflection.TitlesDescsGetter;
import ua.com.fielden.platform.utils.Pair;

/**
 * Master entity object.
 * @author Developers
 *
 */
@KeyType(DynamicEntityKey.class)
@KeyTitle("Repair ID")
@CompanionObject(RepairCo.class)
@MapEntityTo
@DescTitle("Description of Repair")
@DisplayDescription
@DescRequired
public class Repair extends ActivatableAbstractEntity<DynamicEntityKey> {

    private static final Pair<String, String> entityTitleAndDesc = TitlesDescsGetter.getEntityTitleAndDesc(Repair.class);
    public static final String ENTITY_TITLE = entityTitleAndDesc.getKey();
    public static final String ENTITY_DESC = entityTitleAndDesc.getValue();
    
    
    @IsProperty(length = 255)
	@MapTo
	@Title(value = "Repair ID", desc = "ID of this repair")
	@CompositeKeyMember(1)
	@BeforeChange(@Handler(MaxLengthValidator.class))
	private String repairID;
    

    
    @IsProperty
	@MapTo
	@Title(value = "Vehicle", desc = "Vehicle that is related to this repair")
	private Vehicle vehicle;

    
    @IsProperty
	@MapTo
	@Title(value = "Created At", desc = "The time this Repair entry was created")
    @Readonly
	private Date createdAt;

	@Observable
	public Repair setCreatedAt(final Date createdAt) {
		this.createdAt = createdAt;
		return this;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	

    
	@Observable
	public Repair setVehicle(final Vehicle vehicle) {
		this.vehicle = vehicle;
		return this;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}



    @IsProperty
    @MapTo
    @Required
    @Title(value = "Issue", desc = "Issue that has been resolved.")
    private String issue;

    @IsProperty
    @MapTo
    @Required
    @Title(value = "Repairman", desc = "The repairman responsible for the repair.")
    private Person repairman;
    
    @Observable
    public Repair setRepairID(final String id) {
       this.repairID = id;
       return this;
    }

    public String getRepairID() {
       return repairID;
    }


    @Observable
    public Repair setIssue(final String issue) {
       this.issue = issue;
       return this;
    }

    public String getIssue() {
       return issue;
    }

    @Observable
    public Repair setRepairman(final Person repairman) {
       this.repairman = repairman;
       return this;
    }

    public Person getRepairman() {
       return repairman;
    }

}
