package Project.MessagingApp.serviceClass;

import Project.MessagingApp.entities.Conversation;
import Project.MessagingApp.entities.Message;
import Project.MessagingApp.entities.User;
import Project.MessagingApp.exceptions.MessageException;
import Project.MessagingApp.exceptions.UserException;
import Project.MessagingApp.repository.ConversationRepository;
import Project.MessagingApp.repository.MessageRepository;
import Project.MessagingApp.repository.UserRepository;
import Project.MessagingApp.serviceInterface.MessageInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MessageService implements MessageInterface {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    ConversationRepository conversationRepository;

    @Autowired
    UserRepository userRepository;


    private final String FOLDER_PATH = "C:/Users/Developer/Desktop/sendphotos/";


    @Override
    public List<Message> getAllMessages() {

        return messageRepository.findAll();
    }

    @Override
    public Message sendMessage(Message messages) throws UserException {
        Optional<User> user = userRepository.findById(messages.getUserId());
        Optional<Conversation> conversation = conversationRepository.findById(messages.getConversationId());
        if (user.isPresent() && conversation.isPresent()) {
            messages.setSentAt(LocalDateTime.now());
            messageRepository.save(messages);
            return messages;
        } else {
            throw new UserException("Invalid UserId or ConversationId");
        }
    }


    @Override
    public List<Message> listMessages(int conversationId) throws MessageException {
        List<Message> messages = messageRepository.findByConversationId(conversationId);
        if (messages.isEmpty()) {
            throw new MessageException("No Messages Found");
        } else {
            return messages;
        }
    }

    @Override
    public Message updateMessage(int messageId, String name) throws MessageException {
        Optional<Message> optionalMessage = messageRepository.findById(messageId);
        if (optionalMessage.isPresent()) {
            Message message = optionalMessage.get();
            message.setMessage(name);
            return messageRepository.save(message);
        } else {
            throw new MessageException("messageId not found");
        }
    }

    @Override
    public void deleteMessage(int messageId) throws MessageException {
        Optional<Message> optionalMessage = messageRepository.findById(messageId);
        if (optionalMessage.isPresent()) {
            messageRepository.deleteById(messageId);
        } else {
            throw new MessageException("messageId not found");
        }
    }


    @Override
    public Message sendImage(int conversationId, int userId, String message, MultipartFile photoUrl) throws UserException, IOException {
        Optional<User> user = userRepository.findById(userId);
        Optional<Conversation> conversation = conversationRepository.findById(conversationId);
        if (user.isPresent() && conversation.isPresent()) {
            String filepath = FOLDER_PATH + photoUrl.getOriginalFilename();
            Message messageObj = new Message();
            messageObj.setConversationId(conversationId);
            messageObj.setUserId(userId);
            messageObj.setMessage(message);
            messageObj.setSentAt(LocalDateTime.now());
            messageObj.setPhotoUrl(filepath);
            photoUrl.transferTo(new File(filepath));
            return messageRepository.save(messageObj);
        } else {
                throw new UserException("Invalid UserId or ConversationId");
            }
    }
}