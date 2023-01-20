package decavun2.objects;


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
import ua.com.fielden.platform.entity.annotation.Required;
import ua.com.fielden.platform.entity.annotation.Title;
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
    @Title(value = "DriverReport ID", desc = "The ID of a particular driver report.")
    @CompositeKeyMember(1)
    private String driverReportID;

    @IsProperty
    @MapTo
    @Required
    @Title(value = "Date", desc = "The date of a particular driver report.")
    private Date date;

    @IsProperty
    @MapTo
    @Required
    @Title(value = "Vehicle", desc = "The vehicle that the driver report concerns.")
    private String vehicle;

    @IsProperty
    @MapTo
    @Required
    @Title(value = "PerceivedState", desc = "The perceived state of the vehicle by the driver.")
    private String state;
    
    @Observable
    public DriverReport setDriverReportID(final String id) {
       this.driverReportID = id;
       return this;
    }

    public String getDriverReportID() {
       return driverReportID;
    }

    @Observable
    public DriverReport setDate(final Date date) {
       this.date = date;
       return this;
    }

    public Date getDate() {
       return date;
    }

    @Observable
    public DriverReport setVehicle(final String vehicle) {
       this.vehicle = vehicle;
       return this;
    }

    public String getVehicle() {       
       return vehicle;
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