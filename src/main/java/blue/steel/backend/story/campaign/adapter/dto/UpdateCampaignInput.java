package blue.steel.backend.story.campaign.adapter.dto;

import blue.steel.backend.story.campaign.persistence.Campaign;
import blue.steel.backend.story.campaign.usecase.dto.UpdateCampaignUseCaseInput;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.UUID;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Value;

/** Update campaign input. */
@Value
public class UpdateCampaignInput {
  @NotNull UUID id;
  @NotEmpty String name;
  @NotEmpty String description;
  @NotEmpty String imageUrl;
  @NotNull Integer version;

  /**
   * Maps the data to a new update campaign use case input.
   *
   * @return new campaign with input data
   */
  @JsonIgnore
  public UpdateCampaignUseCaseInput getUpdateCampaignUseCaseInput() {
    Campaign campaign = new Campaign();
    campaign.setName(getName());
    campaign.setDescription(getDescription());
    campaign.setImageUrl(getImageUrl());
    return new UpdateCampaignUseCaseInput(id, name, description, imageUrl, version);
  }
}
