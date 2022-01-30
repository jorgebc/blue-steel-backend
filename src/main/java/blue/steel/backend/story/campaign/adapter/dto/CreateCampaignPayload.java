package blue.steel.backend.story.campaign.adapter.dto;

import blue.steel.backend.story.campaign.entity.Campaign;
import lombok.Value;

/** Create campaign payload response. */
@Value
public class CreateCampaignPayload {
  Campaign campaign;
}
