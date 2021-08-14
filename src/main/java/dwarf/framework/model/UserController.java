package dwarf.framework.model;

import dwarf.framework.repo.UserRepository;
import dwarf.framework.service.UserService;
import lombok.AllArgsConstructor;
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
@RequestMapping("api/v1/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    /**
     * @method userList - при http GET запросе по адресу .../api/auth/users/{id}
     * @return {@code User} - пользователя с полными данными.
     * @see User
     */
    @GetMapping(path = "{userId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'EVENT_ADMIN')")
    public User getUser(@PathVariable("userId") Integer userId) {
        return userRepository.getById(userId);
    }

    /**
     * @method getAllUser - при http GET запросе по адресу .../api/auth/users
     * @return {@code List<user>} - список всех пользователей с полными данными пользователей.
     * @see User
     */
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'EVENT_ADMIN')")
    public List<User> getAllUser() {
        return userService.getAllUser();
    }

    /**
     * @method getAllUser - при http POST запросе по адресу .../api/auth/users/
     * добавляет пользователя.
     * @see User
     */
    @PostMapping
    public void addUser(@RequestBody User user) {
        userService.addUser(user);
    }

    /**
     * @method deleteUser - при http DELETE запросе по адресу .../api/auth/users/
     * удаляет пользователя.
     * @see User
     */
    @DeleteMapping(path = "{userId}")
    public void deleteUser(
            @PathVariable("userId") Integer userId) {
        userService.deleteUser(userId);
    }

}
