package dwarf.framework.model;

import dwarf.framework.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер работы с пользователями.
 * @version 0.001
 * @author habatoo
 */
@RestController
@RequestMapping("api/v1/users")
public class UserController {
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository
    ) {
        this.userRepository = userRepository;
    }

    /**
     * @method userList - при http GET запросе по адресу .../api/auth/users
     * @return {@code List<user>} - список всех пользователей с полными данными пользователей.
     * @see User
     */
    @GetMapping(path = "{userId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'EVENT_ADMIN')")
    public User getUser(@PathVariable("userId") Integer userId) {
        return userRepository.getById(userId);
    }

}
