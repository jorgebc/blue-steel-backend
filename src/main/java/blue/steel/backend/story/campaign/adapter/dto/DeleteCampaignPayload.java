package blue.steel.backend.story.campaign.adapter.dto;

import java.util.UUID;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;

/** Delete campaign payload. */
@Value
@Builder
public class DeleteCampaignPayload {
  @NotNull UUID campaignId;
}
