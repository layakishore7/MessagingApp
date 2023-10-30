package Project.MessagingApp.serviceClass;

import Project.MessagingApp.entities.Conversation;
import Project.MessagingApp.exceptions.ConversationException;
import Project.MessagingApp.repository.ConversationRepository;
import Project.MessagingApp.serviceInterface.ConversationInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConversationService implements ConversationInterface {
    @Autowired
    private ConversationRepository conversationRepository;


    @Override
    public List<Conversation> listallConversations() {

        return conversationRepository.findAll();
    }

    @Override
    public Conversation newConversation(Conversation conversation) throws ConversationException {
        if (conversation.getConversationType() != null) {
            conversationRepository.save(conversation);
            return conversation;
        } else {
            throw new ConversationException("Conversation cannot be null");
        }
    }

    @Override
    public void deleteById(int conversationId) throws ConversationException {
        Optional<Conversation> conversation = conversationRepository.findById(conversationId);
        if (conversation.isPresent()) {
            conversationRepository.deleteById(conversationId);
        } else {
            throw new ConversationException("Conversation Id Not Found");
        }
    }

    @Override
    public Optional<Conversation> findById(int conversationId) throws ConversationException {
        Optional<Conversation> conversations = conversationRepository.findByConversationId(conversationId);
        if (conversations.isPresent()) {
            return conversations;
        } else {
            throw new ConversationException("Conversation Not Found");
        }
    }

    @Override
    public Conversation updateById(int conversationId, String name) throws ConversationException {
        Optional<Conversation> optionalConversation = conversationRepository.findById(conversationId);
        if (optionalConversation.isPresent()){
            Conversation conversation = optionalConversation.get();
            conversation.setName(name);
            return conversationRepository.save(conversation);
        } else {
            throw new ConversationException("ConversationId not found");
        }
    }
}
