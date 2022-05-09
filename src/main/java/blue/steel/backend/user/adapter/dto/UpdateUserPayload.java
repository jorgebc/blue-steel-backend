package blue.steel.backend.user.adapter.dto;

import blue.steel.backend.user.persistence.User;
import lombok.Builder;
import lombok.Value;

/** Update user response. */
@Value
@Builder
public class UpdateUserPayload {
  User user;
}
