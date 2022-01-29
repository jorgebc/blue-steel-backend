package blue.steel.backend.story;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.validation.constraints.NotNull;
import lombok.Value;

/** Create campaign input. */
@Value
public class CreateCampaignInput {
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
