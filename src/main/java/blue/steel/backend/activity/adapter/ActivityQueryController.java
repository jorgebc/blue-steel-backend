package blue.steel.backend.activity.adapter;

import blue.steel.backend.activity.adapter.dto.GetLastActivitiesPayload;
import blue.steel.backend.activity.usecase.ActivityQuery;
import blue.steel.backend.activity.usecase.dto.Activity;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

/** Activity query graphql query controller. */
@Controller
@RequiredArgsConstructor
public class ActivityQueryController {

  private final ActivityQuery activityQuery;

  /**
   * Gets last activity.
   *
   * @return last activity records
   */
  @QueryMapping
  public GetLastActivitiesPayload getLastActivities() {
    List<Activity> lastActivities = activityQuery.getLastActivities();
    return GetLastActivitiesPayload.builder().lastActivities(lastActivities).build();
  }
}
