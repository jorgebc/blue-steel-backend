package blue.steel.backend.story.summary.adapter;

import blue.steel.backend.core.usecase.UseCase;
import blue.steel.backend.story.summary.adapter.dto.CreateCampaignSummaryInput;
import blue.steel.backend.story.summary.adapter.dto.CreateCampaignSummaryPayload;
import blue.steel.backend.story.summary.adapter.dto.DeleteCampaignSummaryInput;
import blue.steel.backend.story.summary.adapter.dto.DeleteCampaignSummaryPayload;
import blue.steel.backend.story.summary.adapter.dto.UpdateCampaignSummaryInput;
import blue.steel.backend.story.summary.adapter.dto.UpdateCampaignSummaryPayload;
import blue.steel.backend.story.summary.persistence.Summary;
import blue.steel.backend.story.summary.usecase.dto.CreateSummaryUseCaseInput;
import blue.steel.backend.story.summary.usecase.dto.CreateSummaryUseCaseOutput;
import blue.steel.backend.story.summary.usecase.dto.DeleteSummaryUseCaseInput;
import blue.steel.backend.story.summary.usecase.dto.DeleteSummaryUseCaseOutput;
import blue.steel.backend.story.summary.usecase.dto.UpdateSummaryUseCaseInput;
import blue.steel.backend.story.summary.usecase.dto.UpdateSummaryUseCaseOutput;
import java.util.UUID;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

/** Campaign summary mutation controller. */
@Controller
@AllArgsConstructor
public class SummaryMutationController {

  private final UseCase<CreateSummaryUseCaseInput, CreateSummaryUseCaseOutput>
      createCampaignSummary;
  private final UseCase<UpdateSummaryUseCaseInput, UpdateSummaryUseCaseOutput>
      updateCampaignSummary;
  private final UseCase<DeleteSummaryUseCaseInput, DeleteSummaryUseCaseOutput>
      deleteCampaignSummary;

  /**
   * Creates a new campaign summary.
   *
   * @param input summary data
   * @return the created summary
   */
  @MutationMapping
  public CreateCampaignSummaryPayload createCampaignSummary(
      @Argument @Valid @NotNull CreateCampaignSummaryInput input) {

    CreateSummaryUseCaseInput useCaseInput = input.toCreateCampaignSummaryUseCaseInput();
    CreateSummaryUseCaseOutput useCaseOutput = createCampaignSummary.execute(useCaseInput);

    Summary createdSummary = useCaseOutput.getCreatedSummary();
    return CreateCampaignSummaryPayload.builder().summary(createdSummary).build();
  }

  /**
   * Updates a campaign summary.
   *
   * @param input summary data
   * @return the updated summary
   */
  @MutationMapping
  public UpdateCampaignSummaryPayload updateCampaignSummary(
      @Argument @Valid @NotNull UpdateCampaignSummaryInput input) {

    UpdateSummaryUseCaseInput useCaseInput = input.toUpdateSummaryUseCaseInput();
    UpdateSummaryUseCaseOutput useCaseOutput = updateCampaignSummary.execute(useCaseInput);

    Summary updatedSummary = useCaseOutput.getSummary();
    return UpdateCampaignSummaryPayload.builder().summary(updatedSummary).build();
  }

  /**
   * Deletes a campaign summary.
   *
   * @param input summary id to delete
   * @return the deleted summary id
   */
  @MutationMapping
  public DeleteCampaignSummaryPayload deleteCampaignSummary(
      @Argument @Valid @NotNull DeleteCampaignSummaryInput input) {

    DeleteSummaryUseCaseInput useCaseInput = input.toDeleteSummaryUseCaseInput();
    DeleteSummaryUseCaseOutput useCaseOutput = deleteCampaignSummary.execute(useCaseInput);

    UUID deletedSummaryId = useCaseOutput.getSummaryId();
    return DeleteCampaignSummaryPayload.builder().summaryId(deletedSummaryId).build();
  }
}
