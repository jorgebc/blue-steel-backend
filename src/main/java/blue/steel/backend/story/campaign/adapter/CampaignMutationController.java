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
import blue.steel.backend.story.campaign.persistence.Campaign;
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
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

/** Campaign mutation controller. */
@Controller
@AllArgsConstructor
public class CampaignMutationController {

  private final UseCase<CreateCampaignUseCaseInput, CreateCampaignUseCaseOutput> createCampaign;
  private final UseCase<UpdateCampaignUseCaseInput, UpdateCampaignUseCaseOutput> updateCampaign;
  private final UseCase<DeleteCampaignUseCaseInput, DeleteCampaignUseCaseOutput> deleteCampaign;
  private final UseCase<SetActualCampaignUseCaseInput, SetActualCampaignUseCaseOutput>
      setActualCampaign;

  /**
   * Creates a new campaign.
   *
   * @param input campaign data
   * @return the created campaign
   */
  @MutationMapping
  public CreateCampaignPayload createCampaign(@Argument @Valid @NotNull CreateCampaignInput input) {

    CreateCampaignUseCaseInput createCampaignInput = input.toCreateCampaignUseCaseInput();
    CreateCampaignUseCaseOutput createCampaignOutput = createCampaign.execute(createCampaignInput);

    Campaign createdCampaign = createCampaignOutput.getCreatedCampaign();
    return CreateCampaignPayload.builder().campaign(createdCampaign).build();
  }

  /**
   * Update campaign.
   *
   * @param input campaign data
   * @return the updated campaign
   */
  @MutationMapping
  public UpdateCampaignPayload updateCampaign(@Argument @Valid @NotNull UpdateCampaignInput input) {

    UpdateCampaignUseCaseInput updateCampaignInput = input.toUpdateCampaignUseCaseInput();
    UpdateCampaignUseCaseOutput updateCampaignOutput = updateCampaign.execute(updateCampaignInput);

    Campaign updatedCampaign = updateCampaignOutput.getUpdatedCampaign();
    return UpdateCampaignPayload.builder().campaign(updatedCampaign).build();
  }

  /**
   * Deletes an existing campaign.
   *
   * @param input campaign id
   * @return the deleted campaign id
   */
  @MutationMapping
  public DeleteCampaignPayload deleteCampaign(@Argument @Valid @NotNull DeleteCampaignInput input) {

    DeleteCampaignUseCaseInput deleteCampaignInput = input.toDeleteSummaryUseCaseInput();
    DeleteCampaignUseCaseOutput deletedCampaignOutput = deleteCampaign.execute(deleteCampaignInput);

    UUID deletedCampaignId = deletedCampaignOutput.getDeletedCampaignId();
    return DeleteCampaignPayload.builder().campaignId(deletedCampaignId).build();
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

    SetActualCampaignUseCaseInput useCaseInput = input.toSetActualCampaignUseCaseInput();
    SetActualCampaignUseCaseOutput useCaseOutput = setActualCampaign.execute(useCaseInput);

    Campaign actualCampaign = useCaseOutput.getActualCampaign();
    return SetActualCampaignPayload.builder().campaign(actualCampaign).build();
  }
}
