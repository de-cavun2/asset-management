package decavun2.objects;

import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.utils.EntityUtils;

import static metamodels.MetaModels.TransportCondition_;

import metamodels.MetaModels;
import ua.com.fielden.platform.dao.IEntityDao;

/**
 * Companion object for entity {@link TransportCondition}.
 *
 * @author Developers
 *
 */
public interface TransportConditionCo extends IEntityDao<TransportCondition> {

    static final IFetchProvider<TransportCondition> FETCH_PROVIDER = EntityUtils.fetch(TransportCondition.class)
    		.with(TransportCondition_.conditionId(), TransportCondition_.stage());

}
