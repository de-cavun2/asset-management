package decavun2.objects;

import ua.com.fielden.platform.entity.DynamicEntityKey;

import org.apache.logging.log4j.core.config.plugins.validation.constraints.Required;

import ua.com.fielden.platform.entity.ActivatableAbstractEntity;
import ua.com.fielden.platform.entity.annotation.KeyType;
import ua.com.fielden.platform.entity.annotation.KeyTitle;
import ua.com.fielden.platform.entity.annotation.CompanionObject;
import ua.com.fielden.platform.entity.annotation.CompositeKeyMember;
import ua.com.fielden.platform.entity.annotation.MapEntityTo;
import ua.com.fielden.platform.entity.annotation.MapTo;
import ua.com.fielden.platform.entity.annotation.Observable;
import ua.com.fielden.platform.entity.annotation.Title;
import ua.com.fielden.platform.entity.annotation.DescTitle;
import ua.com.fielden.platform.entity.annotation.DisplayDescription;
import ua.com.fielden.platform.entity.annotation.IsProperty;
import ua.com.fielden.platform.entity.annotation.DescRequired;
import ua.com.fielden.platform.reflection.TitlesDescsGetter;
import ua.com.fielden.platform.utils.Pair;

/**
 * Master entity object.
 * Used to represent a concrete vehicle with all necessary information about it. 
 * @author Developers
 *
 */
@KeyType(DynamicEntityKey.class)
@KeyTitle("License plate")
@CompanionObject(VehicleCo.class)
@MapEntityTo
@DescTitle("Vehicle's description")
@DisplayDescription
@DescRequired
public class Vehicle extends ActivatableAbstractEntity<DynamicEntityKey> {

    private static final Pair<String, String> entityTitleAndDesc = TitlesDescsGetter.getEntityTitleAndDesc(Vehicle.class);
    public static final String ENTITY_TITLE = entityTitleAndDesc.getKey();
    public static final String ENTITY_DESC = entityTitleAndDesc.getValue();
    
    @IsProperty
    @MapTo
    @Title(value = "License plate", desc = "The unique vehicle's identifier")
    @CompositeKeyMember(1)
    private String licensePlate;
    
    @IsProperty
    @MapTo
    @Required
    @Title(value = "Model", desc = "The model of the vehicle.")
    private String model;

    @IsProperty
    @MapTo
    @Required
    @Title(value = "Current location", desc = "The current location of the vehicle.")
    private String currentLocation;
    
    @IsProperty
    @MapTo
    @Required
    @Title(value = "Workable status", desc = "Describes whether the vehicle is workable or not.")
    private boolean isWorkable;

    @IsProperty
    @MapTo
    @Required
    @Title(value = "Driver", desc = "The vehicle's driver.")
    private String driver;

    @Observable
    public Vehicle setDriver(final String driver) {
       this.driver = driver;
       return this;
    }

    public String getDriver() {
       return driver;
    }
    
    @Observable
    public Vehicle setIsWorkable(final boolean isWorkable) {
       this.isWorkable = isWorkable;
       return this;
    }

    public boolean getIsWorkable() {       
       return isWorkable;
    }

    @Observable
    public Vehicle setCurrentLocation(final String currentLocation) {
       this.currentLocation = currentLocation;
       return this;
    }

    public String getCurrentLocation() {
       return currentLocation;
    }

    @Observable
    public Vehicle setModel(final String model) {
       this.model = model;
       return this;
    }

    public String getModel() {
       return model;
    }

    @Observable
    public Vehicle setLicensePlate(final String licensePlate) {
       this.licensePlate = licensePlate;
       return this;
    }

    public String getLicensePlate() {
       return licensePlate;
    }

}