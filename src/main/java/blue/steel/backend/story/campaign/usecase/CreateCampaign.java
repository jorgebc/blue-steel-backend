package blue.steel.backend.story.campaign.usecase;

import blue.steel.backend.story.campaign.entity.Campaign;
import blue.steel.backend.story.campaign.entity.CampaignRepository;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

/** Create campaign use case. */
@Service
@Transactional
public class CreateCampaign {

  private final CampaignRepository campaignRepository;

  public CreateCampaign(CampaignRepository campaignRepository) {
    this.campaignRepository = campaignRepository;
  }

  /**
   * Creates a new campaign.
   *
   * @param campaign new campaign data
   * @return the created campaign
   */
  public Campaign create(Campaign campaign) {
    return campaignRepository.save(campaign);
  }
}
