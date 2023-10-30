package Project.MessagingApp.repository;

import Project.MessagingApp.entities.ConversationMember;
import Project.MessagingApp.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message,Integer> {

    @Query("Select m from Message m where conversationId=:conversationId and userId=:userId")
    Message findByConversationIdAndUserId(@Param("conversationId") int conversationId, @Param("userId") int userId);


    List<Message> findByConversationId(int conversationId);

}
