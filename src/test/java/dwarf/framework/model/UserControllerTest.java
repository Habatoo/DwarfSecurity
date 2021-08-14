package dwarf.framework.model;

import dwarf.framework.repo.UserRepository;
import dwarf.framework.service.UserService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserRepository userRepository;
    private UserController userController;
    private UserService userService;

    @BeforeEach
    void setUp() {
        userController = new UserController(
                userService,
                userRepository);
        //userController.addUser(new User("habatoo"));
    }

    @Test
    void getUser_Test() {
        // when
        userController.getUser(1);
        // then
        Mockito.verify(userRepository).getById(1);
    }

    @Test
    void getAllUser_Test() {
        // when
        userController.getAllUser();
        // then
        Mockito.verify(userRepository).findAll();
    }

    @Test
    void addUser_Test() {
        // when
        userController.addUser(new User("habatoo"));;
        // then
        Mockito.verify(userRepository).getById(1);
    }

    @Test
    void deleteUser_Test() {
        // when
        userController.deleteUser(2);;
        // then
        Mockito.verify(userRepository).getById(2);
    }
}