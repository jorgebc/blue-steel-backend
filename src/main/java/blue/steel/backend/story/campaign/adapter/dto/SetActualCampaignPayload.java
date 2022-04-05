package blue.steel.backend.story.campaign.adapter.dto;

import blue.steel.backend.story.campaign.persistence.Campaign;
import lombok.Value;

/** Set actual campaigns payload. */
@Value
public class SetActualCampaignPayload {
  Campaign campaign;
}
