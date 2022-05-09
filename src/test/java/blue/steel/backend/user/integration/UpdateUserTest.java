package blue.steel.backend.user.integration;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import blue.steel.backend.IntegrationTest;
import blue.steel.backend.user.adapter.dto.UpdateUserInput;
import blue.steel.backend.user.persistence.User;
import blue.steel.backend.user.persistence.UserRepository;
import blue.steel.backend.user.persistence.UserRepositoryTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.execution.ErrorType;

/** Update user use case tests. */
class UpdateUserTest extends IntegrationTest {

  private static final String UPDATE_USER_QUERY = "user/queries/updateUser";

  @Autowired private UserRepository userRepository;

  @Test
  @DisplayName("Updating a valid user should return a not null user")
  void updateValidUser() {
    // Given an existing user
    User user = UserRepositoryTest.createUser();
    user = userRepository.save(user);

    // And a valid update user input
    String id = user.getId();
    UpdateUserInput updateUserInput =
        UpdateUserInput.builder().userId(id).name("new name").imageUrl("new imageUrl").build();

    // When updating a campaign
    getGraphQlTesterWithAdminJwtToken(UPDATE_USER_QUERY)
        .variable("input", updateUserInput)
        .execute()
        .path("updateUser.user")
        .entity(User.class)

        // Then response should contain the user
        .satisfies(updatedUser -> assertThat(updatedUser).isNotNull());
  }

  @Test
  @DisplayName("Updating a non existing user should return a not found error")
  void updateNotFoundUser() {
    // Given no user

    // And a valid update campaign input
    String id = "userId";
    UpdateUserInput updateUserInput =
        UpdateUserInput.builder().userId(id).name("new name").imageUrl("new imageUrl").build();

    // When updating a campaign
    getGraphQlTesterWithAdminJwtToken(UPDATE_USER_QUERY)
        .variable("input", updateUserInput)
        .execute()
        .errors()

        // Then error response should contain a not found error
        .expect(graphQLError -> graphQLError.getErrorType().equals(ErrorType.NOT_FOUND))
        .verify();
  }
}
