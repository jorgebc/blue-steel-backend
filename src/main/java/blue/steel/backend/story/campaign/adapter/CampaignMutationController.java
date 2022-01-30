package blue.steel.backend.story.campaign.adapter;

import blue.steel.backend.story.campaign.adapter.dto.CreateCampaignInput;
import blue.steel.backend.story.campaign.adapter.dto.CreateCampaignPayload;
import blue.steel.backend.story.campaign.adapter.dto.UpdateCampaignInput;
import blue.steel.backend.story.campaign.adapter.dto.UpdateCampaignPayload;
import blue.steel.backend.story.campaign.entity.Campaign;
import blue.steel.backend.story.campaign.usecase.CreateCampaign;
import blue.steel.backend.story.campaign.usecase.UpdateCampaign;
import java.util.UUID;
import javax.validation.Valid;
import org.jetbrains.annotations.NotNull;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

/** Campaign mutation controller. */
@Controller
public class CampaignMutationController {

  private final CreateCampaign createCampaign;
  private final UpdateCampaign updateCampaign;

  public CampaignMutationController(CreateCampaign createCampaign, UpdateCampaign updateCampaign) {
    this.createCampaign = createCampaign;
    this.updateCampaign = updateCampaign;
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
    campaign = createCampaign.create(campaign);
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
    campaign = updateCampaign.update(id, campaign);
    return new UpdateCampaignPayload(campaign);
  }
}
