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

    @IsProperty
    @MapTo
    @Title(value = "Repair ID", desc = "The ID of a particular repair.")
    @CompositeKeyMember(1)
    private String repairID;

    @IsProperty
    @MapTo
    @Required
    @Title(value = "Date", desc = "The date of a particular repair.")
    private Date date;

    @IsProperty
    @MapTo
    @Required
    @Title(value = "Vehicle", desc = "The vehicle that has been repaired.")
    private String vehicle;

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
    public Repair setDate(final Date date) {
       this.date = date;
       return this;
    }

    public Date getDate() {
       return date;
    }

    @Observable
    public Repair setVehicle(final String vehicle) {
       this.vehicle = vehicle;
       return this;
    }

    public String getVehicle() {       
       return vehicle;
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