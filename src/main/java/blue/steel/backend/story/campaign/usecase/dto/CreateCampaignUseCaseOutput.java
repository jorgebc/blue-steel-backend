package blue.steel.backend.story.campaign.usecase.dto;

import blue.steel.backend.core.usecase.UseCaseOutput;
import blue.steel.backend.story.campaign.entity.Campaign;
import lombok.Value;

/** Create campaign use case output. */
@Value
public class CreateCampaignUseCaseOutput implements UseCaseOutput {
  Campaign createdCampaign;
}
