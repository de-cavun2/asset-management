package decavun2.personnel;

import static ua.com.fielden.platform.reflection.TitlesDescsGetter.getEntityTitleAndDesc;

import decavun2.security.tokens.persistent.Person_CanModify_user_Token;
import ua.com.fielden.platform.entity.ActivatableAbstractEntity;
import ua.com.fielden.platform.entity.DynamicEntityKey;
import ua.com.fielden.platform.entity.annotation.CompanionObject;
import ua.com.fielden.platform.entity.annotation.CompositeKeyMember;
import ua.com.fielden.platform.entity.annotation.DescRequired;
import ua.com.fielden.platform.entity.annotation.DescTitle;
import ua.com.fielden.platform.entity.annotation.DisplayDescription;
import ua.com.fielden.platform.entity.annotation.EntityTitle;
import ua.com.fielden.platform.entity.annotation.IsProperty;
import ua.com.fielden.platform.entity.annotation.KeyTitle;
import ua.com.fielden.platform.entity.annotation.KeyType;
import ua.com.fielden.platform.entity.annotation.MapEntityTo;
import ua.com.fielden.platform.entity.annotation.MapTo;
import ua.com.fielden.platform.entity.annotation.Observable;
import ua.com.fielden.platform.entity.annotation.SkipEntityExistsValidation;
import ua.com.fielden.platform.entity.annotation.Title;
import ua.com.fielden.platform.entity.annotation.Unique;
import ua.com.fielden.platform.entity.annotation.mutator.BeforeChange;
import ua.com.fielden.platform.entity.annotation.mutator.Handler;
import ua.com.fielden.platform.property.validator.EmailValidator;
import ua.com.fielden.platform.entity.validation.MaxLengthValidator;
import ua.com.fielden.platform.security.Authorise;
import ua.com.fielden.platform.security.user.User;
import ua.com.fielden.platform.utils.Pair;

/**
 * An entity type representing a person.
 *
 * @author Generated
 */
@KeyType(DynamicEntityKey.class)
@EntityTitle("Person")
@KeyTitle(value = "Email", desc = "Uniquely identifies a person.")
@DescTitle(value = "Full Name", desc = "Person's full name - e.g. the first name followed by the middle initial followed by the surname.")
@MapEntityTo
@CompanionObject(PersonCo.class)
@DescRequired
@DisplayDescription
public class Person extends ActivatableAbstractEntity<DynamicEntityKey> {

    private static final Pair<String, String> entityTitleAndDesc = getEntityTitleAndDesc(Person.class);
    public static final String ENTITY_TITLE = entityTitleAndDesc.getKey();
    public static final String ENTITY_DESC = entityTitleAndDesc.getValue();

    @IsProperty(length = 255)
    @CompositeKeyMember(1)
    @MapTo
    @Title(value = "Email", desc = "Uniquely identifies a person.")
    @BeforeChange({ @Handler(MaxLengthValidator.class), @Handler(EmailValidator.class) })
    private String email;

    @IsProperty
    @Unique
    @MapTo
    @Title(value = "User", desc = "An application user associated with the current person.")
    @SkipEntityExistsValidation(skipActiveOnly = true)
    private User user;

    @IsProperty(length = 255)
    @MapTo
    @Title(value = "Title", desc = "Person's role, position or title.")
    @BeforeChange(@Handler(MaxLengthValidator.class))
    private String title;

    @IsProperty(length = 255)
    @MapTo
    @Title(value = "Employee No", desc = "An employee number allocated to a person by their organisation.")
    @BeforeChange(@Handler(MaxLengthValidator.class))
    private String employeeNo;

    @IsProperty(length = 255)
    @MapTo
    @Title(value = "Phone", desc = "An telephone number, usually representing a work office land line number.")
    @BeforeChange(@Handler(MaxLengthValidator.class))
    private String phone;

    @IsProperty(length = 255)
    @MapTo
    @Title(value = "Mobile", desc = "A mobile phone number for this person.")
    @BeforeChange(@Handler(MaxLengthValidator.class))
    private String mobile;

    @Override
    @Observable
    public Person setDesc(final String desc) {
        return (Person) super.setDesc(desc);
    }

    @Observable
    public Person setEmail(final String email) {
        this.email = email;
        return this;
    }

    public String getEmail() {
        return email;
    }

    @Observable
    public Person setMobile(final String mobile) {
        this.mobile = mobile;
        return this;
    }

    public String getMobile() {
        return mobile;
    }

    @Observable
    public Person setPhone(final String phone) {
        this.phone = phone;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    @Observable
    public Person setEmployeeNo(final String employeeNo) {
        this.employeeNo = employeeNo;
        return this;
    }

    public String getEmployeeNo() {
        return employeeNo;
    }

    @Observable
    public Person setTitle(final String title) {
        this.title = title;
        return this;
    }

    public String getTitle() {
        return title;
    }

    @Override
    @Observable
    public Person setActive(final boolean active) {
        super.setActive(active);
        return this;
    }

    @Observable
    @Authorise(Person_CanModify_user_Token.class)
    public Person setUser(final User user) {
        this.user = user;
        return this;
    }

    public User getUser() {
        return user;
    }

    /** A convenient method to identify whether the current person instance is an application user. */
    public boolean isAUser() {
        return getUser() != null;
    }

}
