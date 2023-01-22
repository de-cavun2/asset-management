package decavun2.objects;

import com.google.inject.Inject;

import java.util.Collection;
import java.util.List;

import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.security.Authorise;
import ua.com.fielden.platform.dao.annotations.SessionRequired;
import decavun2.security.tokens.persistent.VehicleFinDet_CanSave_Token;
import decavun2.objects.VehicleFinDet;
import decavun2.objects.VehicleFinDetCo;
import decavun2.security.tokens.persistent.VehicleFinDet_CanDelete_Token;
import ua.com.fielden.platform.dao.CommonEntityDao;
import ua.com.fielden.platform.entity.query.IFilter;
import ua.com.fielden.platform.entity.annotation.EntityType;

/**
 * DAO implementation for companion object {@link VehicleFinDetCo}.
 *
 * @author Developers
 *
 */
@EntityType(VehicleFinDet.class)
public class VehicleFinDetDao extends CommonEntityDao<VehicleFinDet> implements VehicleFinDetCo {

    @Inject
    public VehicleFinDetDao(final IFilter filter) {
        super(filter);
    }

    @Override
    @SessionRequired
    @Authorise(VehicleFinDet_CanSave_Token.class)
    public VehicleFinDet save(VehicleFinDet entity) {
        return super.save(entity);
    }

    @Override
    @SessionRequired
    @Authorise(VehicleFinDet_CanDelete_Token.class)
    public int batchDelete(final Collection<Long> entitiesIds) {
        return defaultBatchDelete(entitiesIds);
    }

    @Override
    @SessionRequired
    @Authorise(VehicleFinDet_CanDelete_Token.class)
    public int batchDelete(final List<VehicleFinDet> entities) {
        return defaultBatchDelete(entities);
    }

    @Override
    protected IFetchProvider<VehicleFinDet> createFetchProvider() {
        return FETCH_PROVIDER;
    }
}