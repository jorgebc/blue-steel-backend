package blue.steel.backend.story.campaign.integration;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import blue.steel.backend.IntegrationTest;
import blue.steel.backend.story.campaign.adapter.dto.GetCampaignInput;
import blue.steel.backend.story.campaign.entity.Campaign;
import blue.steel.backend.story.campaign.entity.CampaignRepository;
import blue.steel.backend.story.campaign.entity.CampaignRepositoryTest;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.execution.ErrorType;

class GetCampaignTest extends IntegrationTest {

  public static final String GET_CAMPAIGN_QUERY = "story/campaign/queries/getCampaign";

  @Autowired private CampaignRepository campaignRepository;

  @Test
  @DisplayName("Fetching for an existing campaign should return a campaign")
  void getExistingCampaign() {
    // Given a campaign
    Campaign campaign = CampaignRepositoryTest.createCampaign();
    campaign = campaignRepository.save(campaign);
    UUID campaignId = campaign.getId();
    GetCampaignInput getCampaignInput = new GetCampaignInput(campaignId);

    // When fetching the campaign
    getGraphQlTesterWithAdminJwtToken(GET_CAMPAIGN_QUERY)
        .variable("input", getCampaignInput)
        .execute()
        .path("getCampaign.campaign")
        .entity(Campaign.class)

        // Then response should contain a campaign
        .satisfies(fetchedCampaign -> assertThat(fetchedCampaign.getId()).isEqualTo(campaignId));
  }

  @Test
  @DisplayName("Fetching for a non existing campaign should return not found error")
  void getNonExistingCampaign() {
    // Given no campaign
    UUID campaignId = UUID.randomUUID();
    GetCampaignInput getCampaignInput = new GetCampaignInput(campaignId);

    // When fetching for a campaign
    getGraphQlTesterWithAdminJwtToken(GET_CAMPAIGN_QUERY)
        .variable("input", getCampaignInput)
        .execute()
        .errors()

        // Then error response should contain a not found error
        .expect(graphQLError -> graphQLError.getErrorType().equals(ErrorType.NOT_FOUND))
        .verify();
  }
}
