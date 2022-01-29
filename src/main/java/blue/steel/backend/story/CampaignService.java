package blue.steel.backend.story;

import javax.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

/** Campaign service. */
@Service
public class CampaignService {

  private final CampaignRepository campaignRepository;

  public CampaignService(CampaignRepository campaignRepository) {
    this.campaignRepository = campaignRepository;
  }

  public Campaign findActualCampaign() {
    return campaignRepository.findByActual(true).orElseThrow(EntityNotFoundException::new);
  }
}
