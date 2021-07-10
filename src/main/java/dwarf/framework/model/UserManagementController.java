package dwarf.framework.model;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("management/api/v1/users")
public class UserManagementController {

    private static final List<User> USERS = Arrays.asList(
            new User(1, "Admin"),
            new User(2, "Moderator"),
            new User(3, "User")
    );

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'EVENT_ADMIN')")
    // hasRole("ROLE_") hasAnyRole("ROLE_") hasAuthority("permission") hasAnyAuthority("permission")
    public List<User> getAllUsers() {
        return USERS;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('student:write')")
    public void registerNewUser(@RequestBody User user) {
        System.out.println(user);
    }

    @DeleteMapping(path = "{userId}")
    @PreAuthorize("hasAuthority('student:delete')")
    public void deleteUser(@PathVariable Integer userId) {
        System.out.println(userId);
    }

    @PutMapping(path = "{userId}")
    @PreAuthorize("hasAuthority('student:write')")
    public void updateUser(@PathVariable Integer userId, @RequestBody String userName) {
        System.out.println(String.format("%d %s", userId, userName));
    }
}
