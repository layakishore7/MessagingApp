package Project.MessagingApp.controller;

import Project.MessagingApp.entities.Message;
import Project.MessagingApp.exceptions.ConversationException;
import Project.MessagingApp.exceptions.MessageException;
import Project.MessagingApp.exceptions.UserException;
import Project.MessagingApp.serviceClass.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageService messageService;


    //To get list of messages
    @GetMapping("/messages")
    @PreAuthorize("hasAuthority('USER')")
    public List<Message> getAllMessages() {
        return messageService.getAllMessages();
    }

    //To send message
    @PostMapping("/messages")
    @PreAuthorize("hasAuthority('USER')")
    public Message newMessage(@RequestBody Message messages) throws UserException {
        return messageService.sendMessage(messages);
    }


    //To get messages by conversationId
    @GetMapping("/messages/{conversationId}")
    @PreAuthorize("hasAuthority('USER')")
    public List<Message> getMessages(@PathVariable("conversationId") int conversationId) throws MessageException {
        return messageService.listMessages(conversationId);
    }

    //update a message
    @PutMapping("/messages/{messageId}")
    @PreAuthorize("hasAuthority('USER')")
    public Message updateMessage(@PathVariable("messageId") int messageId, @RequestParam String name) throws MessageException {
        return messageService.updateMessage(messageId, name);
    }

    //To delete a message
    @DeleteMapping("/messages/{messageId}")
    @PreAuthorize("hasAuthority('USER')")
    public void deleteMessage(@PathVariable("messageId") int messageId) throws MessageException {
        messageService.deleteMessage(messageId);
    }

    //To send a image
    @PostMapping("/messages/photos")
    //@PreAuthorize("hasAuthority('USER')")
    public Message sendImage(
            @RequestParam("conversationId") int conversationId,
            @RequestParam("userId") int userId,
            @RequestParam("message") String message,
            @RequestParam("photoUrl") MultipartFile file) throws IOException, UserException {
        return messageService.sendImage(conversationId, userId, message, file);
    }
}
