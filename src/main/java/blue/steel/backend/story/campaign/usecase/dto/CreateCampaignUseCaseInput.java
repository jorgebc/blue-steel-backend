package blue.steel.backend.story.campaign.usecase.dto;

import blue.steel.backend.core.usecase.UseCaseInput;
import blue.steel.backend.story.campaign.persistence.Campaign;
import lombok.Value;

/** Create campaign use case input. */
@Value
public class CreateCampaignUseCaseInput implements UseCaseInput {
  String name;

  String description;

  String imageUrl;

  /**
   * Creates a campaign from input data.
   *
   * @return a new campaign from input data
   */
  public Campaign getCampaign() {
    Campaign campaign = new Campaign();
    campaign.setName(getName());
    campaign.setDescription(getDescription());
    campaign.setImageUrl(getImageUrl());
    return campaign;
  }
}
