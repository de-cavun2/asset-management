package decavun2.objects;

import com.google.inject.Inject;

import java.util.Collection;
import java.util.List;

import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.security.Authorise;
import ua.com.fielden.platform.dao.annotations.SessionRequired;
import decavun2.security.tokens.persistent.Repair_CanSave_Token;
import decavun2.security.tokens.persistent.Repair_CanDelete_Token;
import ua.com.fielden.platform.dao.CommonEntityDao;
import ua.com.fielden.platform.entity.query.IFilter;
import ua.com.fielden.platform.entity.annotation.EntityType;

/**
 * DAO implementation for companion object {@link RepairCo}.
 *
 * @author Developers
 *
 */
@EntityType(Repair.class)
public class RepairDao extends CommonEntityDao<Repair> implements RepairCo {

    @Inject
    public RepairDao(final IFilter filter) {
        super(filter);
    }

    @Override
    @SessionRequired
    @Authorise(Repair_CanSave_Token.class)
    public Repair save(Repair entity) {
        return super.save(entity);
    }

    @Override
    @SessionRequired
    @Authorise(Repair_CanDelete_Token.class)
    public int batchDelete(final Collection<Long> entitiesIds) {
        return defaultBatchDelete(entitiesIds);
    }

    @Override
    @SessionRequired
    @Authorise(Repair_CanDelete_Token.class)
    public int batchDelete(final List<Repair> entities) {
        return defaultBatchDelete(entities);
    }

    @Override
    protected IFetchProvider<Repair> createFetchProvider() {
        return FETCH_PROVIDER;
    }
}