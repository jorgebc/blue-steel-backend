package blue.steel.backend.story.campaign.usecase.dto;

import blue.steel.backend.core.usecase.UseCaseOutput;
import blue.steel.backend.story.campaign.persistence.Campaign;
import lombok.Builder;
import lombok.Value;

/** Create campaign use case output. */
@Value
@Builder
public class CreateCampaignUseCaseOutput implements UseCaseOutput {
  Campaign createdCampaign;
}
