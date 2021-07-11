package dwarf.framework.auth;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static dwarf.framework.security.ApplicationUserRole.*;

@Repository("in_memory")
public class ApplicationUserDaoService implements ApplicationUserDao {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ApplicationUserDaoService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
        return getApplicationUser()
                .stream()
                .filter(applicationUser -> username.equals(applicationUser.getUsername()))
                .findFirst();
    }

    private List<ApplicationUser> getApplicationUser() {
        List<ApplicationUser> applicationUsers = Lists.newArrayList(
                new ApplicationUser(
                        ADMIN.getGrantedAuthorities(),
                        passwordEncoder.encode("password"),
                        "Admin",
                        true,
                        true,
                        true,
                        true),

                new ApplicationUser(
                        EVENT_ADMIN.getGrantedAuthorities(),
                        passwordEncoder.encode("password"),
                        "EventAdmin",
                        true,
                        true,
                        true,
                        true),

                new ApplicationUser(
                        USER.getGrantedAuthorities(),
                        passwordEncoder.encode("password"),
                        "User",
                        true,
                        true,
                        true,
                        true)
        );

        return applicationUsers;
    }
}
