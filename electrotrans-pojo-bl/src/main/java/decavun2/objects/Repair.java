package decavun2.objects;

import ua.com.fielden.platform.entity.DynamicEntityKey;

import java.util.Date;

import ua.com.fielden.platform.entity.ActivatableAbstractEntity;
import ua.com.fielden.platform.entity.annotation.KeyType;
import ua.com.fielden.platform.entity.annotation.KeyTitle;
import ua.com.fielden.platform.entity.annotation.CompanionObject;
import ua.com.fielden.platform.entity.annotation.CompositeKeyMember;
import ua.com.fielden.platform.entity.annotation.IsProperty;
import ua.com.fielden.platform.entity.annotation.MapEntityTo;
import ua.com.fielden.platform.entity.annotation.MapTo;
import ua.com.fielden.platform.entity.annotation.Observable;
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
@KeyTitle("Key")
@CompanionObject(RepairCo.class)
@MapEntityTo
public class Repair extends ActivatableAbstractEntity<DynamicEntityKey> {

    private static final Pair<String, String> entityTitleAndDesc = TitlesDescsGetter.getEntityTitleAndDesc(Repair.class);
    public static final String ENTITY_TITLE = entityTitleAndDesc.getKey();
    public static final String ENTITY_DESC = entityTitleAndDesc.getValue();

    @IsProperty
	@MapTo
	@Title(value = "Title", desc = "1")
	@CompositeKeyMember(1)
	private String repairID;
    
    @IsProperty
	@MapTo
	@Title(value = "Title", desc = "Extended_description")
	private Vehicle vehicle;

    
    @IsProperty
	@MapTo
	@Title(value = "Title", desc = "Extended_description")
	private Date createdAt;

	@Observable
	public Repair setName(final Date createdAt) {
		this.createdAt = createdAt;
		return this;
	}

	public Date getName() {
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

	


	@Observable
	public Repair setRepairID(final String repairID) {
		this.repairID = repairID;
		return this;
	}

	public String getRepairID() {
		return repairID;
	}

	

}
