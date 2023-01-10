package decavun2.personnel;

import static ua.com.fielden.platform.entity.query.fluent.EntityQueryUtils.fetch;
import static ua.com.fielden.platform.entity.query.fluent.EntityQueryUtils.from;
import static ua.com.fielden.platform.entity.query.fluent.EntityQueryUtils.select;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import com.google.inject.Inject;

import decavun2.security.tokens.persistent.Person_CanSave_Token;
import decavun2.security.tokens.persistent.Person_CanDelete_Token;

import ua.com.fielden.platform.dao.CommonEntityDao;
import ua.com.fielden.platform.dao.annotations.SessionRequired;
import ua.com.fielden.platform.entity.annotation.EntityType;
import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.entity.query.IFilter;
import ua.com.fielden.platform.entity.query.fluent.fetch;
import ua.com.fielden.platform.entity.query.model.EntityResultQueryModel;
import ua.com.fielden.platform.error.Result;
import ua.com.fielden.platform.security.Authorise;

/**
 * DAO implementation for companion object {@link PersonCo}.
 *
 * @author Generated
 */
@EntityType(Person.class)
public class PersonDao extends CommonEntityDao<Person> implements PersonCo {

    @Inject
    protected PersonDao(final IFilter filter) {
        super(filter);
    }

    @Override
    public Person new_() {
        return super.new_().setActive(true);
    }

    @Override
    @SessionRequired
    @Authorise(Person_CanSave_Token.class)
    public Person save(final Person person) {
        person.isValid().ifFailure(Result::throwRuntime);
        return super.save(person);
    }

    @Override
    @SessionRequired
    @Authorise(Person_CanDelete_Token.class)
    public int batchDelete(final Collection<Long> entitiesIds) {
        return defaultBatchDelete(entitiesIds);
    }

    @Override
    @SessionRequired
    @Authorise(Person_CanDelete_Token.class)
    public int batchDelete(final List<Person> entities) {
        return defaultBatchDelete(entities);
    }

    @Override
    public Optional<Person> currentPerson(final fetch<Person> fetchPerson) {
        final EntityResultQueryModel<Person> qExecModel = select(Person.class).where().prop("user").eq().val(getUser()).model();
        return getEntityOptional(from(qExecModel).with(fetchPerson).model());
    }

    @Override
    public Optional<Person> currentPerson() {
        return currentPerson(fetch(Person.class));
    }

    @Override
    protected IFetchProvider<Person> createFetchProvider() {
        return PersonCo.FETCH_PROVIDER;
    }

}
