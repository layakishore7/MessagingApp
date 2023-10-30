package Project.MessagingApp.service;

import Project.MessagingApp.entities.User;
import Project.MessagingApp.repository.UserRepository;
import Project.MessagingApp.serviceClass.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepoMock;

    //private List<User>  users = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        userService = new UserService(userRepoMock);
    }

    @Test
    public void testAllUsers() {
        User user1 = new User(1, "Laya Kishore", "Laya123", "USER,ADMIN");
        User user2 = new User(1, "Nookesh", "Nookesh123", "USER");
        List<User> users = Arrays.asList(user1, user2);
        when(userRepoMock.findAll()).thenReturn(users);

        List<User> result = userService.getallUsers();

        assertEquals(2, result.size(), "number of returned users should match");
    }
}
