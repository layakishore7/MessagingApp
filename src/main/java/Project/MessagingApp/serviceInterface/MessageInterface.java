package Project.MessagingApp.serviceInterface;

import Project.MessagingApp.entities.Message;
import Project.MessagingApp.exceptions.ConversationException;
import Project.MessagingApp.exceptions.MessageException;
import Project.MessagingApp.exceptions.UserException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public interface MessageInterface {


     List<Message> getAllMessages();
     Message sendMessage(Message messages) throws UserException;

     //Message sendMessage(MultipartFile file, Message messages) throws UserException;

     List<Message> listMessages(int conversationId) throws MessageException;


     Message updateMessage(int messageId, String name) throws MessageException;

     Message sendImage(int conversationId, int userId, String message, MultipartFile photoUrl) throws IOException, UserException;

    // public String saveImage(MultipartFile file) throws IOException;


     void deleteMessage(int messageId) throws MessageException;




}
