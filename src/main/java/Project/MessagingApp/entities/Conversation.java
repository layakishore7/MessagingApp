package Project.MessagingApp.entities;

import Project.MessagingApp.enums.ConversationEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "conversations")
public class Conversation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "conversation_id")
    private int conversationId;

    @Column(name = "conversation_type")
    @Enumerated(EnumType.STRING)
    private ConversationEnum conversationType;

    @Column(name = "name")
    private String name;


    @OneToMany(mappedBy = "conversation")
    @JsonIgnore
    private List<ConversationMember> conversationMembers;


    @OneToMany(mappedBy = "conversation")
    @JsonIgnore
    private List<Message> messages;


}
