package decavun2.change;

import ua.com.fielden.platform.entity.DynamicEntityKey;

import static ua.com.fielden.platform.entity.query.fluent.EntityQueryUtils.expr;
import static ua.com.fielden.platform.entity.query.fluent.EntityQueryUtils.select;

import java.util.Date;

import ua.com.fielden.platform.entity.ActivatableAbstractEntity;
import ua.com.fielden.platform.entity.annotation.KeyType;
import ua.com.fielden.platform.entity.annotation.KeyTitle;
import ua.com.fielden.platform.entity.annotation.Calculated;
import ua.com.fielden.platform.entity.annotation.CompanionObject;
import ua.com.fielden.platform.entity.annotation.CompositeKeyMember;
import ua.com.fielden.platform.entity.annotation.MapEntityTo;
import ua.com.fielden.platform.entity.annotation.MapTo;
import ua.com.fielden.platform.entity.annotation.Observable;
import ua.com.fielden.platform.entity.annotation.Readonly;
import ua.com.fielden.platform.entity.annotation.Required;
import ua.com.fielden.platform.entity.annotation.Title;
import ua.com.fielden.platform.entity.annotation.mutator.BeforeChange;
import ua.com.fielden.platform.entity.annotation.mutator.Handler;
import ua.com.fielden.platform.entity.query.model.ExpressionModel;
import ua.com.fielden.platform.entity.validation.MaxLengthValidator;
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
@KeyTitle("Title")
@CompanionObject(ReportCo.class)
@MapEntityTo
@DescTitle("Report Description")
@DisplayDescription
@DescRequired
// TODO: May need this later if some entities need to be automatically cascade-deactivated when this entity is deactivated
// @DeactivatableDependencies({ Dependency1.class, Dependency2.class, Dependency3.class })
public class Report extends ActivatableAbstractEntity<DynamicEntityKey> {

    private static final Pair<String, String> entityTitleAndDesc = TitlesDescsGetter.getEntityTitleAndDesc(Report.class);
    public static final String ENTITY_TITLE = entityTitleAndDesc.getKey();
    public static final String ENTITY_DESC = entityTitleAndDesc.getValue();
    
    public static final int TITLE_LENGTH = 100;
    public static final int DESC_LENGTH = 2000;
    
    @IsProperty(length = TITLE_LENGTH)
	@MapTo
	@Title(value = "Title", desc = "Title of the report")
	@CompositeKeyMember(1)
    @Readonly
    @BeforeChange(@Handler(MaxLengthValidator.class))
	private String title;
    
    @IsProperty(length = DESC_LENGTH)
	@MapTo
	@Title(value = "Desc", desc = "Content of the report itself")
    @Required
    @BeforeChange(@Handler(MaxLengthValidator.class))
	private String  desc;
    
    @IsProperty
	@MapTo
	@Title(value = "Department", desc = "Department this report is relevant to")
	private String department;
    
    
    @IsProperty
	@MapTo
	@Title(value = "Change", desc = "Change associated with this report. Mutually exclusive with issue")
	private String change;
    
    
    @IsProperty
	@MapTo
	@Title(value = "Issue", desc = "Issue associated with this report. Mutually exclusive with change")
	private String issue;
    
    
    @IsProperty
	@Readonly
	@Calculated
	@Title(value = "Created date", desc = "Date and time when this report was created")
	private Date createdAt;
	protected static final ExpressionModel name_ = expr().val(new Date()).model();

	@Observable
	protected Report setCreatedAt(final Date createdAt) {
		this.createdAt = createdAt;
		return this;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	

	


	


	@Observable
	public Report setIssue(final String issue) {
		this.issue = issue;
		return this;
	}

	public String getIssue() {
		return issue;
	}

	


	@Observable
	public Report setChange(final String change) {
		this.change = change;
		return this;
	}

	public String getChange() {
		return change;
	}

	

	@Observable
	public Report setDepartment(final String department) {
		this.department = department;
		return this;
	}

	public String getDepartment() {
		return department;
	}



	@Observable
	public Report setDesc(final String  desc) {
		this.desc = desc;
		return this;
	}

	public String  getDesc() {
		return desc;
	}

	

	
	@Observable
	public Report setTitle(final String title) {
		this.title = title;
		return this;
	}
	
	public String getTitle() {
		return title;
	}




}
