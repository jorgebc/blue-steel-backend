package blue.steel.backend.story.campaign.usecase;

import blue.steel.backend.core.usecase.EntityUtils;
import blue.steel.backend.core.usecase.UseCase;
import blue.steel.backend.story.campaign.entity.Campaign;
import blue.steel.backend.story.campaign.entity.CampaignRepository;
import blue.steel.backend.story.campaign.usecase.dto.UpdateCampaignUseCaseInput;
import blue.steel.backend.story.campaign.usecase.dto.UpdateCampaignUseCaseOutput;
import java.util.UUID;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

/** Update campaign use case. */
@Service
@Transactional
public class UpdateCampaign
    implements UseCase<UpdateCampaignUseCaseInput, UpdateCampaignUseCaseOutput> {

  private final CampaignRepository campaignRepository;

  public UpdateCampaign(CampaignRepository campaignRepository) {
    this.campaignRepository = campaignRepository;
  }

  /**
   * Updates a campaign.
   *
   * @param input campaign id and updated campaign values
   * @return the updated campaign
   */
  @Override
  public UpdateCampaignUseCaseOutput execute(UpdateCampaignUseCaseInput input) {

    UUID id = input.getCampaignId();
    Campaign campaign = campaignRepository.findById(id).orElseThrow(EntityNotFoundException::new);

    EntityUtils.copyVersionableEntityProperties(input, campaign);
    campaign = campaignRepository.save(campaign);

    return new UpdateCampaignUseCaseOutput(campaign);
  }
}
