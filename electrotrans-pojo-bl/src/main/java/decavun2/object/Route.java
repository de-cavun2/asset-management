package decavun2.object;

import ua.com.fielden.platform.entity.DynamicEntityKey;
import decavun2.object.definers.MakeTrafficDataRequiredDefiner;
import ua.com.fielden.platform.entity.AbstractPersistentEntity;
import ua.com.fielden.platform.entity.annotation.KeyType;
import ua.com.fielden.platform.entity.annotation.KeyTitle;
import ua.com.fielden.platform.entity.annotation.CompanionObject;
import ua.com.fielden.platform.entity.annotation.CompositeKeyMember;
import ua.com.fielden.platform.entity.annotation.MapEntityTo;
import ua.com.fielden.platform.entity.annotation.MapTo;
import ua.com.fielden.platform.entity.annotation.Observable;
import ua.com.fielden.platform.entity.annotation.Required;
import ua.com.fielden.platform.entity.annotation.Title;
import ua.com.fielden.platform.entity.annotation.mutator.AfterChange;
import ua.com.fielden.platform.entity.annotation.DescTitle;
import ua.com.fielden.platform.entity.annotation.DisplayDescription;
import ua.com.fielden.platform.entity.annotation.EntityTitle;
import ua.com.fielden.platform.entity.annotation.IsProperty;
import ua.com.fielden.platform.entity.annotation.DescRequired;
import ua.com.fielden.platform.reflection.TitlesDescsGetter;
import ua.com.fielden.platform.utils.Pair;

/**
 * Master entity object.
 * Used to represent a concrete route with all necessary information about it.
 * @author Developers
 */
@KeyType(DynamicEntityKey.class)
@KeyTitle("Route number")
@CompanionObject(RouteCo.class)
@MapEntityTo
@EntityTitle(value = "Route", desc = "Routes that are used by the Lvivelectrotrans.")
@DescTitle(value = "Route description", desc = "The extended description.")
@DisplayDescription
@DescRequired
public class Route extends AbstractPersistentEntity<DynamicEntityKey> {

    private static final Pair<String, String> entityTitleAndDesc = TitlesDescsGetter.getEntityTitleAndDesc(Route.class);
    public static final String ENTITY_TITLE = entityTitleAndDesc.getKey();
    public static final String ENTITY_DESC = entityTitleAndDesc.getValue();
    
    @IsProperty
    @MapTo
    @Title(value = "Route number", desc = "The unique route's identifier.")
    @CompositeKeyMember(1)
    private Integer routeNum;
    
    @IsProperty
    @MapTo
    @Required
    @Title(value = "Name", desc = "The name of the route.")
    private String name;

    @IsProperty
    @MapTo
    @Required
    @Title(value = "Station order", desc = "The route's station order.")
    private String stationOrder;

    @IsProperty
    @MapTo
    @Title(value = "Traffic data", desc = "The data about traffic on the route.")
    private String trafficData;
    
    @IsProperty
    @MapTo
    @AfterChange(MakeTrafficDataRequiredDefiner.class)
    @Title(value = "In use", desc = "Indicates whether the route is currently used or not.")
    private boolean inUse;

    @Observable
    public Route setInUse(final boolean inUse) {
       this.inUse = inUse;
       return this;
    }

    public boolean getInUse() {
       return inUse;
    }

    @Observable
    public Route setTrafficData(final String trafficData) {
       this.trafficData = trafficData;
       return this;
    }

    public String getTrafficData() {
       return trafficData;
    }

    @Observable
    public Route setStationOrder(final String stationOrder) {
       this.stationOrder = stationOrder;
       return this;
    }

    public String getStationOrder() {
       return stationOrder;
    }

    @Observable
    public Route setName(final String name) {
       this.name = name;
       return this;
    }

    public String getName() {
       return name;
    }

    @Observable
    public Route setRouteNum(final Integer routeNum) {
       this.routeNum = routeNum;
       return this;
    }

    public Integer getRouteNum() {
       return routeNum;
    }

}
