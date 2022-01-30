package blue.steel.backend.story.campaign.usecase;

import blue.steel.backend.story.campaign.entity.Campaign;
import blue.steel.backend.story.campaign.entity.CampaignRepository;
import java.util.UUID;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

/** Update campaign use case. */
@Service
@Transactional
public class UpdateCampaign {

  private final CampaignRepository campaignRepository;

  public UpdateCampaign(CampaignRepository campaignRepository) {
    this.campaignRepository = campaignRepository;
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
