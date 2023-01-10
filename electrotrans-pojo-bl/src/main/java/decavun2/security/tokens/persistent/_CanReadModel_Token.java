package decavun2.security.tokens.persistent;

import decavun2.security.tokens.AppAdminModuleToken;

/**
 * A security token that controls whether model reading should be permissive or not. If access to this token is granted then model reading is permitted for entity types, which were
 * not provided with individual security tokens. Effectively, this token controls the default authorisation behaviour for model reading in the absence of specific authorisation
 * tokens.
 *
 * @author Generated
 */
public class _CanReadModel_Token extends AppAdminModuleToken {

    public final static String TITLE = "Can Read Model (if missing token)";
    public final static String DESC = "If access to this token is granted then model reading would be permitted for entity types, which were not provided with individual security tokens.";

}
