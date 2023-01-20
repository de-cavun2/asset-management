package decavun2.personnel.validators;

import java.lang.annotation.Annotation;
import java.util.Set;

import decavun2.personnel.Person;
import ua.com.fielden.platform.entity.meta.MetaProperty;
import ua.com.fielden.platform.entity.meta.impl.AbstractBeforeChangeEventHandler;
import ua.com.fielden.platform.error.Result;

public class NoSpacesValidator extends AbstractBeforeChangeEventHandler<String> {
    public static final String ERR_SPACES = "No spaces are permitted for [%s] in entity [%s].";

    @Override
    public Result handle(MetaProperty<String> property, String newValue, Set<Annotation> mutatorAnnotations) {
        if (newValue.contains(" ")) {
            return Result.failure(ERR_SPACES.formatted(property.getTitle(), Person.ENTITY_TITLE));
        } else {
            return Result.successful(newValue);
        }
    }

}
