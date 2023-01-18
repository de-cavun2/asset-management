package decavun2.personnel.meta;

import decavun2.personnel.Person;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.util.function.Supplier;
import javax.annotation.processing.Generated;
import ua.com.fielden.platform.annotations.metamodel.MetaModelForType;
import ua.com.fielden.platform.entity.AbstractEntity;
import ua.com.fielden.platform.processors.metamodel.models.EntityMetaModel;
import ua.com.fielden.platform.processors.metamodel.models.PropertyMetaModel;
import ua.com.fielden.platform.security.user.meta.UserMetaModel;

/**
 * Title: Person
 * <p>
 * Description: People in Lvivelectrotrans
 * <p>
 * Auto-generated meta-model for {@link Person}.
 * <p>
 * Generation datetime: 18-01-2023 14:33:06.698 EET
 * <p>
 * Generated by {@link ua.com.fielden.platform.processors.metamodel.MetaModelProcessor}
 * <p>
 */
@Generated(
        value = "ua.com.fielden.platform.processors.metamodel.MetaModelProcessor",
        date = "2023-01-18T14:33:06.698+02:00"
)
@MetaModelForType(Person.class)
public class PersonMetaModel extends EntityMetaModel {
    public static final Class<Person> TYPE = decavun2.personnel.Person.class;

    public static final String active_ = "active";

    public static final String createdBy_ = "createdBy";

    public static final String createdDate_ = "createdDate";

    public static final String createdTransactionGuid_ = "createdTransactionGuid";

    public static final String desc_ = "desc";

    public static final String email_ = "email";

    public static final String employeeNo_ = "employeeNo";

    public static final String id_ = "id";

    public static final String key_ = "key";

    public static final String lastUpdatedBy_ = "lastUpdatedBy";

    public static final String lastUpdatedDate_ = "lastUpdatedDate";

    public static final String lastUpdatedTransactionGuid_ = "lastUpdatedTransactionGuid";

    public static final String mobile_ = "mobile";

    public static final String name_ = "name";

    public static final String phone_ = "phone";

    public static final String refCount_ = "refCount";

    public static final String surname_ = "surname";

    public static final String title_ = "title";

    public static final String user_ = "user";

    private final PropertyMetaModel active;

    private Supplier<UserMetaModel> createdBy;

    private final PropertyMetaModel createdDate;

    private final PropertyMetaModel createdTransactionGuid;

    private final PropertyMetaModel desc;

    private final PropertyMetaModel email;

    private final PropertyMetaModel employeeNo;

    private final PropertyMetaModel id;

    private final PropertyMetaModel key;

    private Supplier<UserMetaModel> lastUpdatedBy;

    private final PropertyMetaModel lastUpdatedDate;

    private final PropertyMetaModel lastUpdatedTransactionGuid;

    private final PropertyMetaModel mobile;

    private final PropertyMetaModel name;

    private final PropertyMetaModel phone;

    private final PropertyMetaModel refCount;

    private final PropertyMetaModel surname;

    private final PropertyMetaModel title;

    private Supplier<UserMetaModel> user;

