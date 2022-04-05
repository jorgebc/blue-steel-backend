package blue.steel.backend.story.campaign.adapter.dto;

import blue.steel.backend.story.campaign.persistence.Campaign;
import lombok.Value;

/** Create campaign response. */
@Value
public class CreateCampaignPayload {
  Campaign campaign;
}
