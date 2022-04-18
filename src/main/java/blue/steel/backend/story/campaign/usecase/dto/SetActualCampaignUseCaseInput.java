package blue.steel.backend.story.campaign.usecase.dto;

import blue.steel.backend.core.usecase.UseCaseInput;
import java.util.UUID;
import lombok.Builder;
import lombok.Value;

/** Set actual campaign use case input. */
@Value
@Builder
public class SetActualCampaignUseCaseInput implements UseCaseInput {
  UUID campaignId;
}
