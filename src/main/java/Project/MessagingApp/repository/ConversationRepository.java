package Project.MessagingApp.repository;

import Project.MessagingApp.entities.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ConversationRepository extends JpaRepository<Conversation,Integer> {
    Optional<Conversation> findByConversationId(int conversationId);
}
