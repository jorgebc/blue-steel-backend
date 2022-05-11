package blue.steel.backend.user.usecase.dto;

import blue.steel.backend.core.usecase.UseCaseInput;
import blue.steel.backend.user.persistence.User;
import lombok.Builder;
import lombok.Value;

/** Create user use case input. */
@Value
@Builder
public class CreateUserInput extends User implements UseCaseInput {
  String id;
  String name;

  /** Create user from input data. */
  public User toUser() {
    User user = new User();
    user.setId(id);
    user.setName(name);
    return user;
  }
}
