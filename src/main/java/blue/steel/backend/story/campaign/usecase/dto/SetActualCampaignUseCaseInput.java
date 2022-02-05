package blue.steel.backend.story.campaign.usecase.dto;

import blue.steel.backend.core.usecase.UseCaseInput;
import java.util.UUID;
import lombok.Value;

/** Set actual campaign use case input. */
@Value
public class SetActualCampaignUseCaseInput implements UseCaseInput {
  UUID campaignId;
}
