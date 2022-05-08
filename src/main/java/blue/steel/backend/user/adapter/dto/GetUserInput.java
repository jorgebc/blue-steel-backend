package blue.steel.backend.user.adapter.dto;

import javax.validation.constraints.NotNull;
import lombok.Value;

/** Get user input. */
@Value
public class GetUserInput {
  @NotNull String userId;
}
