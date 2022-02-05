package blue.steel.backend.story.campaign.usecase.dto;

import blue.steel.backend.core.usecase.UseCaseInput;
import java.util.UUID;
import lombok.Value;

/** Delete campaign use case input. */
@Value
public class DeleteCampaignUseCaseInput implements UseCaseInput {
  UUID campaignId;
}
