package blue.steel.backend.story.summary.adapter.dto;

import blue.steel.backend.story.summary.usecase.dto.DeleteSummaryUseCaseInput;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.UUID;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

/** Delete campaign summary input. */
@Data
@Builder
public class DeleteCampaignSummaryInput {
  @NotNull UUID summaryId;

  /**
   * Convert to use case input.
   *
   * @return the use case input
   */
  @JsonIgnore
  public DeleteSummaryUseCaseInput toDeleteSummaryUseCaseInput() {
    return DeleteSummaryUseCaseInput.builder().summaryId(summaryId).build();
  }
}
