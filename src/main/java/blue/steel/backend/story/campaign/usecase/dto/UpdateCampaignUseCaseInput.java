package blue.steel.backend.story.campaign.usecase.dto;

import blue.steel.backend.core.entity.Versionable;
import blue.steel.backend.core.usecase.UseCaseInput;
import java.util.UUID;
import lombok.Value;

/** Update campaign use case input. */
@Value
public class UpdateCampaignUseCaseInput implements UseCaseInput, Versionable {
  UUID campaignId;
  String name;
  String description;
  String imageUrl;
  Integer version;
}
