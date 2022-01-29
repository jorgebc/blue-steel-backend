package blue.steel.backend.story;

import javax.validation.Valid;
import org.jetbrains.annotations.NotNull;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

/** Campaign mutation controller. */
@Controller
public class CampaignMutationController {

  private final CampaignMutationService campaignMutationService;

  public CampaignMutationController(CampaignMutationService campaignMutationService) {
    this.campaignMutationService = campaignMutationService;
  }

  /**
   * Creates a new campaign.
   *
   * @param input Campaign data
   * @return Created campaign
   */
  @MutationMapping
  public CreateCampaignPayload createCampaign(@Argument @Valid @NotNull CreateCampaignInput input) {
    Campaign campaign = input.getCampaign();
    campaign = campaignMutationService.create(campaign);
    return new CreateCampaignPayload(campaign);
  }
}
