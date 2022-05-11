package blue.steel.backend.activity.adapter.dto;

import blue.steel.backend.activity.usecase.dto.Activity;
import java.util.List;
import lombok.Builder;
import lombok.Value;

/** Get last activity payload. */
@Value
@Builder
public class GetLastActivitiesPayload {
  List<Activity> lastActivities;
}
