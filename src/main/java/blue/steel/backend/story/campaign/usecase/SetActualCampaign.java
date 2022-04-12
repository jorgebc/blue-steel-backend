package blue.steel.backend.story.campaign.usecase;

import blue.steel.backend.core.usecase.UseCase;
import blue.steel.backend.story.campaign.persistence.Campaign;
import blue.steel.backend.story.campaign.persistence.CampaignRepository;
import blue.steel.backend.story.campaign.usecase.dto.SetActualCampaignUseCaseInput;
import blue.steel.backend.story.campaign.usecase.dto.SetActualCampaignUseCaseOutput;
import java.util.UUID;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

/** Set actual campaign use case. */
@Service
@Transactional
public class SetActualCampaign
    implements UseCase<SetActualCampaignUseCaseInput, SetActualCampaignUseCaseOutput> {

  private final CampaignRepository campaignRepository;

  public SetActualCampaign(CampaignRepository campaignRepository) {
    this.campaignRepository = campaignRepository;
  }

  /**
   * Set a campaign to be the actual, updates previous actual campaign if exists.
   *
   * @param input campaign id
   * @return the actual campaign
   */
  @Override
  public SetActualCampaignUseCaseOutput execute(SetActualCampaignUseCaseInput input) {

    UUID campaignId = input.getCampaignId();
    Campaign campaign =
        campaignRepository.findById(campaignId).orElseThrow(EntityNotFoundException::new);

    campaignRepository
        .findByActual(true)
        .ifPresent(
            actualCampaign -> {
              actualCampaign.setActual(false);
              campaignRepository.save(actualCampaign);
            });

    campaign.setActual(true);

    Campaign actualCampaign = campaignRepository.save(campaign);
    return SetActualCampaignUseCaseOutput.builder().actualCampaign(actualCampaign).build();
  }
}
