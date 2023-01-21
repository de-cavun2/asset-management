package decavun2.security.tokens.persistent;

import static java.lang.String.format;

import ua.com.fielden.platform.security.tokens.Template;
import decavun2.objects.Order;
import decavun2.security.tokens.UsersAndPersonnelModuleToken;

/**
 * A security token for entity {@link Order} to guard Delete.
 *
 * @author Developers
 *
 */
public class Order_CanDelete_Token extends UsersAndPersonnelModuleToken {
    public final static String TITLE = format(Template.DELETE.forTitle(), Order.ENTITY_TITLE);
    public final static String DESC = format(Template.DELETE.forDesc(), Order.ENTITY_TITLE);
}