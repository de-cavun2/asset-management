package decavun2.data;

import static org.junit.Assert.assertNotNull;

import java.util.Optional;

import decavun2.personnel.Person;
import decavun2.personnel.PersonRole;
import ua.com.fielden.platform.dao.annotations.SessionRequired;
import ua.com.fielden.platform.data.IDomainDrivenData;
import ua.com.fielden.platform.entity.AbstractEntity;
import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.entity.query.fluent.fetch;
import ua.com.fielden.platform.error.Result;
import ua.com.fielden.platform.reflection.PropertyTypeDeterminator;
import ua.com.fielden.platform.security.user.IUser;
import ua.com.fielden.platform.security.user.User;
import ua.com.fielden.platform.security.user.UserAndRoleAssociation;
import ua.com.fielden.platform.security.user.UserRole;
import ua.com.fielden.platform.test.ioc.UniversalConstantsForTesting;
import ua.com.fielden.platform.test_data.EnsureData;
import ua.com.fielden.platform.utils.IUniversalConstants;

/**
 * This provides valuable representative sample data, which can be used as a basis for comprehensive tests.
 *
 * @author Generated
 */
public interface IDomainData extends IDomainDrivenData {

    public static final String EXTERNAL = "EXTERNAL";

    default <E extends AbstractEntity<?>, O extends AbstractEntity<?>> E fetchEntityForPropOf(final String propName, final Class<O> entityType, final Object... keyValues) {
        final Class<E> entityClass = (Class<E>) PropertyTypeDeterminator.determinePropertyType(entityType, propName);
        final IFetchProvider<O> fetchProvider = co(entityType).getFetchProvider();
        final fetch<E> eFetch = fetchProvider.<E> fetchFor(propName).fetchModel();
        return co(entityClass).findByKeyAndFetch(eFetch, keyValues);
    }

    default Person setupPerson(final User.system_users systemUser, final String emailDomain, final String name, final String surname, final PersonRole personRole) {
        final User user = fetchEntityForPropOf("user", Person.class, systemUser.name());
        return createAndSavePerson(systemUser.name() + "@" + emailDomain, name, surname, user, personRole);
    }

    default Person createAndSavePerson(final String email, final String name, final String surname, final User user, final PersonRole personRole) {
        return save(new_composite(Person.class, email).setName(name).setSurname(surname).setActive(true).setUser(user).setPersonRole(personRole));
    }

    default void ensureAdminBaseUserExists(final IUser co$User) {
        if (!co$User.entityWithKeyExists(ADMIN + BASE_SUFFIX)) {
            final User adminBase = co$User.save(new_(User.class, ADMIN + BASE_SUFFIX).setBase(true).setEmail(ADMIN + BASE_SUFFIX + "@fielden.com.au").setActive(true));
            final UserRole adminRole = co(UserRole.class).findByKeyOptional(ADMIN)
                    .orElseThrow(() -> Result.failure("ADMIN role does not exist"));

            save(new_composite(UserAndRoleAssociation.class, adminBase, adminRole));
            co$User.resetPasswd(adminBase, SUPER_SECRET_PASSWORD);
        }
    }

    default void mkUserAndPerson(final String userName, final String name, final String surname, final String email, final String title, final PersonRole personRole) {
        final IUser co$User = co$(User.class);
        ensureAdminBaseUserExists(co$User);

        final User adminBase = fetchEntityForPropOf("basedOnUser", User.class, ADMIN + BASE_SUFFIX);
        assertNotNull(adminBase);
        final User newUser = co$User.save(new_(User.class, userName.toUpperCase()).setBase(false).setBasedOnUser(adminBase).setEmail(email).setActive(true));
        final User user = co$User.resetPasswd(newUser, SUPER_SECRET_PASSWORD).getKey();

        final UserRole admin = co(UserRole.class).findByKeyOptional(ADMIN).orElseThrow(() -> Result.failure("ADMIN role does not exist"));

        save(new_composite(UserAndRoleAssociation.class, user, admin));

        createAndSavePerson(email, name, surname, user, personRole);
    }

    @EnsureData({})
    @SessionRequired(allowNestedScope = false)
    default void setupBaseUsers() {
        final IUser co$User = co$(User.class);
        if (!co$User.entityWithKeyExists(ADMIN + BASE_SUFFIX)) {
            final User adminBase = co$User.save(new_(User.class, ADMIN + BASE_SUFFIX).setBase(true).setEmail(ADMIN + BASE_SUFFIX + "@fielden.com.au").setActive(true));
            final UserRole adminRole = co(UserRole.class).findByKeyOptional(ADMIN).orElseThrow(() -> Result.failure("ADMIN role does not exist"));

            save(new_composite(UserAndRoleAssociation.class, adminBase, adminRole));
            co$User.resetPasswd(adminBase, SUPER_SECRET_PASSWORD);
        }
        if (!co$User.entityWithKeyExists(EXTERNAL + BASE_SUFFIX)) {
            final User externalBase = co$User.save(new_(User.class, EXTERNAL + BASE_SUFFIX).setBase(true).setEmail(EXTERNAL + BASE_SUFFIX + "@fielden.com.au").setActive(true));
            final UserRole externalRole = save(new_(UserRole.class, EXTERNAL, "External User Role").setActive(true));

            save(new_composite(UserAndRoleAssociation.class, externalBase, externalRole));
            co$User.resetPasswd(externalBase, SUPER_SECRET_PASSWORD);
        }
    }

    default Optional<UniversalConstantsForTesting> getConstantsForTesting() {
        final IUniversalConstants constants = getInstance(IUniversalConstants.class);
        if (constants instanceof UniversalConstantsForTesting) {
            final UniversalConstantsForTesting testingConstants = (UniversalConstantsForTesting) constants;
            return Optional.of(testingConstants);
        } else {
            return Optional.empty();
        }
    }

}
