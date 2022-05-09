package blue.steel.backend.user.integration;

import static org.assertj.core.api.Assertions.assertThat;

import blue.steel.backend.IntegrationTest;
import blue.steel.backend.user.persistence.User;
import blue.steel.backend.user.persistence.UserRepository;
import blue.steel.backend.user.persistence.UserRepositoryTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.execution.ErrorType;

class GetUserTest extends IntegrationTest {

  private static final String GET_USER_QUERY = "user/queries/getUser";

  @Autowired private UserRepository userRepository;

  @Test
  @DisplayName("Fetching for an existing user should return the user")
  void getUser() {
    // Given a user
    User user = UserRepositoryTest.createAdminUser();
    userRepository.save(user);

    // When fetching the user
    getGraphQlTesterWithAdminJwtToken(GET_USER_QUERY)
        .execute()
        .path("getUser.user")
        .entity(User.class)

        // Then response should contain the user
        .satisfies(fetchedUser -> assertThat(fetchedUser.getName()).isEqualTo(ADMIN_USER_NAME));
  }

  @Test
  @DisplayName("Fetching for a non existing user should return not found error")
  void getNonExistingCampaign() {
    // Given no user

    // When fetching for a campaign
    getGraphQlTesterWithAdminJwtToken(GET_USER_QUERY)
        .execute()
        .errors()

        // Then error response should contain a not found error
        .expect(graphQLError -> graphQLError.getErrorType().equals(ErrorType.NOT_FOUND))
        .verify();
  }
}
