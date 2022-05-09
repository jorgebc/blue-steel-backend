package blue.steel.backend.user.adapter;

import blue.steel.backend.core.usecase.UseCase;
import blue.steel.backend.user.adapter.dto.UpdateUserInput;
import blue.steel.backend.user.adapter.dto.UpdateUserPayload;
import blue.steel.backend.user.persistence.User;
import blue.steel.backend.user.usecase.dto.UpdateUserUseCaseInput;
import blue.steel.backend.user.usecase.dto.UpdateUserUseCaseOutput;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

/** User mutation controller. */
@Controller
@AllArgsConstructor
public class UserMutationController {

  private final UseCase<UpdateUserUseCaseInput, UpdateUserUseCaseOutput> updateUser;

  /**
   * Update user.
   *
   * @param input user data
   * @return the updated user
   */
  @MutationMapping
  public UpdateUserPayload updateUser(@Argument @Valid @NotNull UpdateUserInput input) {
    UpdateUserUseCaseInput updateUserInput = input.toUpdateUseCaseInput();
    UpdateUserUseCaseOutput updateUserOutput = updateUser.execute(updateUserInput);

    User user = updateUserOutput.getUser();
    return UpdateUserPayload.builder().user(user).build();
  }
}
