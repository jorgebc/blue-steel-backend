package blue.steel.backend.story.summary.usecase;

import blue.steel.backend.core.usecase.EntityUtils;
import blue.steel.backend.core.usecase.UseCase;
import blue.steel.backend.story.campaign.persistence.Campaign;
import blue.steel.backend.story.campaign.persistence.CampaignRepository;
import blue.steel.backend.story.summary.persistence.Summary;
import blue.steel.backend.story.summary.persistence.SummaryRepository;
import blue.steel.backend.story.summary.usecase.dto.UpdateSummaryUseCaseInput;
import blue.steel.backend.story.summary.usecase.dto.UpdateSummaryUseCaseOutput;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/** Update campaign summary use case. */
@Service
@Transactional
@AllArgsConstructor
public class UpdateCampaignSummary
    implements UseCase<UpdateSummaryUseCaseInput, UpdateSummaryUseCaseOutput> {

  private final SummaryRepository summaryRepository;
  private final CampaignRepository campaignRepository;

  @Override
  public UpdateSummaryUseCaseOutput execute(UpdateSummaryUseCaseInput input) {
    Campaign campaign =
        campaignRepository
            .findById(input.getCampaignId())
            .orElseThrow(EntityNotFoundException::new);

    Summary summary =
        summaryRepository.findById(input.getId()).orElseThrow(EntityNotFoundException::new);
    EntityUtils.copyVersionableEntityProperties(input, summary);
    summary.setCampaign(campaign);
    summary = summaryRepository.save(summary);

    return new UpdateSummaryUseCaseOutput(summary);
  }
}
