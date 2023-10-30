package Project.MessagingApp.serviceInterface;

import Project.MessagingApp.entities.ConversationMember;
import Project.MessagingApp.exceptions.ConversationException;
import Project.MessagingApp.exceptions.UserException;

import java.util.List;

public interface ConversationMemberInterface {

     List<ConversationMember> listAllConversationMembers();

     ConversationMember addMember(ConversationMember conversationMember);

     List<String> getUserById(int conversation_id) throws ConversationException;

     void removeUserById(int conversation_id, int user_id) throws ConversationException, UserException;
}
