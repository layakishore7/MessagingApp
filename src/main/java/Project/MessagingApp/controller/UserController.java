package Project.MessagingApp.controller;

import Project.MessagingApp.dto.AuthRequest;
import Project.MessagingApp.entities.User;
import Project.MessagingApp.exceptions.UserException;
import Project.MessagingApp.serviceClass.JwtService;
import Project.MessagingApp.serviceClass.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    //List of Users
    @GetMapping("/users")
    //@PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    public List<User> getUsers() {
        return userService.getallUsers();
    }

    //User can create account
    @PostMapping("/users")
    //@PreAuthorize("hasAuthority('USER')")
    public User addUser(@RequestBody User user) throws UserException {
        userService.newUser(user);
        return user;
    }

    //To Update Username
    @PutMapping("/usernames")
    @PreAuthorize("hasAuthority('USER')")
    public User updateUser(@RequestParam String name) throws UserException {
        return userService.updateUsername(name);
    }

    //To a Delete User
    @DeleteMapping("/users")
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    public void deleteUserid() throws UserException {
        userService.deleteByUserId();
    }


    //get user by userid
    @GetMapping("/user")
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    public User getUserById() throws UserException {
        return userService.getUserByUserId();
    }

    @PostMapping("/authenticate")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getName(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getName());
        } else {
            throw new UsernameNotFoundException("Invalid User");
        }
    }
}
