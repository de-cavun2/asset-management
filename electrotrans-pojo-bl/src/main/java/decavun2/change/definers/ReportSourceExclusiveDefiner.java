package decavun2.change.definers;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import decavun2.change.Report;
import decavun2.change.ReportCo;
import metamodels.MetaModels;
import ua.com.fielden.platform.entity.meta.MetaProperty;
import ua.com.fielden.platform.entity.meta.impl.AbstractAfterChangeEventHandler;

public class ReportSourceExclusiveDefiner extends AbstractAfterChangeEventHandler<String> {

	@Override
	public void handle(MetaProperty<String> mp, String value) {
		final Report report = mp.getEntity();
		if (report.isInitialising()) {
            mp.setRequired(!StringUtils.isEmpty(value), ReportCo.ERR_ONE_OF_REPORT_SOURCE_FIELDS_SHOULD_NOT_BE_EMPTY);
        } else {
            if (StringUtils.isEmpty(value)) {
                return;
            }
            final List<MetaProperty<String>> mpOwnershipTypesList = Arrays.asList(
            		report.getProperty(MetaModels.Report_.change()), 
            		report.getProperty(MetaModels.Report_.issue())
);
            mp.setRequired(true, ReportCo.ERR_ONE_OF_REPORT_SOURCE_FIELDS_SHOULD_NOT_BE_EMPTY);
            for (var mpOtherType : mpOwnershipTypesList) {
                if (mpOtherType.getName() != mp.getName()) {
                    mpOtherType.setRequired(false);
                    report.set(mpOtherType.getName(), null);
                }
            }
        }
		
	}

}
