package decavun2.change;

import ua.com.fielden.platform.entity.DynamicEntityKey;

import static ua.com.fielden.platform.entity.query.fluent.EntityQueryUtils.expr;
import static ua.com.fielden.platform.entity.query.fluent.EntityQueryUtils.select;

import java.util.Date;

import decavun2.change.definers.ReportSourceExclusiveDefiner;
import decavun2.personnel.Person;
import ua.com.fielden.platform.entity.ActivatableAbstractEntity;
import ua.com.fielden.platform.entity.annotation.KeyType;
import ua.com.fielden.platform.entity.annotation.KeyTitle;
import ua.com.fielden.platform.entity.annotation.Calculated;
import ua.com.fielden.platform.entity.annotation.CompanionObject;
import ua.com.fielden.platform.entity.annotation.CompositeKeyMember;
import ua.com.fielden.platform.entity.annotation.DateOnly;
import ua.com.fielden.platform.entity.annotation.MapEntityTo;
import ua.com.fielden.platform.entity.annotation.MapTo;
import ua.com.fielden.platform.entity.annotation.Observable;
import ua.com.fielden.platform.entity.annotation.Readonly;
import ua.com.fielden.platform.entity.annotation.Required;
import ua.com.fielden.platform.entity.annotation.Title;
import ua.com.fielden.platform.entity.annotation.Unique;
import ua.com.fielden.platform.entity.annotation.mutator.AfterChange;
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
 * Report entity. Captures information that was concluded as a result of Change or Issue.
 *
 * @author Stefan Malyk
 *
 */
@KeyType(DynamicEntityKey.class)
@KeyTitle("Title")
@CompanionObject(ReportCo.class)
@MapEntityTo
@DescTitle("Report Description")
@DisplayDescription
@DescRequired
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
	@Title(value = "Created by", desc = "Person who created this report")
    @Required
	private Person person;
	

    @IsProperty
	@MapTo
	@Title(value = "Change", desc = "Change associated with this report. Mutually exclusive with issue")
    @AfterChange(ReportSourceExclusiveDefiner.class)
	private Change change;
    
    
    @IsProperty
	@MapTo
	@Title(value = "Issue", desc = "Issue associated with this report. Mutually exclusive with change")
    @AfterChange(ReportSourceExclusiveDefiner.class)
	private String issue;
    
    
    @IsProperty
	@MapTo
	@Required
	@Readonly
	@Title(value = "Created at", desc = "Date and time when this property was created at")
	private Date createdAt;
    
    
	@Observable
	public Report setPerson(final Person person) {
		this.person = person;
		return this;
	}

	public Person getPerson() {
		return person;
	}

	@Observable
	public Report setCreatedAt(final Date createdAt) {
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
	public Report setChange(final Change change) {
		this.change = change;
		return this;
	}

	public Change getChange() {
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
