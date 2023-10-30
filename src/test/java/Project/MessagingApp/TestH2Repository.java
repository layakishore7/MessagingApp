package Project.MessagingApp;

import Project.MessagingApp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestH2Repository extends JpaRepository<User,Integer> {
}
