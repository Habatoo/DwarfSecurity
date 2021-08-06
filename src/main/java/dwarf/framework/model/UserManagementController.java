package dwarf.framework.model;

import dwarf.framework.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Контроллер работы с пользователями.
 * @version 0.001
 * @author habatoo
 */
@RestController
@RequestMapping("management/api/v1/users")
public class UserManagementController {
    private final UserRepository userRepository;

    @Autowired
    public UserManagementController(UserRepository userRepository
    ) {
        this.userRepository = userRepository;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'EVENT_ADMIN')")
    public List<User> getAllUsers() {
        return userRepository.findAll();
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
