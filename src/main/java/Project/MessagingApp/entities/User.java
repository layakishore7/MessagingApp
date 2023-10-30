package Project.MessagingApp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;


    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<ConversationMember> conversationMembers;


    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Message> messages;

    public User(int i, String name, String password, String role) {
    }

    public User(String name, String password, String role) {
    }
}
