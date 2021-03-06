package blue.steel.backend.story.campaign.adapter.dto;

import blue.steel.backend.story.campaign.persistence.Campaign;
import lombok.Value;

/** Get campaign payload. */
@Value
public class GetCampaignPayload {
  Campaign campaign;
}
