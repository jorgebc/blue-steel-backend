package blue.steel.backend.story.summary.adapter.dto;

import blue.steel.backend.story.summary.usecase.dto.CreateSummaryUseCaseInput;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDate;
import java.util.UUID;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Value;

/** Create campaign summary input. */
@Value
public class CreateCampaignSummaryInput {

  @NotNull UUID campaignId;
  @NotEmpty String name;
  @NotEmpty String description;
  @NotNull LocalDate gameDate;

  /**
   * Maps the data to a new creation campaign summary use case input.
   *
   * @return create campaign summary use case data
   */
  @JsonIgnore
  public CreateSummaryUseCaseInput getCreateCampaignSummaryUseCaseInput() {
    return new CreateSummaryUseCaseInput(campaignId, name, description, gameDate);
  }
}
