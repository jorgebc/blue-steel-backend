package blue.steel.backend.user.adapter;

import blue.steel.backend.core.util.SecurityUtils;
import blue.steel.backend.user.adapter.dto.GetUserPayload;
import blue.steel.backend.user.persistence.User;
import blue.steel.backend.user.usecase.UserQuery;
import lombok.RequiredArgsConstructor;
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
   * @return the user
   */
  @QueryMapping
  public GetUserPayload getUser() {
    String userId = SecurityUtils.getUserId();
    User user = userQuery.findById(userId);
    return new GetUserPayload(user);
  }
}
