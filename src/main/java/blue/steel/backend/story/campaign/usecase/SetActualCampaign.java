package blue.steel.backend.story.campaign.usecase;

import blue.steel.backend.story.campaign.entity.Campaign;
import blue.steel.backend.story.campaign.entity.CampaignRepository;
import java.util.UUID;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

/** Set actual campaign use case. */
@Service
@Transactional
public class SetActualCampaign {

  private final CampaignRepository campaignRepository;

  public SetActualCampaign(CampaignRepository campaignRepository) {
    this.campaignRepository = campaignRepository;
  }

  /**
   * Set a campaign to be the actual, updates previous actual campaign if exists.
   *
   * @param campaignId campaign id
   * @return the actual campaign
   */
  public Campaign setActual(UUID campaignId) {

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

    return campaignRepository.save(campaign);
  }
}
