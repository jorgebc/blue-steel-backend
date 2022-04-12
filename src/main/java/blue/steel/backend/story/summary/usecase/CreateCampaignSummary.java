package blue.steel.backend.story.summary.usecase;

import blue.steel.backend.core.usecase.UseCase;
import blue.steel.backend.story.campaign.persistence.Campaign;
import blue.steel.backend.story.campaign.persistence.CampaignRepository;
import blue.steel.backend.story.summary.persistence.Summary;
import blue.steel.backend.story.summary.persistence.SummaryRepository;
import blue.steel.backend.story.summary.usecase.dto.CreateSummaryUseCaseInput;
import blue.steel.backend.story.summary.usecase.dto.CreateSummaryUseCaseOutput;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/** Create campaign summary use case. */
@Service
@Transactional
@AllArgsConstructor
public class CreateCampaignSummary
    implements UseCase<CreateSummaryUseCaseInput, CreateSummaryUseCaseOutput> {

  private final SummaryRepository summaryRepository;
  private final CampaignRepository campaignRepository;

  @Override
  public CreateSummaryUseCaseOutput execute(CreateSummaryUseCaseInput input) {

    Campaign campaign =
        campaignRepository
            .findById(input.getCampaignId())
            .orElseThrow(EntityNotFoundException::new);

    Summary summary = input.getSummary();
    summary.setCampaign(campaign);
    summary = summaryRepository.save(summary);

    return CreateSummaryUseCaseOutput.builder().createdSummary(summary).build();
  }
}
