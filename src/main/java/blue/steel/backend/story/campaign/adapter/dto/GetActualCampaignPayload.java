package blue.steel.backend.story.campaign.adapter.dto;

import blue.steel.backend.story.campaign.entity.Campaign;
import lombok.Value;

/** Get actual campaign payload. */
@Value
public class GetActualCampaignPayload {
  Campaign campaign;
}
