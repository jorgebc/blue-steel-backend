package blue.steel.backend.story.campaign.adapter;

import blue.steel.backend.story.campaign.adapter.dto.GetActualCampaignPayload;
import blue.steel.backend.story.campaign.adapter.dto.GetCampaignInput;
import blue.steel.backend.story.campaign.adapter.dto.GetCampaignPayload;
import blue.steel.backend.story.campaign.adapter.dto.GetCampaignsPayload;
import blue.steel.backend.story.campaign.persistence.Campaign;
import blue.steel.backend.story.campaign.usecase.CampaignQuery;
import java.util.Collection;
import java.util.UUID;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

/** Campaign graphql query controller. */
@Controller
@RequiredArgsConstructor
public class CampaignQueryController {

  private final CampaignQuery campaignQuery;

  @QueryMapping
  public GetCampaignsPayload getCampaigns() {
    Collection<Campaign> campaigns = campaignQuery.findAll();
    return new GetCampaignsPayload(campaigns);
  }

  /**
   * Get an existing campaign.
   *
   * @param input campaign id
   * @return campaign found
   */
  @QueryMapping
  public GetCampaignPayload getCampaign(@Argument @Valid @NotNull GetCampaignInput input) {
    UUID id = input.getCampaignId();
    Campaign campaign = campaignQuery.findById(id);
    return new GetCampaignPayload(campaign);
  }

  @QueryMapping
  public GetActualCampaignPayload getActualCampaign() {
    Campaign campaign = campaignQuery.findActualCampaign();
    return new GetActualCampaignPayload(campaign);
  }
}
