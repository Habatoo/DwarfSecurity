package dwarf.framework.model;

import dwarf.framework.repo.UserRepository;
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

    @BeforeEach
    void setUp() {
        userController = new UserController(userRepository);
    }

    @Test
    void getUser_Test() {
        // when
        userController.getUser(1);
        // then
        Mockito.verify(userRepository).getById(1);
    }
}