package decavun2.security.tokens.open_simple_master;

import static java.lang.String.format;
import static ua.com.fielden.platform.reflection.TitlesDescsGetter.getEntityTitleAndDesc;

import decavun2.security.tokens.UsersAndPersonnelModuleToken;
import decavun2.personnel.Person;
import ua.com.fielden.platform.security.tokens.Template;

/**
 * A security token for entity {@link Person} to guard MASTER_OPEN.
 *
 * @author Generated
 */
public class PersonMaster_CanOpen_Token extends UsersAndPersonnelModuleToken {

    private final static String ENTITY_TITLE = getEntityTitleAndDesc(Person.class).getKey() + " Master";
    public final static String TITLE = format(Template.MASTER_OPEN.forTitle(), ENTITY_TITLE);
    public final static String DESC = format(Template.MASTER_OPEN.forDesc(), ENTITY_TITLE);

}
