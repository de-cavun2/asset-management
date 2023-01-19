package metamodels;

import decavun2.change.meta.ReportMetaModel;
import decavun2.change.meta.ReportMetaModelAliased;
import decavun2.personnel.meta.PersonMetaModel;
import decavun2.personnel.meta.PersonMetaModelAliased;
import java.lang.String;
import javax.annotation.processing.Generated;
import ua.com.fielden.platform.security.user.meta.UserMetaModel;
import ua.com.fielden.platform.security.user.meta.UserMetaModelAliased;

/**
 * Generation datetime: 19-01-2023 20:06:51.753 EET
 * <p>
 * Generated by {@link ua.com.fielden.platform.processors.metamodel.MetaModelProcessor}.
 */
@Generated(
        value = "ua.com.fielden.platform.processors.metamodel.MetaModelProcessor",
        date = "2023-01-19T20:06:51.753+02:00"
)
public final class MetaModels {
    public static final PersonMetaModel Person_ = new PersonMetaModel();

    public static final ReportMetaModel Report_ = new ReportMetaModel();

    public static final UserMetaModel User_ = new UserMetaModel();

    public static PersonMetaModelAliased Person_(final String alias) {
        final PersonMetaModelAliased aliased = new PersonMetaModelAliased(alias);
        return aliased;
    }

    public static ReportMetaModelAliased Report_(final String alias) {
        final ReportMetaModelAliased aliased = new ReportMetaModelAliased(alias);
        return aliased;
    }

    public static UserMetaModelAliased User_(final String alias) {
        final UserMetaModelAliased aliased = new UserMetaModelAliased(alias);
        return aliased;
    }
}
