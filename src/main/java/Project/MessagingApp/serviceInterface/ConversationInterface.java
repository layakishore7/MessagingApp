package Project.MessagingApp.serviceInterface;

import Project.MessagingApp.entities.Conversation;
import Project.MessagingApp.exceptions.ConversationException;

import java.util.List;
import java.util.Optional;

public interface ConversationInterface {

    List<Conversation> listallConversations();

    Conversation newConversation(Conversation conversation) throws ConversationException;

    void deleteById(int conversationId) throws ConversationException;

    Optional<Conversation> findById(int conversationId) throws ConversationException;

    Conversation updateById(int conversationId, String name) throws ConversationException;
}
