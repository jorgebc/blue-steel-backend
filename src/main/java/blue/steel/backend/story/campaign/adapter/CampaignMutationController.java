package blue.steel.backend.story.campaign.adapter;

import blue.steel.backend.core.usecase.UseCase;
import blue.steel.backend.story.campaign.adapter.dto.CreateCampaignInput;
import blue.steel.backend.story.campaign.adapter.dto.CreateCampaignPayload;
import blue.steel.backend.story.campaign.adapter.dto.DeleteCampaignInput;
import blue.steel.backend.story.campaign.adapter.dto.DeleteCampaignPayload;
import blue.steel.backend.story.campaign.adapter.dto.SetActualCampaignInput;
import blue.steel.backend.story.campaign.adapter.dto.SetActualCampaignPayload;
import blue.steel.backend.story.campaign.adapter.dto.UpdateCampaignInput;
import blue.steel.backend.story.campaign.adapter.dto.UpdateCampaignPayload;
import blue.steel.backend.story.campaign.entity.Campaign;
import blue.steel.backend.story.campaign.usecase.dto.CreateCampaignUseCaseInput;
import blue.steel.backend.story.campaign.usecase.dto.CreateCampaignUseCaseOutput;
import blue.steel.backend.story.campaign.usecase.dto.DeleteCampaignUseCaseInput;
import blue.steel.backend.story.campaign.usecase.dto.DeleteCampaignUseCaseOutput;
import blue.steel.backend.story.campaign.usecase.dto.SetActualCampaignUseCaseInput;
import blue.steel.backend.story.campaign.usecase.dto.SetActualCampaignUseCaseOutput;
import blue.steel.backend.story.campaign.usecase.dto.UpdateCampaignUseCaseInput;
import blue.steel.backend.story.campaign.usecase.dto.UpdateCampaignUseCaseOutput;
import java.util.UUID;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

/** Campaign mutation controller. */
@Controller
public class CampaignMutationController {

  private final UseCase<CreateCampaignUseCaseInput, CreateCampaignUseCaseOutput> createCampaign;
  private final UseCase<UpdateCampaignUseCaseInput, UpdateCampaignUseCaseOutput> updateCampaign;
  private final UseCase<DeleteCampaignUseCaseInput, DeleteCampaignUseCaseOutput> deleteCampaign;
  private final UseCase<SetActualCampaignUseCaseInput, SetActualCampaignUseCaseOutput>
      setActualCampaign;

  /**
   * Constructor.
   *
   * @param createCampaign create campaign use case
   * @param updateCampaign update campaign use case
   * @param deleteCampaign delete campaign use case
   * @param setActualCampaign set actual campaign use case
   */
  public CampaignMutationController(
      UseCase<CreateCampaignUseCaseInput, CreateCampaignUseCaseOutput> createCampaign,
      UseCase<UpdateCampaignUseCaseInput, UpdateCampaignUseCaseOutput> updateCampaign,
      UseCase<DeleteCampaignUseCaseInput, DeleteCampaignUseCaseOutput> deleteCampaign,
      UseCase<SetActualCampaignUseCaseInput, SetActualCampaignUseCaseOutput> setActualCampaign) {
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
    CreateCampaignUseCaseInput createCampaignInput = input.getCreateCampaignUseCaseInput();
    CreateCampaignUseCaseOutput createCampaignOutput = createCampaign.execute(createCampaignInput);
    Campaign createdCampaign = createCampaignOutput.getCreatedCampaign();
    return new CreateCampaignPayload(createdCampaign);
  }

  /**
   * Updates an existing campaign.
   *
   * @param input campaign data
   * @return the updated campaign
   */
  @MutationMapping
  public UpdateCampaignPayload updateCampaign(@Argument @Valid @NotNull UpdateCampaignInput input) {
    UpdateCampaignUseCaseInput updateCampaignInput = input.getUpdateCampaignUseCaseInput();
    UpdateCampaignUseCaseOutput updateCampaignOutput = updateCampaign.execute(updateCampaignInput);
    Campaign updatedCampaign = updateCampaignOutput.getUpdatedCampaign();
    return new UpdateCampaignPayload(updatedCampaign);
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
    DeleteCampaignUseCaseInput deleteCampaignInput = new DeleteCampaignUseCaseInput(campaignId);
    DeleteCampaignUseCaseOutput deletedCampaignOutput = deleteCampaign.execute(deleteCampaignInput);
    UUID deletedCampaignId = deletedCampaignOutput.getDeletedCampaignId();
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
    SetActualCampaignUseCaseInput setActualCampaignInput =
        new SetActualCampaignUseCaseInput(campaignId);
    SetActualCampaignUseCaseOutput setActualCampaignOutput =
        setActualCampaign.execute(setActualCampaignInput);
    Campaign actualCampaign = setActualCampaignOutput.getActualCampaign();
    return new SetActualCampaignPayload(actualCampaign);
  }
}
