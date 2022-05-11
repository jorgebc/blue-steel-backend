package blue.steel.backend.story.campaign.adapter.dto;

import blue.steel.backend.story.campaign.persistence.Campaign;
import java.util.List;
import lombok.Value;

/** Get campaigns payload. */
@Value
public class GetCampaignsPayload {
  List<Campaign> campaigns;
}