    public PersonMetaModel(final String path) {
        super(path);
        this.email = new PropertyMetaModel(joinPath(email_));
        this.name = new PropertyMetaModel(joinPath(name_));
        this.surname = new PropertyMetaModel(joinPath(surname_));
        this.user = () -> {
                  ua.com.fielden.platform.security.user.meta.UserMetaModel value = new ua.com.fielden.platform.security.user.meta.UserMetaModel(joinPath(user_));
                  user = () -> value;
                  return value;
                };
        this.title = new PropertyMetaModel(joinPath(title_));
        this.employeeNo = new PropertyMetaModel(joinPath(employeeNo_));
        this.phone = new PropertyMetaModel(joinPath(phone_));
        this.mobile = new PropertyMetaModel(joinPath(mobile_));
        this.active = new PropertyMetaModel(joinPath(active_));
        this.refCount = new PropertyMetaModel(joinPath(refCount_));
        this.createdBy = () -> {
                  ua.com.fielden.platform.security.user.meta.UserMetaModel value = new ua.com.fielden.platform.security.user.meta.UserMetaModel(joinPath(createdBy_));
                  createdBy = () -> value;
                  return value;
                };
        this.createdDate = new PropertyMetaModel(joinPath(createdDate_));
        this.createdTransactionGuid = new PropertyMetaModel(joinPath(createdTransactionGuid_));
        this.lastUpdatedBy = () -> {
                  ua.com.fielden.platform.security.user.meta.UserMetaModel value = new ua.com.fielden.platform.security.user.meta.UserMetaModel(joinPath(lastUpdatedBy_));
                  lastUpdatedBy = () -> value;
                  return value;
                };
        this.lastUpdatedDate = new PropertyMetaModel(joinPath(lastUpdatedDate_));
        this.lastUpdatedTransactionGuid = new PropertyMetaModel(joinPath(lastUpdatedTransactionGuid_));
        this.desc = new PropertyMetaModel(joinPath(desc_));
        this.key = new PropertyMetaModel(joinPath(key_));
        this.id = new PropertyMetaModel(joinPath(id_));
    }

    public PersonMetaModel() {
        this("");
    }

    /**
     * Title: Active?
     * <p>
     * Description: Designates whether an entity instance is active or not.
     * <p>
     * Type: {@link boolean}
     * <p>
     * {@literal @}{@link IsProperty}<br>
     * {@literal @}{@link MapTo}<br>
     * {@literal @}{@link Title}(value = "Active?", desc = "Designates whether an entity instance is active or not.")<br>
     * {@literal @}{@link BeforeChange}(value = {{@literal @}ua.com.fielden.platform.entity.annotation.mutator.Handler(value = ua.com.fielden.platform.entity.validation.ActivePropertyValidator.class)})
     */
    public PropertyMetaModel active() {
        return active;
    }

    /**
     * Title: Created by User
     * <p>
     * Description: The user who originally created this entity instance.
     * <p>
     * Type: {@link ua.com.fielden.platform.security.user.User}
     * <p>
     * Meta-model: {@link UserMetaModel}
     * <p>
     * {@literal @}{@link IsProperty}<br>
     * {@literal @}{@link MapTo}<br>
     * {@literal @}{@link Title}(value = "Created by User", desc = "The user who originally created this entity instance.")<br>
     * {@literal @}{@link SkipEntityExistsValidation}<br>
     * {@literal @}{@link Readonly}
     */
    public UserMetaModel createdBy() {
        return createdBy.get();
    }

    /**
     * Title: Creation Date
     * <p>
     * Description: The date/time when this entity instace was created.
     * <p>
     * Type: {@link java.util.Date}
     * <p>
     * {@literal @}{@link IsProperty}<br>
     * {@literal @}{@link MapTo}<br>
     * {@literal @}{@link Title}(value = "Creation Date", desc = "The date/time when this entity instace was created.")<br>
     * {@literal @}{@link Readonly}
     */
    public PropertyMetaModel createdDate() {
        return createdDate;
    }

    /**
     * Title: Creation Transation ID
     * <p>
     * Description: A unique identifier of the creation transation for this entity instance.
     * <p>
     * Type: {@link String}
     * <p>
     * {@literal @}{@link IsProperty}<br>
     * {@literal @}{@link MapTo}<br>
     * {@literal @}{@link Title}(value = "Creation Transation ID", desc = "A unique identifier of the creation transation for this entity instance.")<br>
     * {@literal @}{@link Readonly}
     */
    public PropertyMetaModel createdTransactionGuid() {
        return createdTransactionGuid;
    }

    /**
     * Type: {@link String}
     * <p>
     * {@literal @}{@link IsProperty}<br>
     * {@literal @}{@link MapTo}(value = "DESC_")
     */
    public PropertyMetaModel desc() {
        return desc;
    }

