package blue.steel.backend.story.campaign.adapter.dto;

import blue.steel.backend.story.campaign.usecase.dto.DeleteCampaignUseCaseInput;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.UUID;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;

/** Delete campaign input. */
@Value
@Builder
public class DeleteCampaignInput {
  @NotNull UUID campaignId;

  /**
   * Convert to use case input.
   *
   * @return the use case input
   */
  @JsonIgnore
  public DeleteCampaignUseCaseInput toDeleteSummaryUseCaseInput() {
    return DeleteCampaignUseCaseInput.builder().campaignId(campaignId).build();
  }
}
