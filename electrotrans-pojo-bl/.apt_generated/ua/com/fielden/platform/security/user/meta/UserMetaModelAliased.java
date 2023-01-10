package ua.com.fielden.platform.security.user.meta;

import java.lang.Override;
import java.lang.String;
import ua.com.fielden.platform.annotations.metamodel.MetaModelForType;
import ua.com.fielden.platform.processors.metamodel.exceptions.EntityMetaModelAliasedException;
import ua.com.fielden.platform.security.user.User;

@MetaModelForType(User.class)
public class UserMetaModelAliased extends UserMetaModel {
    public final String alias;

    public UserMetaModelAliased(final String alias) {
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