    /**
     * Title: Email
     * <p>
     * Description: Uniquely identifies a person.
     * <p>
     * Type: {@link String}
     * <p>
     * {@literal @}{@link IsProperty}(length = 255)<br>
     * {@literal @}{@link CompositeKeyMember}(value = 1)<br>
     * {@literal @}{@link MapTo}<br>
     * {@literal @}{@link Title}(value = "Email", desc = "Uniquely identifies a person.")<br>
     * {@literal @}{@link BeforeChange}(value = {{@literal @}ua.com.fielden.platform.entity.annotation.mutator.Handler(value = ua.com.fielden.platform.entity.validation.MaxLengthValidator.class), {@literal @}ua.com.fielden.platform.entity.annotation.mutator.Handler(value = ua.com.fielden.platform.property.validator.EmailValidator.class)})
     */
    public PropertyMetaModel email() {
        return email;
    }

    /**
     * Title: Employee No
     * <p>
     * Description: An employee number allocated to a person by their organisation.
     * <p>
     * Type: {@link String}
     * <p>
     * {@literal @}{@link IsProperty}(length = 255)<br>
     * {@literal @}{@link MapTo}<br>
     * {@literal @}{@link Title}(value = "Employee No", desc = "An employee number allocated to a person by their organisation.")<br>
     * {@literal @}{@link BeforeChange}(value = {{@literal @}ua.com.fielden.platform.entity.annotation.mutator.Handler(value = ua.com.fielden.platform.entity.validation.MaxLengthValidator.class)})
     */
    public PropertyMetaModel employeeNo() {
        return employeeNo;
    }

    @Override
    public Class<? extends AbstractEntity> getEntityClass() {
        return TYPE;
    }

    /**
     * Title: Id
     * <p>
     * Description: Surrogate unique identifier.
     * <p>
     * Type: {@link java.lang.Long}
     * <p>
     * {@literal @}{@link MapTo}(value = "_ID")<br>
     * {@literal @}{@link Title}(value = "Id", desc = "Surrogate unique identifier.")
     */
    public PropertyMetaModel id() {
        return id;
    }

    /**
     * Type: {@link K}
     * <p>
     * {@literal @}{@link IsProperty}<br>
     * {@literal @}{@link UpperCase}<br>
     * {@literal @}{@link MapTo}(value = "KEY_")<br>
     * {@literal @}{@link Required}
     */
    public PropertyMetaModel key() {
        return key;
    }

    /**
     * Title: Last Updated By
     * <p>
     * Description: The user who was the last to update this entity instance.
     * <p>
     * Type: {@link ua.com.fielden.platform.security.user.User}
     * <p>
     * Meta-model: {@link UserMetaModel}
     * <p>
     * {@literal @}{@link IsProperty}<br>
     * {@literal @}{@link MapTo}<br>
     * {@literal @}{@link Title}(value = "Last Updated By", desc = "The user who was the last to update this entity instance.")<br>
     * {@literal @}{@link SkipEntityExistsValidation}<br>
     * {@literal @}{@link Readonly}
     */
    public UserMetaModel lastUpdatedBy() {
        return lastUpdatedBy.get();
    }

    /**
     * Title: Last Updated Date
     * <p>
     * Description: The date/time when this entity instance was last updated.
     * <p>
     * Type: {@link java.util.Date}
     * <p>
     * {@literal @}{@link IsProperty}<br>
     * {@literal @}{@link MapTo}<br>
     * {@literal @}{@link Title}(value = "Last Updated Date", desc = "The date/time when this entity instance was last updated.")<br>
     * {@literal @}{@link Readonly}
     */
    public PropertyMetaModel lastUpdatedDate() {
        return lastUpdatedDate;
    }

    /**
     * Title: Last Update Transaction ID
     * <p>
     * Description: A unique identifier of the last update transaction for this entity instance.
     * <p>
     * Type: {@link String}
     * <p>
     * {@literal @}{@link IsProperty}<br>
     * {@literal @}{@link MapTo}<br>
     * {@literal @}{@link Title}(value = "Last Update Transaction ID", desc = "A unique identifier of the last update transaction for this entity instance.")<br>
     * {@literal @}{@link Readonly}
     */
    public PropertyMetaModel lastUpdatedTransactionGuid() {
        return lastUpdatedTransactionGuid;
    }

