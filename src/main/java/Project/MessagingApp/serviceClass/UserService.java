package Project.MessagingApp.serviceClass;

import Project.MessagingApp.config.UserInfoUserDetails;
import Project.MessagingApp.entities.User;
import Project.MessagingApp.exceptions.UserException;
import Project.MessagingApp.repository.UserRepository;
import Project.MessagingApp.serviceInterface.UserInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserService implements UserInterface, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public  UserService(){

    }

    @Override
    public List<User> getallUsers() {

        return userRepository.findAll();
    }


    @Override
    public User newUser(User user) throws UserException {

        if(userRepository.existsByName(user.getName())) {
            throw new UserException("user already exists");
        } else {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        }
        return user;
    }

    @Override
    public User updateUsername(String name) throws UserException {
        Optional<User> user = userRepository.findById(getDetails());
        if (user.isPresent()) {
            User user1=user.get();
            user1.setName(name);
            userRepository.save(user1);
            return user1;
        } else {
            throw new UserException("Invalid UserId");
        }
    }

    @Override
    public void deleteByUserId() throws UserException {
        Optional<User> user = userRepository.findById(getDetails());
        if (user.isPresent()) {
            userRepository.deleteById(getDetails());
        } else {
            throw new UserException("Invalid UserId");
        }
    }

    @Override
    public User getUserByUserId() throws UserException {
        Optional<User> user = userRepository.findById(getDetails());
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new UserException("Invalid UserId");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByName(name);
        return optionalUser.map(UserInfoUserDetails::new)
                .orElseThrow(()-> new UsernameNotFoundException("Invalid user" +name));
    }


    public int getDetails(){
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByName(name).get().getUserId();
    }
}