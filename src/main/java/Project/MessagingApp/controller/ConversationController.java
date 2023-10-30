package Project.MessagingApp.controller;

import Project.MessagingApp.entities.Conversation;
import Project.MessagingApp.exceptions.ConversationException;
import Project.MessagingApp.serviceClass.ConversationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/conversation")
public class ConversationController {
    @Autowired
    private ConversationService conversationService;


    //To get list of conversations
    @GetMapping("/conversations")
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    public List<Conversation> getAllConversations() {
        return conversationService.listallConversations();
    }

    //To start a new conversation
    @PostMapping("/conversations")
    //@PreAuthorize("hasAuthority('USER')")
    public Conversation addConversation(@RequestBody Conversation conversations) throws ConversationException {
        conversationService.newConversation(conversations);
        return conversations;
    }

    //Delete conversation by conversationId
    @DeleteMapping("/conversations/{conversationId}")
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    public void deleteByConversationId(@PathVariable int conversationId) throws ConversationException {
        conversationService.deleteById(conversationId);
    }

    //Get conversations by conversationId
    @GetMapping("/conversations/{conversationId}")
    @PreAuthorize("hasAuthority('USER')")
    public Optional<Conversation> getConversations(@PathVariable("conversationId") int conversationId) throws ConversationException {
        return conversationService.findById(conversationId);
    }


    //Update conversation name
    @PutMapping("conversations/{conversationId}")
    @PreAuthorize("hasAuthority('USER')")
    public Conversation updateById(@PathVariable("conversationId") int conversationId,@RequestParam String name) throws ConversationException {
        return conversationService.updateById(conversationId,name);

    }

}
