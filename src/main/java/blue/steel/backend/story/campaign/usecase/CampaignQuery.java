package blue.steel.backend.story.campaign.usecase;

import blue.steel.backend.story.campaign.entity.Campaign;
import blue.steel.backend.story.campaign.entity.CampaignRepository;
import java.util.UUID;
import javax.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

/** Campaign query service. */
@Service
public class CampaignQuery {

  private final CampaignRepository campaignRepository;

  public CampaignQuery(CampaignRepository campaignRepository) {
    this.campaignRepository = campaignRepository;
  }

  public Campaign findById(UUID id) {
    return campaignRepository.findById(id).orElseThrow(EntityNotFoundException::new);
  }

  public Campaign findActualCampaign() {
    return campaignRepository.findByActual(true).orElseThrow(EntityNotFoundException::new);
  }
}
