package blue.steel.backend.story.campaign.adapter.dto;

import java.util.UUID;
import javax.validation.constraints.NotNull;
import lombok.Value;

/** Set actual campaign input. */
@Value
public class SetActualCampaignInput {
  @NotNull UUID campaignId;
}
