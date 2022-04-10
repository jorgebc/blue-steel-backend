package blue.steel.backend.story.summary.adapter;

import blue.steel.backend.core.usecase.UseCase;
import blue.steel.backend.story.summary.adapter.dto.CreateCampaignSummaryInput;
import blue.steel.backend.story.summary.adapter.dto.CreateCampaignSummaryPayload;
import blue.steel.backend.story.summary.persistence.Summary;
import blue.steel.backend.story.summary.usecase.dto.CreateSummaryUseCaseInput;
import blue.steel.backend.story.summary.usecase.dto.CreateSummaryUseCaseOutput;
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

  /**
   * Creates a new campaign summary.
   *
   * @param input summary data
   * @return the created summary
   */
  @MutationMapping
  public CreateCampaignSummaryPayload createCampaignSummary(
      @Argument @Valid @NotNull CreateCampaignSummaryInput input) {

    CreateSummaryUseCaseInput createCampaignSummaryInput =
        input.getCreateCampaignSummaryUseCaseInput();

    CreateSummaryUseCaseOutput createdSummaryOutput =
        createCampaignSummary.execute(createCampaignSummaryInput);

    Summary createdSummary = createdSummaryOutput.getCreatedSummary();
    return new CreateCampaignSummaryPayload(createdSummary);
  }
}
