package blue.steel.backend.story.campaign.usecase.dto;

import blue.steel.backend.core.usecase.UseCaseOutput;
import java.util.UUID;
import lombok.Value;

/** Delete campaign use case output. */
@Value
public class DeleteCampaignUseCaseOutput implements UseCaseOutput {
  UUID deletedCampaignId;
}
