package blue.steel.backend.story.campaign.usecase;

import blue.steel.backend.story.campaign.entity.CampaignRepository;
import java.util.UUID;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

/** Delete campaign use case. */
@Service
@Transactional
public class DeleteCampaign {

  private final CampaignRepository campaignRepository;

  public DeleteCampaign(CampaignRepository campaignRepository) {
    this.campaignRepository = campaignRepository;
  }

  /**
   * Deletes a campaign.
   *
   * @param id campaign id
   * @return the deleted campaign id
   */
  public UUID delete(UUID id) {
    campaignRepository.deleteById(id);
    return id;
  }
}
