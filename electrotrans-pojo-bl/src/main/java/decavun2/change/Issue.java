package decavun2.change;

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
import ua.com.fielden.platform.entity.annotation.Title;
import ua.com.fielden.platform.entity.annotation.DescTitle;
import ua.com.fielden.platform.entity.annotation.DisplayDescription;
import ua.com.fielden.platform.entity.annotation.IsProperty;
import ua.com.fielden.platform.entity.annotation.DescRequired;
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
import ua.com.fielden.platform.reflection.TitlesDescsGetter;
import ua.com.fielden.platform.utils.Pair;

/**
 * Mockup for Issue object
 *
 * @author Developers
 *
 */
@KeyType(DynamicEntityKey.class)
@KeyTitle("Key")
@CompanionObject(IssueCo.class)
@MapEntityTo
public class Issue extends ActivatableAbstractEntity<DynamicEntityKey> {

	private static final Pair<String, String> entityTitleAndDesc = TitlesDescsGetter.getEntityTitleAndDesc(Issue.class);
    public static final String ENTITY_TITLE = entityTitleAndDesc.getKey();
    public static final String ENTITY_DESC = entityTitleAndDesc.getValue();
    public static final int ASSET_NO_LENGTH = 6;

    @IsProperty(length=ASSET_NO_LENGTH)
    @MapTo
    @Title(value = "Issue Number", desc = "Unique identicator of the Issue.")
    @CompositeKeyMember(1)
    @Readonly
    @BeforeChange(@Handler(MaxLengthValidator.class))
    private String issueNumber;

    @IsProperty
    @MapTo
    @Required
    @Title(value = "Description", desc = "Detailed description of the Issue.")
    private String description;

    @IsProperty
    @MapTo
    @Required
    @Title(value = "Date", desc = "Date of the opened issue")
    private Date date;

    @IsProperty
    @MapTo
    @Required
    @Title(value = "Responsible person", desc = "Person that is responsible for the issue")
    private Person responsiblePerson;
    
    @IsProperty
	@MapTo
	@Title(value = "Change", desc = "Change related to this Issue")
	private Change change;

	@Observable
	public Issue setChange(final Change change) {
		this.change = change;
		return this;
	}

	public Change getChange() {
		return change;
	}

	


    @Observable
    public Issue setIssueNumber(final String issueNumber) {
        this.issueNumber = issueNumber;
	return this;
    }

    public String getIssueNumber() {
	return issueNumber;
    }

    @Observable
    public Issue setDescription(final String description) {
	this.description = description;
	return this;
    }

    public String getDescription() {
	return description;
    }

    @Observable
    public Issue setDate(final Date date) {
        this.date = date;
        return this;
    }

    public Date getDate() {
        return date;
    }

    @Observable
    public Issue setResponsiblePerson(final Person responsiblePerson) {
        this.responsiblePerson = responsiblePerson;
        return this;
    }

    public Person getResponsiblePerson() {
        return responsiblePerson;
    }

    @Override
    @Observable
    public Issue setActive(final boolean active) {
            super.setActive(active);
            return this;
    }
}
