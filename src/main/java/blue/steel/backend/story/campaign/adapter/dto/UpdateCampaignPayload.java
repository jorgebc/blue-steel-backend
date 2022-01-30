package blue.steel.backend.story.campaign.adapter.dto;

import blue.steel.backend.story.campaign.entity.Campaign;
import lombok.Value;

/** Update campaign payload response. */
@Value
public class UpdateCampaignPayload {
  Campaign campaign;
}
