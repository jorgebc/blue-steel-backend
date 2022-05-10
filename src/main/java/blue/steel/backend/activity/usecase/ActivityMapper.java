package blue.steel.backend.activity.usecase;

import blue.steel.backend.activity.usecase.dto.Activity;
import blue.steel.backend.activity.usecase.dto.ActivityType;
import blue.steel.backend.core.persistence.Auditable;
import blue.steel.backend.story.campaign.persistence.Campaign;
import blue.steel.backend.story.summary.persistence.Summary;
import java.util.List;
import java.util.stream.Stream;

/** Activity mapper. */
public class ActivityMapper {

  private ActivityMapper() {
    throw new IllegalStateException("Utility class");
  }

  /**
   * Returns a list of the last 10 modified auditable objects.
   *
   * @param elements the elements
   * @return the filtered list
   */
  @SafeVarargs
  public static List<Activity> getLastTenLastModified(
          int limit, List<? extends Auditable>... elements) {
    return Stream.of(elements)
            .flatMap(List::stream)
            .sorted(
                    (a, b) ->
                            b.getAuditingMetadata()
                                    .getLastModifiedDate()
                                    .compareTo(a.getAuditingMetadata().getLastModifiedDate()))
            .limit(limit)
            .map(ActivityMapper::map)
            .toList();
  }

  /**
   * Maps an auditable to an activity dto.
   *
   * @param auditable the auditable
   * @param <T> an auditable object
   * @return the activity dto
   */
  public static <T extends Auditable> Activity map(T auditable) {
    //TODO change to pattern matching instead of if/else

    if (auditable instanceof Campaign campaign) {
      return Activity.builder()
              .id(campaign.getId())
              .type(ActivityType.CAMPAIGN)
              .name(campaign.getName())
              .description(campaign.getDescription())
              .lastModifiedBy(campaign.getAuditingMetadata().getLastModifiedBy())
              .lastModifiedDate(campaign.getAuditingMetadata().getLastModifiedDate())
              .build();

    } else if (auditable instanceof Summary summary) {
      return Activity.builder()
              .id(summary.getId())
              .type(ActivityType.SUMMARY)
              .name(summary.getName())
              .description(summary.getDescription())
              .lastModifiedBy(summary.getAuditingMetadata().getLastModifiedBy())
              .lastModifiedDate(summary.getAuditingMetadata().getLastModifiedDate())
              .build();

    } else {
      throw new IllegalArgumentException("Cannot map to activity, unknown auditable type");
    }
  }
}
