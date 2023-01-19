package decavun2.change.master.menu.actions;

import com.google.inject.Inject;

import ua.com.fielden.platform.security.Authorise;
import ua.com.fielden.platform.dao.annotations.SessionRequired;
import decavun2.security.tokens.compound_master_menu.ChangeMaster_OpenReport_MenuItem_CanAccess_Token;
import ua.com.fielden.platform.dao.CommonEntityDao;
import ua.com.fielden.platform.entity.query.IFilter;
import ua.com.fielden.platform.entity.annotation.EntityType;

/**
 * DAO implementation for companion object {@link ChangeMaster_OpenReport_MenuItemCo}.
 *
 * @author Developers
 *
 */
@EntityType(ChangeMaster_OpenReport_MenuItem.class)
public class ChangeMaster_OpenReport_MenuItemDao extends CommonEntityDao<ChangeMaster_OpenReport_MenuItem> implements ChangeMaster_OpenReport_MenuItemCo {

    @Inject
    public ChangeMaster_OpenReport_MenuItemDao(final IFilter filter) {
        super(filter);
    }

    @Override
    @SessionRequired
    @Authorise(ChangeMaster_OpenReport_MenuItem_CanAccess_Token.class)
    public ChangeMaster_OpenReport_MenuItem save(ChangeMaster_OpenReport_MenuItem entity) {
        return super.save(entity);
    }

}