package blue.steel.backend.story.campaign.adapter;

import blue.steel.backend.story.campaign.entity.Campaign;
import blue.steel.backend.story.campaign.usecase.CampaignQueryService;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

/** Campaign graphql query controller. */
@Controller
public class CampaignQueryController {

  private final CampaignQueryService campaignQueryService;

  public CampaignQueryController(CampaignQueryService campaignService) {
    this.campaignQueryService = campaignService;
  }

  @QueryMapping
  public Campaign actualCampaign() {
    return campaignQueryService.findActualCampaign();
  }
}
