package decavun2.objects;


import ua.com.fielden.platform.entity.DynamicEntityKey;

import java.util.Date;

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
@KeyTitle("Order ID")
@CompanionObject(OrderCo.class)
@MapEntityTo
@DescTitle("Description of Order")
@DisplayDescription
@DescRequired
public class Order extends ActivatableAbstractEntity<DynamicEntityKey> {

    private static final Pair<String, String> entityTitleAndDesc = TitlesDescsGetter.getEntityTitleAndDesc(Order.class);
    public static final String ENTITY_TITLE = entityTitleAndDesc.getKey();
    public static final String ENTITY_DESC = entityTitleAndDesc.getValue();

    @IsProperty
    @MapTo
    @Title(value = "Order ID", desc = "The ID of a particular order.")
    @CompositeKeyMember(1)
    private String orderID;

    @IsProperty
    @MapTo
    @Required
    @Title(value = "Date", desc = "The date of a particular order.")
    private Date date;
  
    @IsProperty
    @MapTo
    @Required
    @Title(value = "Parts", desc = "The description of the parts in the order.")
    private String parts;
    
    @Observable
    public Order setOrderID(final String id) {
       this.orderID = id;
       return this;
    }

    public String getOrderID() {
       return orderID;
    }

    @Observable
    public Order setDate(final Date date) {
       this.date = date;
       return this;
    }

    public Date getDate() {
       return date;
    }

    @Observable
    public Order setParts(final String parts) {
       this.parts = parts;
       return this;
    }

    public String getParts() {
       return parts;
    }

}