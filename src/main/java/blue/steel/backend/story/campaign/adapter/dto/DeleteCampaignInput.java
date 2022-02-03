package blue.steel.backend.story.campaign.adapter.dto;

import java.util.UUID;
import javax.validation.constraints.NotNull;
import lombok.Value;

/** Delete campaign input */
@Value
public class DeleteCampaignInput {
  @NotNull UUID campaignId;
}
