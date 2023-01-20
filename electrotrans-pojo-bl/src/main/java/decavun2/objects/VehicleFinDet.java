package decavun2.objects;

import java.util.Date;

import decavun2.objects.meta.VehicleFinDetMetaModel;
import ua.com.fielden.platform.entity.AbstractPersistentEntity;
import ua.com.fielden.platform.entity.annotation.CompanionObject;
import ua.com.fielden.platform.entity.annotation.Dependent;
import ua.com.fielden.platform.entity.annotation.EntityTitle;
import ua.com.fielden.platform.entity.annotation.IsProperty;
import ua.com.fielden.platform.entity.annotation.KeyTitle;
import ua.com.fielden.platform.entity.annotation.KeyType;
import ua.com.fielden.platform.entity.annotation.MapEntityTo;
import ua.com.fielden.platform.entity.annotation.MapTo;
import ua.com.fielden.platform.entity.annotation.Observable;
import ua.com.fielden.platform.entity.annotation.Title;
import ua.com.fielden.platform.entity.validation.annotation.GeProperty;
import ua.com.fielden.platform.entity.validation.annotation.LeProperty;
import ua.com.fielden.platform.reflection.TitlesDescsGetter;
import ua.com.fielden.platform.types.Money;
import ua.com.fielden.platform.utils.Pair;

/**
 * One-2-One entity object.
 *
 * @author Developers
 *
 */
@KeyType(Vehicle.class)
@KeyTitle("Vehicle")
@EntityTitle("Financial details")
@CompanionObject(VehicleFinDetCo.class)
@MapEntityTo
public class VehicleFinDet extends AbstractPersistentEntity<Vehicle> {

    private static final Pair<String, String> entityTitleAndDesc = TitlesDescsGetter.getEntityTitleAndDesc(VehicleFinDet.class);
    public static final String ENTITY_TITLE = entityTitleAndDesc.getKey();
    public static final String ENTITY_DESC = entityTitleAndDesc.getValue();
    
    @IsProperty
    @MapTo
    @Title(value = "Initial Cost", desc = "The initial cost of this vehicle.")
    private Money initCost;

    @IsProperty
    @MapTo
    @Dependent(VehicleFinDetMetaModel.disposalDate_)
    @Title(value = "Commission Date", desc = "The date when this vehicle was commissioned.")
    private Date commissionDate;

    @IsProperty
    @MapTo
    @Dependent(VehicleFinDetMetaModel.commissionDate_)
    @Title(value = "Disposal Date", desc = "The date when this vehicle was disposed of.")
    private Date disposalDate;
    
    @IsProperty
    @MapTo
    @Title(value = "Tender ID", desc = "ID of the tender at which the vehicle was purchased")
    private String tenderId;
    
    @IsProperty
    @MapTo
    @Title(value = "Tender Date", desc = "The date when the procurement tender was held.")
    private Date tenderDate;

    @Observable
    public VehicleFinDet setTenderDate(final Date tenderDate) {
        this.tenderDate = tenderDate;
        return this;
    }

    public Date getTenderDate() {
        return tenderDate;
    }

    @Observable
    public VehicleFinDet setTenderId(final String tenderId) {
        this.tenderId = tenderId;
        return this;
    }

    public String getTenderId() {
        return tenderId;
    }

    @Override
    @Observable
    public VehicleFinDet setKey(final Vehicle key) {
        super.setKey(key);
        return this;
    }

    @Observable
    @LeProperty(VehicleFinDetMetaModel.disposalDate_)
    public VehicleFinDet setCommissionDate(final Date commissionDate) {
        this.commissionDate = commissionDate;
        return this;
    }

    public Date getCommissionDate() {
        return commissionDate;
    }

    @Observable
    @GeProperty(VehicleFinDetMetaModel.commissionDate_)
    public VehicleFinDet setDisposalDate(final Date disposalDate) {
        this.disposalDate = disposalDate;
        return this;
    }

    public Date getDisposalDate() {
        return disposalDate;
    }

    @Observable
    public VehicleFinDet setInitCost(final Money initCost) {
        this.initCost = initCost;
        return this;
    }

    public Money getInitCost() {
        return initCost;
    }

}
