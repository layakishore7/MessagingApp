package Project.MessagingApp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "messages")
@Builder
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    private int messageId;


    @Column(name = "conversation_id")
    private int conversationId;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "message")
    private String message;

    @Column(name = "sent_at")
    private LocalDateTime sentAt;

    @Column(name = "photo_url")
    private String photoUrl;

    @Column(name = "video_url")
    private String videoUrl;


    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", updatable = false, insertable = false)
    @JsonIgnore
    private User user;

    @ManyToOne
    @JoinColumn(name = "conversation_id", referencedColumnName = "conversation_id", updatable = false, insertable = false)
    @JsonIgnore
    private Conversation conversation;

}
