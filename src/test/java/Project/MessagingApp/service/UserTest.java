package Project.MessagingApp.service;


import Project.MessagingApp.TestH2Repository;
import Project.MessagingApp.entities.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserTest {

    @LocalServerPort
    private int port;

    private String baseUrl = "http://localhost";


    @Autowired
    private TestH2Repository h2Repository;

    private static RestTemplate restTemplate;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @BeforeAll
    public static void init() {

        restTemplate = new RestTemplate();
    }


    @BeforeEach
    public void setUp() {
        baseUrl = baseUrl.concat(":").concat(port+"").concat("/user");
    }


    @Test
    public void testAddUser(){
        User user = new User("Kishore",passwordEncoder.encode("Kishore123"),"USER");
        User response = restTemplate.postForObject(baseUrl+"/users",user,User.class);
        assertEquals("Kishore",response.getName());
        assertEquals(1,h2Repository.findAll().size());
    }

    @Test
    @Sql(statements = "INSERT INTO users (id,name, password, role) VALUES (1,'kishore','kishore123','USER')", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(statements = "DELETE FROM users WHERE name='kishore'", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void testGetUsers() {
        List<User> users = restTemplate.getForObject(baseUrl+"/users", List.class);
        assertEquals(1, users.size());
        assertEquals(1, h2Repository.findAll().size());
    }
}
