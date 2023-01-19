package decavun2.objects;


import ua.com.fielden.platform.entity.DynamicEntityKey;

import java.time.LocalDate;

import decavun2.objects.Vehicle;
import decavun2.personnel.Person;
import decavun2.objects.Issue;

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
    private LocalDate date;

    @IsProperty
    @MapTo
    @Required
    @Title(value = "Vehicle", desc = "The vehicle that has been repaired.")
    private Vehicle vehicle;

    @IsProperty
    @MapTo
    @Required
    @Title(value = "Issue", desc = "Issue that has been resolved.")
    private Issue issue;

    @IsProperty
    @MapTo
    @Required
    @Title(value = "Repairman", desc = "The repairman responsible for the repair.")
    private Person repairman;
    
    @IsProperty
    @MapTo
    @Required
    @Title(value = "Description", desc = "The description of the repair.")
    private String desc;
    
    @Observable
    public Repair setID(final String id) {
       this.repairID = id;
       return this;
    }

    public String getID() {
       return repairID;
    }

    @Observable
    public Repair setDate(final LocalDate date) {
       this.date = date;
       return this;
    }

    public LocalDate getDate() {
       return date;
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
    public Repair setIssue(final Issue issue) {
       this.issue = issue;
       return this;
    }

    public Issue getIssue() {
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

    @Observable
    public Repair setDescription(final String description) {
       this.desc = description;
       return this;
    }

    public String getDescription() {
       return desc;
    }

}