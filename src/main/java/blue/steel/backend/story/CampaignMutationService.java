package blue.steel.backend.story;

import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

/** Campaign mutation service. */
@Service
@Transactional
public class CampaignMutationService {

  private final CampaignRepository campaignRepository;

  public CampaignMutationService(CampaignRepository campaignRepository) {
    this.campaignRepository = campaignRepository;
  }

  public Campaign create(Campaign campaign) {
    return campaignRepository.save(campaign);
  }
}
