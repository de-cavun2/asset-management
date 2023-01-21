package ua.com.fielden.platform.security.user.meta;

import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.util.function.Supplier;
import javax.annotation.processing.Generated;
import ua.com.fielden.platform.annotations.metamodel.MetaModelForType;
import ua.com.fielden.platform.entity.AbstractEntity;
import ua.com.fielden.platform.processors.metamodel.models.EntityMetaModel;
import ua.com.fielden.platform.processors.metamodel.models.PropertyMetaModel;
import ua.com.fielden.platform.security.user.User;

/**
 * Title: User
 * <p>
 * Description: User entity
 * <p>
 * Auto-generated meta-model for {@link User}.
 * <p>
 * Generation datetime: 09-01-2023 23:19:50.286 EET
 * <p>
 * Generated by {@link ua.com.fielden.platform.processors.metamodel.MetaModelProcessor}
 * <p>
 */
@Generated(
        value = "ua.com.fielden.platform.processors.metamodel.MetaModelProcessor",
        date = "2023-01-09T23:19:50.286+02:00"
)
@MetaModelForType(User.class)
public class UserMetaModel extends EntityMetaModel {
    public static final Class<User> TYPE = ua.com.fielden.platform.security.user.User.class;

    public static final String active_ = "active";

    public static final String base_ = "base";

    public static final String basedOnUser_ = "basedOnUser";

    public static final String createdBy_ = "createdBy";

    public static final String createdDate_ = "createdDate";

    public static final String createdTransactionGuid_ = "createdTransactionGuid";

    public static final String email_ = "email";

    public static final String id_ = "id";

    public static final String key_ = "key";

    public static final String lastUpdatedBy_ = "lastUpdatedBy";

    public static final String lastUpdatedDate_ = "lastUpdatedDate";

    public static final String lastUpdatedTransactionGuid_ = "lastUpdatedTransactionGuid";

    public static final String refCount_ = "refCount";

    public static final String roles_ = "roles";

    public static final String ssoOnly_ = "ssoOnly";

    private final PropertyMetaModel active;

    private final PropertyMetaModel base;

    private Supplier<UserMetaModel> basedOnUser;

    private Supplier<UserMetaModel> createdBy;

    private final PropertyMetaModel createdDate;

    private final PropertyMetaModel createdTransactionGuid;

    private final PropertyMetaModel email;

    private final PropertyMetaModel id;

    private final PropertyMetaModel key;

    private Supplier<UserMetaModel> lastUpdatedBy;

    private final PropertyMetaModel lastUpdatedDate;

    private final PropertyMetaModel lastUpdatedTransactionGuid;

    private final PropertyMetaModel refCount;

    private final PropertyMetaModel roles;

    private final PropertyMetaModel ssoOnly;

    public UserMetaModel(final String path) {
        super(path);
        this.active = new PropertyMetaModel(joinPath(active_));
        this.base = new PropertyMetaModel(joinPath(base_));
        this.basedOnUser = () -> {
                  ua.com.fielden.platform.security.user.meta.UserMetaModel value = new ua.com.fielden.platform.security.user.meta.UserMetaModel(joinPath(basedOnUser_));
                  basedOnUser = () -> value;
                  return value;
                };
        this.email = new PropertyMetaModel(joinPath(email_));
        this.key = new PropertyMetaModel(joinPath(key_));
        this.roles = new PropertyMetaModel(joinPath(roles_));
        this.ssoOnly = new PropertyMetaModel(joinPath(ssoOnly_));
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
        this.id = new PropertyMetaModel(joinPath(id_));
    }

