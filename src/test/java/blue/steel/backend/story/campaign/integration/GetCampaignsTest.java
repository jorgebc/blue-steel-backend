package blue.steel.backend.story.campaign.integration;

import blue.steel.backend.UseCaseTest;
import blue.steel.backend.story.campaign.entity.Campaign;
import blue.steel.backend.story.campaign.entity.CampaignRepository;
import blue.steel.backend.story.campaign.entity.CampaignRepositoryTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class GetCampaignsTest extends UseCaseTest {

  public static final String GET_CAMPAIGNS_QUERY = "story/campaign/queries/getCampaigns";

  @Autowired private CampaignRepository campaignRepository;

  @Test
  @DisplayName("Fetching for all campaigns should return a list of campaigns")
  void getAllCampaignsShouldReturnOneCampaign() {
    // Given a campaign
    Campaign campaign = CampaignRepositoryTest.createCampaign();
    campaignRepository.save(campaign);

    // When fetching all campaigns
    getGraphQlTesterWithAdminJwtToken(GET_CAMPAIGNS_QUERY)
        .execute()
        .path("getCampaigns.campaigns[*]")
        .entityList(Campaign.class)

        // Then response should contain a list with one campaign
        .hasSize(1);
  }

  @Test
  @DisplayName("Fetching for all campaigns should return empty list")
  void getAllCampaignsShouldReturnEmptyList() {
    // Given no campaign

    // When fetching for a campaign
    getGraphQlTesterWithAdminJwtToken(GET_CAMPAIGNS_QUERY)
        .execute()
        .path("getCampaigns.campaigns[*]")
        .entityList(Campaign.class)

        // Then response should contain an empty list
        .hasSize(0);
  }
}
