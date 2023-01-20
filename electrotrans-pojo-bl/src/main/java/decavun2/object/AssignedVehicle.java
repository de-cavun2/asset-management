package decavun2.object;


import ua.com.fielden.platform.entity.DynamicEntityKey;

import java.util.Date;

import ua.com.fielden.platform.entity.ActivatableAbstractEntity;
import ua.com.fielden.platform.entity.annotation.CompositeKeyMember;
import ua.com.fielden.platform.entity.annotation.DescTitle;
import ua.com.fielden.platform.entity.annotation.EntityTitle;
import ua.com.fielden.platform.entity.annotation.KeyType;
import ua.com.fielden.platform.entity.annotation.KeyTitle;
import ua.com.fielden.platform.entity.annotation.CompanionObject;
import ua.com.fielden.platform.entity.annotation.MapEntityTo;
import ua.com.fielden.platform.entity.annotation.MapTo;
import ua.com.fielden.platform.entity.annotation.IsProperty;
import ua.com.fielden.platform.entity.annotation.Observable;
import ua.com.fielden.platform.entity.annotation.Required;
import ua.com.fielden.platform.entity.annotation.Title;
import ua.com.fielden.platform.reflection.TitlesDescsGetter;
import ua.com.fielden.platform.utils.Pair;

/**
 * One-2-Many entity object.
 * This entity is used to represent a specific vehicle, that is assigned to a concrete route.
 * @author Developers
 */
@KeyType(DynamicEntityKey.class)
@KeyTitle("Assigned vehicle")
@CompanionObject(AssignedVehicleCo.class)
@EntityTitle(value = "Asigned vehicle", desc = "Vehicle, that is assigned to a conrete route.")
@DescTitle(value = "Asigned vehicle's description", desc = "The extended description for the entity AssignedVehicle.")
@MapEntityTo
public class AssignedVehicle extends ActivatableAbstractEntity<DynamicEntityKey> {

    private static final Pair<String, String> entityTitleAndDesc = TitlesDescsGetter.getEntityTitleAndDesc(AssignedVehicle.class);
    public static final String ENTITY_TITLE = entityTitleAndDesc.getKey();
    public static final String ENTITY_DESC = entityTitleAndDesc.getValue();

    @IsProperty
    @MapTo
    @CompositeKeyMember(1)
    private Route route;
    
    @IsProperty
    @MapTo
    @Title(value = "Assigned date", desc = "The date when the vehicle was assigned to this route.")
    @CompositeKeyMember(2)
    private Date assignedDate;
    
    @IsProperty
    @MapTo
    @Title(value = "Vehcile", desc = "The vehicle, that is assigned to this route.")
    @CompositeKeyMember(3)
    private String vehicle;
    
    @IsProperty
    @MapTo
    @Required
    @Title(value = "Station schedule", desc = "The schedule of vehicle's stations.")
    private String stationSchedule;
    
    @IsProperty
	@MapTo
	@Required
	@Title(value = "Interval", desc = "Describes the interval between two consecutive stations.")
	private Integer interval;

	@Observable
	public AssignedVehicle setInterval(final Integer interval) {
		this.interval = interval;
		return this;
	}

	public Integer getInterval() {
		return interval;
	}

	


    @Observable
    public AssignedVehicle setStationSchedule(final String stationSchedule) {
       this.stationSchedule = stationSchedule;
       return this;
    }

    public String getStationSchedule() {
       return stationSchedule;
    }

    @Observable
    public AssignedVehicle setVehicle(final String vehicle) {
       this.vehicle = vehicle;
       return this;
    }

    public String getVehicle() {
       return vehicle;
    }

    @Observable
    public AssignedVehicle setAssignedDate(final Date assignedDate) {
       this.assignedDate = assignedDate;
       return this;
    }

    public Date getAssignedDate() {
       return assignedDate;
    }

    @Observable
    public AssignedVehicle setRoute(final Route value) {
       this.route = value;
       return this;
    }

    public Route getRoute() {
       return route;
    }
    
    @Override
    @Observable
    public AssignedVehicle setActive(final boolean active) {
        super.setActive(active);
        return this;
    }

}
