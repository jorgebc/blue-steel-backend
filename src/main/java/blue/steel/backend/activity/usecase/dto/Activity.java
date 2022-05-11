package blue.steel.backend.activity.usecase.dto;

import blue.steel.backend.user.persistence.User;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Builder;
import lombok.Value;

/** Activity dto. */
@Value
@Builder
public class Activity {
  UUID id;
  ActivityType type;
  String name;
  String description;
  User lastModifiedBy;
  LocalDateTime lastModifiedDate;
}
