package dwarf.framework.model;

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
    public List<User> getAllUsers() {
        return USERS;
    }

    @PostMapping
    public void registerNewUser(@RequestBody User user) {
        System.out.println(user);
    }

    @DeleteMapping(path = "{userId}")
    public void deleteUser(@PathVariable Integer userId) {
        System.out.println(userId);
    }

    @PutMapping(path = "{userId}")
    public void updateUser(@PathVariable Integer userId, @RequestBody String userName) {
        System.out.println(String.format("%d %s", userId, userName));
    }
}
