package dwarf.framework.service;

import dwarf.framework.exception.BadRequestException;
import dwarf.framework.exception.UserNotFoundException;
import dwarf.framework.model.User;
import dwarf.framework.repo.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    /**
     * Add new {@link User}, check if {@link User#username} is unique.
     * @param user
     */
    public void addUser(User user) {
        Optional<User> existsUser = userRepository
                .findByUsername(user.getUsername());
        if (existsUser.isPresent()) {
            throw new BadRequestException(
                    "User " + user.getUsername() + " is already exists!");
        }
        userRepository.save(user);
    }

    /**
     * Delete {@link User} by {@link User#userId}
     */
    public void deleteUser(Integer userid) {
        if (!userRepository.existsByUserId(userid)) {
            throw new UserNotFoundException(
                    "User with userId "  + userid + " is not exists!");
        }
        userRepository.deleteById(userid);
    }


}
