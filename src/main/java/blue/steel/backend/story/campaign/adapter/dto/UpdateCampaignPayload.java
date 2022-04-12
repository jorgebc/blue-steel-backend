package blue.steel.backend.story.campaign.adapter.dto;

import blue.steel.backend.story.campaign.persistence.Campaign;
import lombok.Builder;
import lombok.Value;

/** Update campaign payload response. */
@Value
@Builder
public class UpdateCampaignPayload {
  Campaign campaign;
}
