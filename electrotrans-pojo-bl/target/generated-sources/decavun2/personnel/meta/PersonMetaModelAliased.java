package decavun2.personnel.meta;

import decavun2.personnel.Person;
import java.lang.Override;
import java.lang.String;
import ua.com.fielden.platform.annotations.metamodel.MetaModelForType;
import ua.com.fielden.platform.processors.metamodel.exceptions.EntityMetaModelAliasedException;

@MetaModelForType(Person.class)
public class PersonMetaModelAliased extends PersonMetaModel {
    public final String alias;

    public PersonMetaModelAliased(final String alias) {
        super(alias);
        if (alias.isBlank()) {
            throw new EntityMetaModelAliasedException("An alias cannot be blank.");
        }
        this.alias = alias;
    }

    @Override
    public String toPath() {
        return this.path.isEmpty() ? this.alias : this.path;
    }
}
