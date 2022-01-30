package blue.steel.backend.story.campaign.usecase;

import blue.steel.backend.story.campaign.entity.Campaign;
import blue.steel.backend.story.campaign.entity.CampaignRepository;
import javax.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

/** Campaign query service. */
@Service
public class CampaignQueryService {

  private final CampaignRepository campaignRepository;

  public CampaignQueryService(CampaignRepository campaignRepository) {
    this.campaignRepository = campaignRepository;
  }

  public Campaign findActualCampaign() {
    return campaignRepository.findByActual(true).orElseThrow(EntityNotFoundException::new);
  }
}
