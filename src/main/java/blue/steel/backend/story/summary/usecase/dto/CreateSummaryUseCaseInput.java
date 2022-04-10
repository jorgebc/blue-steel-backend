package blue.steel.backend.story.summary.usecase.dto;

import blue.steel.backend.core.usecase.UseCaseInput;
import blue.steel.backend.story.summary.persistence.Summary;
import java.time.LocalDate;
import java.util.UUID;
import lombok.Value;

/** Create campaign summary use case input. */
@Value
public class CreateSummaryUseCaseInput implements UseCaseInput {

  UUID campaignId;
  String name;
  String description;
  LocalDate gameDate;

  /**
   * Creates a campaign summary from input data.
   *
   * @return a new summary from input data
   */
  public Summary getSummary() {
    Summary summary = new Summary();
    summary.setName(getName());
    summary.setDescription(getDescription());
    summary.setGameDate(getGameDate());
    return summary;
  }
}
