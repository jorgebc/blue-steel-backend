package blue.steel.backend.story.campaign.adapter;

import blue.steel.backend.story.campaign.adapter.dto.CreateCampaignInput;
import blue.steel.backend.story.campaign.adapter.dto.CreateCampaignPayload;
import blue.steel.backend.story.campaign.adapter.dto.DeleteCampaignInput;
import blue.steel.backend.story.campaign.adapter.dto.DeleteCampaignPayload;
import blue.steel.backend.story.campaign.adapter.dto.SetActualCampaignInput;
import blue.steel.backend.story.campaign.adapter.dto.SetActualCampaignPayload;
import blue.steel.backend.story.campaign.adapter.dto.UpdateCampaignInput;
import blue.steel.backend.story.campaign.adapter.dto.UpdateCampaignPayload;
import blue.steel.backend.story.campaign.entity.Campaign;
import blue.steel.backend.story.campaign.usecase.CreateCampaign;
import blue.steel.backend.story.campaign.usecase.DeleteCampaign;
import blue.steel.backend.story.campaign.usecase.SetActualCampaign;
import blue.steel.backend.story.campaign.usecase.UpdateCampaign;
import java.util.UUID;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

/** Campaign mutation controller. */
@Controller
public class CampaignMutationController {

  private final CreateCampaign createCampaign;
  private final UpdateCampaign updateCampaign;
  private final DeleteCampaign deleteCampaign;
  private final SetActualCampaign setActualCampaign;

  /**
   * Constructor.
   *
   * @param createCampaign create campaign use case
   * @param updateCampaign update campaign use case
   * @param deleteCampaign delete campaign use case
   * @param setActualCampaign set actual campaign use case
   */
  public CampaignMutationController(
      CreateCampaign createCampaign,
      UpdateCampaign updateCampaign,
      DeleteCampaign deleteCampaign,
      SetActualCampaign setActualCampaign) {
    this.createCampaign = createCampaign;
    this.updateCampaign = updateCampaign;
    this.deleteCampaign = deleteCampaign;
    this.setActualCampaign = setActualCampaign;
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

  /**
   * Deletes an existing campaign.
   *
   * @param input campaign id
   * @return the deleted campaign id
   */
  @MutationMapping
  public DeleteCampaignPayload deleteCampaign(@Argument @Valid @NotNull DeleteCampaignInput input) {
    UUID campaignId = input.getCampaignId();
    UUID deletedCampaignId = deleteCampaign.delete(campaignId);
    return new DeleteCampaignPayload(deletedCampaignId);
  }

  /**
   * Set a campaign to be the actual.
   *
   * @param input campaign id
   * @return the actual campaign
   */
  @MutationMapping
  public SetActualCampaignPayload setActualCampaign(
      @Argument @Valid @NotNull SetActualCampaignInput input) {
    UUID campaignId = input.getCampaignId();
    Campaign actualCampaign = setActualCampaign.setActual(campaignId);
    return new SetActualCampaignPayload(actualCampaign);
  }
}
