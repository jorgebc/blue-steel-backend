package blue.steel.backend.story.campaign.usecase.dto;

import blue.steel.backend.core.usecase.UseCaseOutput;
import blue.steel.backend.story.campaign.persistence.Campaign;
import lombok.Value;

/** Set actual campaign use case output. */
@Value
public class SetActualCampaignUseCaseOutput implements UseCaseOutput {
  Campaign actualCampaign;
}
