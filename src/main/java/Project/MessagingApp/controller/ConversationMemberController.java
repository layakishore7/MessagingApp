package Project.MessagingApp.controller;

import Project.MessagingApp.entities.ConversationMember;
import Project.MessagingApp.exceptions.ConversationException;
import Project.MessagingApp.exceptions.UserException;
import Project.MessagingApp.serviceClass.ConversationMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/conversation-member")
public class ConversationMemberController {

    @Autowired
    private ConversationMemberService conversationMemberService;


    //To get list of conversations
    @GetMapping("/conversation-members")
    @PreAuthorize("hasAuthority('USER')")
    public List<ConversationMember> getAllConversationMembers(){
        return conversationMemberService.listAllConversationMembers();
    }

    //To add a new member to the conversation
    @PostMapping("/conversation-members")
    //@PreAuthorize("hasAuthority('USER')")
    public ConversationMember addConversationMember(@RequestBody ConversationMember conversationMember) {
        conversationMemberService.addMember(conversationMember);
        return conversationMember;
    }


    //get Usernames by conversationId
    @GetMapping("/usernames/{conversationId}")
    @PreAuthorize("hasAuthority('USER')")
    public List<String> getUserNamesByConversationId(@PathVariable int conversationId) throws ConversationException {
        return conversationMemberService.getUserById(conversationId);
    }


    //To remove a user from the conversation
    @DeleteMapping("/{conversationId}/users/{userId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void removeUserFromConversation(@PathVariable int conversationId, @PathVariable int userId) throws ConversationException, UserException {
        conversationMemberService.removeUserById(conversationId,userId);
    }
}