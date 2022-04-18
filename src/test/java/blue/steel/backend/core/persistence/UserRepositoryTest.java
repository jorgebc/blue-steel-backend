package blue.steel.backend.core.persistence;

import blue.steel.backend.IntegrationTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/** User repository test. */
class UserRepositoryTest extends IntegrationTest {

  @Autowired private UserRepository userRepository;

  @Test
  @DisplayName("Create a user")
  void createUser() {
    // Given a user
    User user = new User();
    user.setId("id2");
    user.setName("test2");

    // When creating a user
    user = userRepository.save(user);

    // Then the user id should not be null
    Assertions.assertThat(user.getId()).isNotNull();
  }
}
