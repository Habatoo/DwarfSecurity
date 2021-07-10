package dwarf.framework.security;

import com.google.common.collect.Sets;
import java.util.Set;
import static dwarf.framework.security.ApplicationUserPermission.*;

public enum ApplicationUserRole {
    ADMIN(Sets.newHashSet(EVENT_DELETE,
            EVENT_READ,
            EVENT_WRITE,
            USER_DELETE,
            USER_READ,
            USER_WRITE)),
    EVENT_ADMIN(Sets.newHashSet(EVENT_DELETE,
            EVENT_READ,
            EVENT_WRITE,
            USER_READ)),
    MOD(Sets.newHashSet(
            EVENT_READ,
            EVENT_WRITE,
            USER_READ,
            USER_WRITE)),
    USER(Sets.newHashSet(
            EVENT_READ,
            USER_READ));

    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }
}
