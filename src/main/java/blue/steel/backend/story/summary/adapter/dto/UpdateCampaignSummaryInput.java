package blue.steel.backend.story.summary.adapter.dto;

import blue.steel.backend.story.summary.usecase.dto.UpdateSummaryUseCaseInput;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDate;
import java.util.UUID;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

/** Update campaign summary input. */
@Data
@Builder
public class UpdateCampaignSummaryInput {
  @NotNull UUID campaignId;
  @NotNull UUID id;
  @NotEmpty String name;
  @NotEmpty String description;
  @NotNull LocalDate gameDate;
  @NotNull Integer version;

  /**
   * Convert to use case input.
   *
   * @return the use case input
   */
  @JsonIgnore
  public UpdateSummaryUseCaseInput toUpdateSummaryUseCaseInput() {
    return UpdateSummaryUseCaseInput.builder()
        .campaignId(campaignId)
        .id(id)
        .name(name)
        .description(description)
        .gameDate(gameDate)
        .version(version)
        .build();
  }
}
