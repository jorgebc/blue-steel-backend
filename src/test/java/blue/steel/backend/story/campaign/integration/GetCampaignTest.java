package blue.steel.backend.story.campaign.integration;

import static org.assertj.core.api.Assertions.assertThat;

import blue.steel.backend.IntegrationTest;
import blue.steel.backend.story.campaign.adapter.dto.GetCampaignInput;
import blue.steel.backend.story.campaign.persistence.Campaign;
import blue.steel.backend.story.campaign.persistence.CampaignRepository;
import blue.steel.backend.story.campaign.persistence.CampaignRepositoryTest;
import blue.steel.backend.story.summary.persistence.Summary;
import blue.steel.backend.story.summary.persistence.SummaryRepository;
import blue.steel.backend.story.summary.persistence.SummaryRepositoryTest;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.execution.ErrorType;

class GetCampaignTest extends IntegrationTest {

  public static final String GET_CAMPAIGN_QUERY = "story/campaign/queries/getCampaign";

  @Autowired private CampaignRepository campaignRepository;
  @Autowired private SummaryRepository summaryRepository;

  @Test
  @DisplayName("Fetching for an existing campaign should return a campaign with summaries")
  void getExistingCampaignWithSummaries() {
    // Given a campaign with a summary
    Campaign campaign = CampaignRepositoryTest.createCampaign();
    campaign = campaignRepository.save(campaign);
    UUID campaignId = campaign.getId();
    Summary summary = SummaryRepositoryTest.createSummary(campaign);
    summaryRepository.save(summary);

    // When fetching the campaign
    GetCampaignInput getCampaignInput = new GetCampaignInput(campaignId);
    getGraphQlTesterWithAdminJwtToken(GET_CAMPAIGN_QUERY)
        .variable("input", getCampaignInput)
        .execute()
        .path("getCampaign.campaign")
        .entity(Campaign.class)

        // Then response should contain a campaign
        .satisfies(fetchedCampaign -> assertThat(fetchedCampaign.getId()).isEqualTo(campaignId))
        .satisfies(
            fetchedCampaign -> assertThat(fetchedCampaign.getSummaries()).asList().isNotEmpty());
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
