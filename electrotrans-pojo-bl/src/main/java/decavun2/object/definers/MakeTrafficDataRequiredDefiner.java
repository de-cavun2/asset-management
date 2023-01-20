package decavun2.object.definers;

import static metamodels.MetaModels.Route_;

import ua.com.fielden.platform.entity.meta.MetaProperty;
import ua.com.fielden.platform.entity.meta.impl.AbstractAfterChangeEventHandler;

public class MakeTrafficDataRequiredDefiner extends AbstractAfterChangeEventHandler<Boolean> {

	@Override
	public void handle(MetaProperty<Boolean> mpinUse, final Boolean value) {
		mpinUse.getEntity().getProperty(Route_.trafficData()).setRequired(value);        
	}

}
