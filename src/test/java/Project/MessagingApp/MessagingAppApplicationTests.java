package Project.MessagingApp;

import Project.MessagingApp.entities.User;
import Project.MessagingApp.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.Assert.*;

@SpringBootTest
class MessagingAppApplicationTests {

	@Autowired
	UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

//	@Test
//	public void testCreateUser() {
//		User user = new User();
//		user.setName("Laya Kishore");
//		user.setPassword(passwordEncoder.encode("Laya123"));
//		user.setRole("USER");
//		userRepository.save(user);
//		Assertions.assertNotNull(user.getUserId());
//	}
//
//	@Test
//	public void deleteUser() {
//		User user = new User();
//		user.setName("Sai");
//		user.setPassword("Sai123");
//		user.setRole("USER");
//		userRepository.save(user);
//		userRepository.deleteById(user.getUserId());
//		User deletedUser = userRepository.findById(user.getUserId()).orElse(null);
//		assertNull(deletedUser);
//	}

}
