package blue.steel.backend.story.campaign.adapter.dto;

import blue.steel.backend.story.campaign.entity.Campaign;
import java.util.Collection;
import lombok.Value;

/** Get campaigns payload. */
@Value
public class GetCampaignsPayload {
  Collection<Campaign> campaigns;
}