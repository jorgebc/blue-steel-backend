package blue.steel.backend.user.persistence;

import blue.steel.backend.IntegrationTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/** User repository test. */
public class UserRepositoryTest extends IntegrationTest {

  @Autowired private UserRepository userRepository;

  @Test
  @DisplayName("Create a user")
  void save() {
    // Given a user
    User user = new User();
    user.setId("id2");
    user.setName("test2");

    // When creating a user
    user = userRepository.save(user);

    // Then the user id should not be null
    Assertions.assertThat(user.getId()).isNotNull();
  }

  /**
   * Create admin user.
   *
   * @return the user
   */
  public static User createAdminUser() {
    User user = new User();
    user.setId(ADMIN_USER_ID);
    user.setName(ADMIN_USER_NAME);
    return user;
  }
}
