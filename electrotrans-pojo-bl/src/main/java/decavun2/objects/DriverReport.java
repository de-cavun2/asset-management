package decavun2.objects;

import java.util.Date;

import ua.com.fielden.platform.entity.AbstractPersistentEntity;
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
import ua.com.fielden.platform.entity.annotation.Required;
import ua.com.fielden.platform.entity.annotation.Title;
import ua.com.fielden.platform.reflection.TitlesDescsGetter;
import ua.com.fielden.platform.utils.Pair;

/**
 * Master entity object.
<<<<<<< HEAD
 *
=======
 * 
>>>>>>> Issue-#12
 * @author Developers
 *
 */
@KeyType(DynamicEntityKey.class)

@KeyTitle("DriverReport ID")
@CompanionObject(DriverReportCo.class)
@MapEntityTo
@DescTitle("Description of DriverReport")
@DisplayDescription
@DescRequired
public class DriverReport extends ActivatableAbstractEntity<DynamicEntityKey> {

    private static final Pair<String, String> entityTitleAndDesc = TitlesDescsGetter.getEntityTitleAndDesc(DriverReport.class);
    public static final String ENTITY_TITLE = entityTitleAndDesc.getKey();
    public static final String ENTITY_DESC = entityTitleAndDesc.getValue();
    
    
    @IsProperty
	@MapTo
	@Title(value = "Title", desc = "Extended_description")
    @Required
	private Vehicle vehicle;
    
    @IsProperty
	@MapTo
	@Title(value = "Title", desc = "Extended_description")
	private Date createdAt;



    @IsProperty
    @MapTo
    @Title(value = "DriverReport ID", desc = "The ID of a particular driver report.")
    @CompositeKeyMember(1)
    private String driverReportID;



    @IsProperty
    @MapTo
    @Required
    @Title(value = "PerceivedState", desc = "The perceived state of the vehicle by the driver.")
    private String state;
    
	@Observable
	public DriverReport setCreatedAt(final Date createdAt) {
		this.createdAt = createdAt;
		return this;
	}

	public Date getCreatedAt() {
		return createdAt;
	}



	@Observable
	public DriverReport setVehicle(final Vehicle vehicle) {
		this.vehicle = vehicle;
		return this;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}
    
    @Observable
    public DriverReport setDriverReportID(final String id) {
       this.driverReportID = id;
       return this;
    }

    public String getDriverReportID() {
       return driverReportID;
    }


    @Observable
    public DriverReport setState(final String state) {
       this.state = state;
       return this;
    }

    public String getState() {
       return state;
    }

}
