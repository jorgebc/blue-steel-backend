package blue.steel.backend.user.adapter;

import blue.steel.backend.user.adapter.dto.GetUserInput;
import blue.steel.backend.user.adapter.dto.GetUserPayload;
import blue.steel.backend.user.persistence.User;
import blue.steel.backend.user.usecase.UserQuery;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

/** User graphql query controller. */
@Controller
@RequiredArgsConstructor
public class UserQueryController {

  private final UserQuery userQuery;

  /**
   * Gets user.
   *
   * @param input use id
   * @return the user
   */
  @QueryMapping
  public GetUserPayload getUser(@Argument @Valid @NotNull GetUserInput input) {
    String userId = input.getUserId();
    User user = userQuery.findById(userId);
    return new GetUserPayload(user);
  }
}
