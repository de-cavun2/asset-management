package decavun2.personnel;

import com.google.inject.Inject;

import java.util.Collection;
import java.util.List;

import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.security.Authorise;
import ua.com.fielden.platform.dao.annotations.SessionRequired;
import decavun2.security.tokens.persistent.PersonRole_CanSave_Token;
import decavun2.security.tokens.persistent.PersonRole_CanDelete_Token;
import ua.com.fielden.platform.dao.CommonEntityDao;
import ua.com.fielden.platform.entity.query.IFilter;
import ua.com.fielden.platform.entity.annotation.EntityType;

/**
 * DAO implementation for companion object {@link PersonRoleCo}.
 *
 * @author Developers
 *
 */
@EntityType(PersonRole.class)
public class PersonRoleDao extends CommonEntityDao<PersonRole> implements PersonRoleCo {

    @Inject
    public PersonRoleDao(final IFilter filter) {
        super(filter);
    }
    
    @Override
    public PersonRole new_() {
        return super.new_().setActive(true);
    }

    @Override
    @SessionRequired
    @Authorise(PersonRole_CanSave_Token.class)
    public PersonRole save(PersonRole entity) {
        return super.save(entity);
    }

    @Override
    @SessionRequired
    @Authorise(PersonRole_CanDelete_Token.class)
    public int batchDelete(final Collection<Long> entitiesIds) {
        return defaultBatchDelete(entitiesIds);
    }

    @Override
    @SessionRequired
    @Authorise(PersonRole_CanDelete_Token.class)
    public int batchDelete(final List<PersonRole> entities) {
        return defaultBatchDelete(entities);
    }

    @Override
    protected IFetchProvider<PersonRole> createFetchProvider() {
        return FETCH_PROVIDER;
    }
}