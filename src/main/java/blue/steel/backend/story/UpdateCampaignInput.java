package blue.steel.backend.story;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.UUID;
import javax.validation.constraints.NotNull;
import lombok.Value;

/** Update campaign input. */
@Value
public class UpdateCampaignInput {
  @NotNull UUID id;

  @NotNull String name;

  @NotNull String description;

  @NotNull String imageUrl;

  /**
   * Maps the data to a new campaign.
   *
   * @return new campaign with input data
   */
  @JsonIgnore
  public Campaign getCampaign() {
    Campaign campaign = new Campaign();
    campaign.setName(getName());
    campaign.setDescription(getDescription());
    campaign.setImageUrl(getImageUrl());
    return campaign;
  }
}
