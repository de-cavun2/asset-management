package decavun2.security.tokens.persistent;

import static java.lang.String.format;
import static ua.com.fielden.platform.reflection.TitlesDescsGetter.getEntityTitleAndDesc;
import static ua.com.fielden.platform.reflection.TitlesDescsGetter.getTitleAndDesc;

import decavun2.security.tokens.UsersAndPersonnelModuleToken;
import decavun2.personnel.Person;
import ua.com.fielden.platform.security.tokens.Template;

/**
 * A token to guard {@link Person${symbol_poundset}User(ua.com.fielden.platform.security.user.User)}.
 *
 * @author Generated
 */
public class Person_CanModify_user_Token extends UsersAndPersonnelModuleToken {

    private static final String ENTITY_TITLE = getEntityTitleAndDesc(Person.class).getKey();
    private static final String PROP_TITLE = getTitleAndDesc("user", Person.class).getKey();
    public static final String TITLE = format(Template.MODIFY.forTitle(), ENTITY_TITLE, PROP_TITLE);
    public static final String DESC = format(Template.MODIFY.forDesc(), ENTITY_TITLE);

}
