package decavun2.analysis;

import ua.com.fielden.platform.entity.DynamicEntityKey;

import java.util.Date;

import decavun2.analysis.meta.StatisticsMetaModel;
import decavun2.objects.Vehicle;
import ua.com.fielden.platform.entity.AbstractPersistentEntity;
import ua.com.fielden.platform.entity.annotation.KeyType;
import ua.com.fielden.platform.entity.annotation.KeyTitle;
import ua.com.fielden.platform.entity.annotation.CompanionObject;
import ua.com.fielden.platform.entity.annotation.CompositeKeyMember;
import ua.com.fielden.platform.entity.annotation.Dependent;
import ua.com.fielden.platform.entity.annotation.IsProperty;
import ua.com.fielden.platform.entity.annotation.MapEntityTo;
import ua.com.fielden.platform.entity.annotation.MapTo;
import ua.com.fielden.platform.entity.annotation.Observable;
import ua.com.fielden.platform.entity.annotation.Readonly;
import ua.com.fielden.platform.entity.annotation.Required;
import ua.com.fielden.platform.entity.annotation.Title;
import ua.com.fielden.platform.entity.annotation.mutator.BeforeChange;
import ua.com.fielden.platform.entity.annotation.mutator.Handler;
import ua.com.fielden.platform.entity.validation.MaxLengthValidator;
import ua.com.fielden.platform.entity.validation.annotation.GeProperty;
import ua.com.fielden.platform.entity.validation.annotation.LeProperty;
import ua.com.fielden.platform.error.Result;
import ua.com.fielden.platform.reflection.TitlesDescsGetter;
import ua.com.fielden.platform.utils.Pair;

/**
 * Master entity object Statistics
 *
 * @author Stefan Malyk
 *
 */
@KeyType(DynamicEntityKey.class)
@KeyTitle("Statistics")
@CompanionObject(StatisticsCo.class)
@MapEntityTo
public class Statistics extends AbstractPersistentEntity<DynamicEntityKey> {

	private static final Pair<String, String> entityTitleAndDesc = TitlesDescsGetter
			.getEntityTitleAndDesc(Statistics.class);
	public static final String ENTITY_TITLE = entityTitleAndDesc.getKey();
	public static final String ENTITY_DESC = entityTitleAndDesc.getValue();

	@IsProperty(length = 255)
	@MapTo
	@Title(value = "Name", desc = "Name of this statistical report")
	@CompositeKeyMember(1)
	@BeforeChange(@Handler(MaxLengthValidator.class))
	private String name;

	@IsProperty
	@MapTo
	@Title(value = "Vehicle", desc = "Vehicle that is related to this statistical report")
	@Required
	private Vehicle vehicle;

	@IsProperty
	@MapTo
	@Title(value = "Created At", desc = "The time this statistical report was created")
	@Readonly
	private Date createdAt;

	@IsProperty
	@MapTo
	@Title(value = "Repairs count", desc = "Number of repairs for selected vehicle in selected time range")
	@Readonly
	private Integer repairsCount;

	@IsProperty
	@MapTo
	@Title(value = "Issues count", desc = "Number of issues for selected vehicle in selected time range")
	@Readonly
	private Integer issuesCount;

	@IsProperty
	@MapTo
	@Dependent(StatisticsMetaModel.endDate_)
	@Title(value = "Start Date", desc = "The start date of the range")
	@Required
	private Date startDate;

	@IsProperty
	@MapTo
	@Dependent(StatisticsMetaModel.startDate_)
	@Title(value = "End Date", desc = "The end date of the range")
	@Required
	private Date endDate;
	
	
	@Override
	public Result isEditable() {
        final var res = super.isEditable();
        if(!res.isSuccessful()) {
            return res;
        }
        if(isPersisted()) {
            return Result.failure(StatisticsCo.STATISTICS_IS_NOT_EDITABLE_ERROR);
        }
        return res;
	}

	@Observable
	@LeProperty(StatisticsMetaModel.endDate_)
	public Statistics setStartDate(final Date startDate) {
		this.startDate = startDate;
		return this;
	}

	public Date getStartDate() {
		return startDate;
	}

	@Observable
	@GeProperty(StatisticsMetaModel.startDate_)
	public Statistics setEndDate(final Date endDate) {
		this.endDate = endDate;
		return this;
	}

	public Date getEndDate() {
		return endDate;
	}

	@Observable
	public Statistics setIssuesCount(final Integer issuesCount) {
		this.issuesCount = issuesCount;
		return this;
	}

	public Integer getIssuesCount() {
		return issuesCount;
	}

	@Observable
	public Statistics setRepairsCount(final Integer repairsCount) {
		this.repairsCount = repairsCount;
		return this;
	}

	public Integer getRepairsCount() {
		return repairsCount;
	}

	@Observable
	public Statistics setCreatedAt(final Date createdAt) {
		this.createdAt = createdAt;
		return this;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	@Observable
	public Statistics setVehicle(final Vehicle vehicle) {
		this.vehicle = vehicle;
		return this;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	@Observable
	public Statistics setName(final String name) {
		this.name = name;
		return this;
	}

	public String getName() {
		return name;
	}

}
