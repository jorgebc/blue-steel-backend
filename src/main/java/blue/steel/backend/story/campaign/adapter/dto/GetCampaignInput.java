package blue.steel.backend.story.campaign.adapter.dto;

import java.util.UUID;
import javax.validation.constraints.NotNull;
import lombok.Value;

/** Get campaign input */
@Value
public class GetCampaignInput {
  @NotNull UUID campaignId;
}
