package Project.MessagingApp.serviceInterface;

import Project.MessagingApp.entities.User;
import Project.MessagingApp.exceptions.UserException;

import java.util.List;

public interface UserInterface {

      List<User> getallUsers();

      User newUser(User user) throws UserException;


      User updateUsername(String name) throws UserException;

      void  deleteByUserId() throws UserException;

      User getUserByUserId() throws UserException;
}
