package decavun2.change.master.menu.actions;

import com.google.inject.Inject;

import ua.com.fielden.platform.security.Authorise;
import ua.com.fielden.platform.dao.annotations.SessionRequired;
import decavun2.security.tokens.compound_master_menu.ChangeMaster_OpenMain_MenuItem_CanAccess_Token;
import ua.com.fielden.platform.dao.CommonEntityDao;
import ua.com.fielden.platform.entity.query.IFilter;
import ua.com.fielden.platform.entity.annotation.EntityType;

/**
 * DAO implementation for companion object {@link ChangeMaster_OpenMain_MenuItemCo}.
 *
 * @author Developers
 *
 */
@EntityType(ChangeMaster_OpenMain_MenuItem.class)
public class ChangeMaster_OpenMain_MenuItemDao extends CommonEntityDao<ChangeMaster_OpenMain_MenuItem> implements ChangeMaster_OpenMain_MenuItemCo {

    @Inject
    public ChangeMaster_OpenMain_MenuItemDao(final IFilter filter) {
        super(filter);
    }

    @Override
    @SessionRequired
    @Authorise(ChangeMaster_OpenMain_MenuItem_CanAccess_Token.class)
    public ChangeMaster_OpenMain_MenuItem save(ChangeMaster_OpenMain_MenuItem entity) {
        return super.save(entity);
    }

}