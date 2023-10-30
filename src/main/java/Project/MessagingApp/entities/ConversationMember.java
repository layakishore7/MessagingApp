package Project.MessagingApp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "conversation_members")
public class ConversationMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "conversation_member_id")
    private int conversationMemberId;


    @Column(name = "conversation_id")
    private int conversationId;

    @Column(name = "user_id")
    private int userId;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    @JsonIgnore
    private User user;


    @ManyToOne
    @JoinColumn(name = "conversation_id", referencedColumnName = "conversation_id", insertable = false, updatable = false)
    @JsonIgnore
    private Conversation conversation;

}
