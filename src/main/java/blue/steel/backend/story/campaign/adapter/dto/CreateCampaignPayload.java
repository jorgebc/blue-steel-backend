package blue.steel.backend.story.campaign.adapter.dto;

import blue.steel.backend.story.campaign.persistence.Campaign;
import lombok.Builder;
import lombok.Value;

/** Create campaign response. */
@Value
@Builder
public class CreateCampaignPayload {
  Campaign campaign;
}