    /**
     * Title: Mobile
     * <p>
     * Description: A mobile phone number for this person.
     * <p>
     * Type: {@link String}
     * <p>
     * {@literal @}{@link IsProperty}(length = 255)<br>
     * {@literal @}{@link MapTo}<br>
     * {@literal @}{@link Title}(value = "Mobile", desc = "A mobile phone number for this person.")<br>
     * {@literal @}{@link BeforeChange}(value = {{@literal @}ua.com.fielden.platform.entity.annotation.mutator.Handler(value = ua.com.fielden.platform.entity.validation.MaxLengthValidator.class)})
     */
    public PropertyMetaModel mobile() {
        return mobile;
    }

    /**
     * Title: First Name
     * <p>
     * Description: Person's first name.
     * <p>
     * Type: {@link String}
     * <p>
     * {@literal @}{@link IsProperty}<br>
     * {@literal @}{@link MapTo}<br>
     * {@literal @}{@link Required}<br>
     * {@literal @}{@link Title}(value = "First Name", desc = "Person's first name.")
     */
    public PropertyMetaModel name() {
        return name;
    }

    /**
     * Title: Phone
     * <p>
     * Description: An telephone number, usually representing a work office land line number.
     * <p>
     * Type: {@link String}
     * <p>
     * {@literal @}{@link IsProperty}(length = 255)<br>
     * {@literal @}{@link MapTo}<br>
     * {@literal @}{@link Title}(value = "Phone", desc = "An telephone number, usually representing a work office land line number.")<br>
     * {@literal @}{@link BeforeChange}(value = {{@literal @}ua.com.fielden.platform.entity.annotation.mutator.Handler(value = ua.com.fielden.platform.entity.validation.MaxLengthValidator.class)})
     */
    public PropertyMetaModel phone() {
        return phone;
    }

    /**
     * Title: Ref Count
     * <p>
     * Description: The count of active entities pointing to this entity.
     * <p>
     * Type: {@link java.lang.Integer}
     * <p>
     * {@literal @}{@link IsProperty}<br>
     * {@literal @}{@link MapTo}<br>
     * {@literal @}{@link Title}(value = "Ref Count", desc = "The count of active entities pointing to this entity.")<br>
     * {@literal @}{@link Readonly}<br>
     * {@literal @}{@link Required}<br>
     * {@literal @}{@link GreaterOrEqual}(value = 0)
     */
    public PropertyMetaModel refCount() {
        return refCount;
    }

    /**
     * Title: Last Name
     * <p>
     * Description: Person's last name.
     * <p>
     * Type: {@link String}
     * <p>
     * {@literal @}{@link IsProperty}<br>
     * {@literal @}{@link MapTo}<br>
     * {@literal @}{@link Required}<br>
     * {@literal @}{@link Title}(value = "Last Name", desc = "Person's last name.")
     */
    public PropertyMetaModel surname() {
        return surname;
    }

    /**
     * Title: Title
     * <p>
     * Description: Person's role, position or title.
     * <p>
     * Type: {@link String}
     * <p>
     * {@literal @}{@link IsProperty}(length = 255)<br>
     * {@literal @}{@link MapTo}<br>
     * {@literal @}{@link Title}(value = "Title", desc = "Person's role, position or title.")<br>
     * {@literal @}{@link BeforeChange}(value = {{@literal @}ua.com.fielden.platform.entity.annotation.mutator.Handler(value = ua.com.fielden.platform.entity.validation.MaxLengthValidator.class)})
     */
    public PropertyMetaModel title() {
        return title;
    }

    /**
     * Title: User
     * <p>
     * Description: An application user associated with the current person.
     * <p>
     * Type: {@link ua.com.fielden.platform.security.user.User}
     * <p>
     * Meta-model: {@link UserMetaModel}
     * <p>
     * {@literal @}{@link IsProperty}<br>
     * {@literal @}{@link Unique}<br>
     * {@literal @}{@link MapTo}<br>
     * {@literal @}{@link Title}(value = "User", desc = "An application user associated with the current person.")<br>
     * {@literal @}{@link SkipEntityExistsValidation}(skipActiveOnly = true)
     */
    public UserMetaModel user() {
        return user.get();
    }
}
