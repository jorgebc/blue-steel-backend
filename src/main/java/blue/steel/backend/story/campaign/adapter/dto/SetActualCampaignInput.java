package blue.steel.backend.story.campaign.adapter.dto;

import blue.steel.backend.story.campaign.usecase.dto.SetActualCampaignUseCaseInput;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.UUID;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;

/** Set actual campaign input. */
@Value
@Builder
public class SetActualCampaignInput {
  @NotNull UUID campaignId;

  /**
   * Convert to use case input.
   *
   * @return the use case input
   */
  @JsonIgnore
  public SetActualCampaignUseCaseInput toSetActualCampaignUseCaseInput() {
    return SetActualCampaignUseCaseInput.builder().campaignId(campaignId).build();
  }
}
