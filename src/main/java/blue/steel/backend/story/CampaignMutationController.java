package blue.steel.backend.story;

import java.util.UUID;
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
   * @param input campaign data
   * @return the created campaign
   */
  @MutationMapping
  public CreateCampaignPayload createCampaign(@Argument @Valid @NotNull CreateCampaignInput input) {
    Campaign campaign = input.getCampaign();
    campaign = campaignMutationService.create(campaign);
    return new CreateCampaignPayload(campaign);
  }

  /**
   * Updates an existing campaign.
   *
   * @param input campaign data
   * @return the updated campaign
   */
  @MutationMapping
  public UpdateCampaignPayload updateCampaign(@Argument @Valid @NotNull UpdateCampaignInput input) {
    Campaign campaign = input.getCampaign();
    UUID id = input.getId();
    campaign = campaignMutationService.update(id, campaign);
    return new UpdateCampaignPayload(campaign);
  }
}
