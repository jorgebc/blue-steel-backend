package blue.steel.backend.story;

import java.util.UUID;
import javax.persistence.EntityNotFoundException;
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

  /**
   * Creates a new campaign.
   *
   * @param campaign new campaign data
   * @return the created campaign
   */
  public Campaign create(Campaign campaign) {
    return campaignRepository.save(campaign);
  }

  /**
   * Updates a campaign.
   *
   * @param id campaign id
   * @param newValues updated campaign values
   * @return the updated campaign
   */
  public Campaign update(UUID id, Campaign newValues) {
    Campaign campaign = campaignRepository.findById(id).orElseThrow(EntityNotFoundException::new);

    campaign.setName(newValues.getName());
    campaign.setDescription(newValues.getDescription());
    campaign.setImageUrl(newValues.getImageUrl());

    return campaignRepository.save(campaign);
  }
}
