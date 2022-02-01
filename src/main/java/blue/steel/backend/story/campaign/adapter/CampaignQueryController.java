package blue.steel.backend.story.campaign.adapter;

import blue.steel.backend.story.campaign.entity.Campaign;
import blue.steel.backend.story.campaign.usecase.CampaignQuery;
import java.util.UUID;
import javax.validation.constraints.NotNull;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

/** Campaign graphql query controller. */
@Controller
public class CampaignQueryController {

  private final CampaignQuery campaignQuery;

  public CampaignQueryController(CampaignQuery campaignQuery) {
    this.campaignQuery = campaignQuery;
  }

  @QueryMapping
  public Campaign getCampaign(@Argument @NotNull UUID id) {
    return campaignQuery.findById(id);
  }

  @QueryMapping
  public Campaign getActualCampaign() {
    return campaignQuery.findActualCampaign();
  }
}
