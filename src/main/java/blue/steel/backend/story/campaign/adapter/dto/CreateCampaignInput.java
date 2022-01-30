package blue.steel.backend.story.campaign.adapter.dto;

import blue.steel.backend.story.campaign.entity.Campaign;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.validation.constraints.NotEmpty;
import lombok.Value;

/** Create campaign input. */
@Value
public class CreateCampaignInput {
  @NotEmpty String name;

  @NotEmpty String description;

  @NotEmpty String imageUrl;

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
