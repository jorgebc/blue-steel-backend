package blue.steel.backend.story.campaign.usecase;

import blue.steel.backend.core.usecase.UseCase;
import blue.steel.backend.story.campaign.persistence.Campaign;
import blue.steel.backend.story.campaign.persistence.CampaignRepository;
import blue.steel.backend.story.campaign.usecase.dto.CreateCampaignUseCaseInput;
import blue.steel.backend.story.campaign.usecase.dto.CreateCampaignUseCaseOutput;
import javax.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/** Create campaign use case. */
@Service
@Transactional
@AllArgsConstructor
public class CreateCampaign
    implements UseCase<CreateCampaignUseCaseInput, CreateCampaignUseCaseOutput> {

  private final CampaignRepository campaignRepository;

  /**
   * Creates a new campaign.
   *
   * @param input new campaign data
   * @return the created campaign
   */
  @Override
  public CreateCampaignUseCaseOutput execute(CreateCampaignUseCaseInput input) {
    Campaign campaign = input.toCampaign();
    campaign = campaignRepository.save(campaign);
    return CreateCampaignUseCaseOutput.builder().createdCampaign(campaign).build();
  }
}