    public UserMetaModel() {
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
     * {@literal @}{@link BeforeChange}(value = {{@literal @}ua.com.fielden.platform.entity.annotation.mutator.Handler(value = ua.com.fielden.platform.entity.validation.ActivePropertyValidator.class)})<br>
     * {@literal @}{@link AfterChange}(value = ua.com.fielden.platform.security.user.definers.UserActivationDefiner.class)
     */
    public PropertyMetaModel active() {
        return active;
    }

    /**
     * Title: Is base user?
     * <p>
     * Description: Indicates whether this is a base user, which is used for application configuration and creation of other application users.
     * <p>
     * Type: {@link boolean}
     * <p>
     * {@literal @}{@link IsProperty}<br>
     * {@literal @}{@link Title}(value = "Is base user?", desc = "Indicates whether this is a base user, which is used for application configuration and creation of other application users.")<br>
     * {@literal @}{@link MapTo}<br>
     * {@literal @}{@link BeforeChange}(value = {{@literal @}ua.com.fielden.platform.entity.annotation.mutator.Handler(value = ua.com.fielden.platform.security.user.validators.UserBaseValidator.class)})<br>
     * {@literal @}{@link AfterChange}(value = ua.com.fielden.platform.security.user.definers.UserBaseDefiner.class)<br>
     * {@literal @}{@link Dependent}(value = {"email", "active"})
     */
    public PropertyMetaModel base() {
        return base;
    }

    /**
     * Title: Base User
     * <p>
     * Description: A user on which the current user is based (aka a profile user). This relates to the application configurations such as visibility of menu items and entity centre configurations.
     * <p>
     * Type: {@link User}
     * <p>
     * Meta-model: {@link UserMetaModel}
     * <p>
     * {@literal @}{@link IsProperty}<br>
     * {@literal @}{@link Title}(value = "Base User", desc = "A user on which the current user is based (aka a profile user). This relates to the application configurations such as visibility of menu items and entity centre configurations.")<br>
     * {@literal @}{@link MapTo}<br>
     * {@literal @}{@link BeforeChange}(value = {{@literal @}ua.com.fielden.platform.entity.annotation.mutator.Handler(value = ua.com.fielden.platform.security.user.validators.UserBaseOnUserValidator.class)})<br>
     * {@literal @}{@link AfterChange}(value = ua.com.fielden.platform.security.user.definers.UserBasedOnUserDefiner.class)
     */
    public UserMetaModel basedOnUser() {
        return basedOnUser.get();
    }

    /**
     * Title: Created by User
     * <p>
     * Description: The user who originally created this entity instance.
     * <p>
     * Type: {@link User}
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
     * Title: Email
     * <p>
     * Description: User email, which is used for password resets
     * <p>
     * Type: {@link String}
     * <p>
     * {@literal @}{@link IsProperty}<br>
     * {@literal @}{@link MapTo}<br>
     * {@literal @}{@link Title}(value = "Email", desc = "User email, which is used for password resets")<br>
     * {@literal @}{@link BeforeChange}(value = {{@literal @}ua.com.fielden.platform.entity.annotation.mutator.Handler(value = ua.com.fielden.platform.property.validator.EmailValidator.class), {@literal @}ua.com.fielden.platform.entity.annotation.mutator.Handler(value = ua.com.fielden.platform.entity.validation.UserAlmostUniqueEmailValidator.class)})<br>
     * {@literal @}{@link Dependent}(value = {"base"})
     */
    public PropertyMetaModel email() {
        return email;
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
     * Type: {@link String}
     * <p>
     * {@literal @}{@link IsProperty}<br>
     * {@literal @}{@link MapTo}<br>
     * {@literal @}{@link BeforeChange}(value = {{@literal @}ua.com.fielden.platform.entity.annotation.mutator.Handler(value = ua.com.fielden.platform.property.validator.StringValidator.class, str = {{@literal @}ua.com.fielden.platform.entity.annotation.mutator.StrParam(name = "regex", value = "^\\w+$")})})
     */
    public PropertyMetaModel key() {
        return key;
    }

    /**
     * Title: Last Updated By
     * <p>
     * Description: The user who was the last to update this entity instance.
     * <p>
     * Type: {@link User}
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
     * Title: Roles
     * <p>
     * Description: The associated with this user roles.
     * <p>
     * Type: {@link java.util.Set<ua.com.fielden.platform.security.user.UserAndRoleAssociation>}
     * <p>
     * {@literal @}{@link IsProperty}(value = ua.com.fielden.platform.security.user.UserAndRoleAssociation.class, linkProperty = "user")<br>
     * {@literal @}{@link Title}(value = "Roles", desc = "The associated with this user roles.")
     */
    public PropertyMetaModel roles() {
        return roles;
    }

    /**
     * Title: SSO only?
     * <p>
     * Description: Only relevant in the SSO authentication mode. Controls the ability for users to loging with Reduced Sign-On (value false) or Signle Sign-On only (value true).
     * <p>
     * Type: {@link boolean}
     * <p>
     * {@literal @}{@link IsProperty}<br>
     * {@literal @}{@link MapTo}<br>
     * {@literal @}{@link Title}(value = "SSO only?", desc = "Only relevant in the SSO authentication mode. Controls the ability for users to loging with Reduced Sign-On (value false) or Signle Sign-On only (value true).")<br>
     * {@literal @}{@link AfterChange}(value = ua.com.fielden.platform.security.user.definers.UserSsoOnlyDefiner.class)
     */
    public PropertyMetaModel ssoOnly() {
        return ssoOnly;
    }
}
