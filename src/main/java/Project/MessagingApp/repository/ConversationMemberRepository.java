package Project.MessagingApp.repository;

import Project.MessagingApp.entities.ConversationMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ConversationMemberRepository extends JpaRepository<ConversationMember, Integer> {


    @Query("SELECT c.user.name from ConversationMember c WHERE c.conversationId = :id")
    List<String> getUserNamesByConversationId(@Param("id") Integer id);


    @Query("Select c from ConversationMember c where conversationId=:conversationId and userId=:userId")
    ConversationMember findByConversationId(@Param("conversationId") int conversationId, @Param("userId") int userId);

}

