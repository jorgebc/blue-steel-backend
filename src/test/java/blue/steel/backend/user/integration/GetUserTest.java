package blue.steel.backend.user.integration;

import static org.assertj.core.api.Assertions.assertThat;

import blue.steel.backend.IntegrationTest;
import blue.steel.backend.user.adapter.dto.GetUserInput;
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
    User user = UserRepositoryTest.createUser();
    userRepository.save(user);

    // When fetching the user
    String userId = user.getId();
    GetUserInput getUserInput = new GetUserInput(userId);
    getGraphQlTesterWithAdminJwtToken(GET_USER_QUERY)
        .variable("input", getUserInput)
        .execute()
        .path("getUser.user")
        .entity(User.class)

        // Then response should contain the user
        .satisfies(fetchedUser -> assertThat(fetchedUser.getId()).isEqualTo(userId));
  }

  @Test
  @DisplayName("Fetching for a non existing user should return not found error")
  void getNonExistingCampaign() {
    // Given no user
    String userId = "userId";
    GetUserInput getUserInput = new GetUserInput(userId);

    // When fetching for a campaign
    getGraphQlTesterWithAdminJwtToken(GET_USER_QUERY)
        .variable("input", getUserInput)
        .execute()
        .errors()

        // Then error response should contain a not found error
        .expect(graphQLError -> graphQLError.getErrorType().equals(ErrorType.NOT_FOUND))
        .verify();
  }
}
