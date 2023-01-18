package decavun2.personnel;

import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.utils.EntityUtils;

import static metamodels.MetaModels.PersonRole_;

import metamodels.MetaModels;
import ua.com.fielden.platform.dao.IEntityDao;

/**
 * Companion object for entity {@link PersonRole}.
 *
 * @author Developers
 *
 */
public interface PersonRoleCo extends IEntityDao<PersonRole> {

    static final IFetchProvider<PersonRole> FETCH_PROVIDER = EntityUtils.fetch(PersonRole.class)
            .with(PersonRole_.name(), PersonRole_.desc());
}
