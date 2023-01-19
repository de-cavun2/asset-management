package decavun2.objects;

import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.utils.EntityUtils;

import static metamodels.MetaModels.Repair_;
import ua.com.fielden.platform.dao.IEntityDao;

/**
 * Companion object for entity {@link Repair}.
 *
 * @author Developers
 *
 */
public interface RepairCo extends IEntityDao<Repair> {

    static final IFetchProvider<Repair> FETCH_PROVIDER = EntityUtils.fetch(Repair.class)
            .with(Repair_.repairID(), Repair_.date(), Repair_.vehicle(),
                  Repair_.issue(), Repair_.repairman(), Repair_.desc());

}