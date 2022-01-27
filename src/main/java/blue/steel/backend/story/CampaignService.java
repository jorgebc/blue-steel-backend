package blue.steel.backend.story;

import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

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
