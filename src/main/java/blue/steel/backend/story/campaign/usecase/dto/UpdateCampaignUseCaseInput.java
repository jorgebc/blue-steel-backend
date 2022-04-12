package blue.steel.backend.story.campaign.usecase.dto;

import blue.steel.backend.core.persistence.Versionable;
import blue.steel.backend.core.usecase.UseCaseInput;
import java.util.UUID;
import lombok.Builder;
import lombok.Value;

/** Update campaign use case input. */
@Value
@Builder
public class UpdateCampaignUseCaseInput implements UseCaseInput, Versionable {
  UUID campaignId;
  String name;
  String description;
  String imageUrl;
  Integer version;
}
