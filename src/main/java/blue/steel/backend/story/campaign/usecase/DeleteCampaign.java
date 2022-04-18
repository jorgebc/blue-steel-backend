package blue.steel.backend.story.campaign.usecase;

import blue.steel.backend.core.usecase.UseCase;
import blue.steel.backend.story.campaign.persistence.CampaignRepository;
import blue.steel.backend.story.campaign.usecase.dto.DeleteCampaignUseCaseInput;
import blue.steel.backend.story.campaign.usecase.dto.DeleteCampaignUseCaseOutput;
import java.util.UUID;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

/** Delete campaign use case. */
@Service
@Transactional
public class DeleteCampaign
    implements UseCase<DeleteCampaignUseCaseInput, DeleteCampaignUseCaseOutput> {

  private final CampaignRepository campaignRepository;

  public DeleteCampaign(CampaignRepository campaignRepository) {
    this.campaignRepository = campaignRepository;
  }

  /**
   * Deletes a campaign.
   *
   * @param input campaign id
   * @return the deleted campaign id
   */
  @Override
  public DeleteCampaignUseCaseOutput execute(DeleteCampaignUseCaseInput input) {
    UUID id = input.getCampaignId();
    campaignRepository.deleteById(id);
    return DeleteCampaignUseCaseOutput.builder().deletedCampaignId(id).build();
  }
}
