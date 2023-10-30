package Project.MessagingApp.serviceClass;

import Project.MessagingApp.entities.Conversation;
import Project.MessagingApp.entities.ConversationMember;
import Project.MessagingApp.entities.User;
import Project.MessagingApp.exceptions.ConversationException;
import Project.MessagingApp.exceptions.UserException;
import Project.MessagingApp.repository.ConversationMemberRepository;
import Project.MessagingApp.repository.ConversationRepository;
import Project.MessagingApp.repository.UserRepository;
import Project.MessagingApp.serviceInterface.ConversationMemberInterface;
import org.hibernate.query.sqm.sql.ConversionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConversationMemberService implements ConversationMemberInterface {

    @Autowired
    ConversationMemberRepository conversationMemberRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ConversationRepository conversationRepository;


    @Override
    public List<ConversationMember> listAllConversationMembers() {

        return conversationMemberRepository.findAll();
    }

    @Override
    public ConversationMember addMember(ConversationMember conversationMember) {
        conversationMemberRepository.save(conversationMember);
        return conversationMember;
    }

    @Override
    public List<String> getUserById(int conversationId) throws ConversationException {
        Optional<Conversation> conversation = conversationRepository.findById(conversationId);
        if (conversation.isPresent()) {
            return conversationMemberRepository.getUserNamesByConversationId(conversationId);
        } else {
            throw new ConversionException("Conversation Id not Found");
        }
    }

    @Override
    public void removeUserById(int conversationId, int userId) throws ConversationException, UserException {
        ConversationMember member = conversationMemberRepository.findByConversationId(conversationId, userId);
        Optional<Conversation> conversation = conversationRepository.findById(conversationId);
        if (conversation.isEmpty()) {
            throw new ConversationException("Conversation not found.");
        }
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new UserException("User not found.");
        }
        conversationMemberRepository.delete(member);
    }
}

