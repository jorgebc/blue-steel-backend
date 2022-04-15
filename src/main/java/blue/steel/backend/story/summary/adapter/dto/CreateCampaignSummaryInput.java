package blue.steel.backend.story.summary.adapter.dto;

import blue.steel.backend.story.summary.usecase.dto.CreateSummaryUseCaseInput;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDate;
import java.util.UUID;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

/** Create campaign summary input. */
@Data
@Builder
public class CreateCampaignSummaryInput {

  @NotNull UUID campaignId;
  @NotEmpty String name;
  @NotEmpty String description;
  @NotNull LocalDate gameDate;

  /**
   * Convert to use case input.
   *
   * @return use case input
   */
  @JsonIgnore
  public CreateSummaryUseCaseInput toCreateCampaignSummaryUseCaseInput() {
    return CreateSummaryUseCaseInput.builder()
        .campaignId(campaignId)
        .name(name)
        .description(description)
        .gameDate(gameDate)
        .build();
  }
}
