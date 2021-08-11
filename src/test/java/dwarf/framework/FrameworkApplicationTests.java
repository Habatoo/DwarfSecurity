package dwarf.framework;

import dwarf.framework.model.User;
import dwarf.framework.repo.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class FrameworkApplicationTests {

	@Autowired
	private UserRepository userRepository;

	@AfterEach
	void tearDown() {
		userRepository.deleteAll();
	}

	@Test
	void itShouldCheckIfUserExists_Test() {
		String username = "habatoo";
		User user = new User(
				username
		);
		userRepository.save(user);

		// when
		boolean expected = userRepository.existsByUsername(username);
		//then
		assertThat(expected).isTrue();
	}

	@Test
	void itShouldCheckIfUserDoesNotExists_Test() {
		String username = "habatoo";

		// when
		boolean expected = userRepository.existsByUsername(username);
		//then
		assertThat(expected).isFalse();
	}

}
