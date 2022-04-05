package blue.steel.backend.story.campaign.usecase.dto;

import blue.steel.backend.core.usecase.UseCaseOutput;
import blue.steel.backend.story.campaign.persistence.Campaign;
import lombok.Value;

/** Update campaign use case output. */
@Value
public class UpdateCampaignUseCaseOutput implements UseCaseOutput {
  Campaign updatedCampaign;
}
