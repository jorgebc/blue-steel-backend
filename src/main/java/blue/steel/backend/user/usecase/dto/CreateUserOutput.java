package blue.steel.backend.user.usecase.dto;

import blue.steel.backend.core.usecase.UseCaseOutput;
import blue.steel.backend.user.persistence.User;
import lombok.Builder;
import lombok.Value;

/** Create user use case output. */
@Value
@Builder
public class CreateUserOutput implements UseCaseOutput {
  User user;
}
