package blue.steel.backend.user.adapter.dto;

import blue.steel.backend.user.persistence.User;
import lombok.Value;

/** Get user payload. */
@Value
public class GetUserPayload {
  User user;
}
