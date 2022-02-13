package blue.steel.backend.story.campaign.integration;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import blue.steel.backend.IntegrationTest;
import blue.steel.backend.story.campaign.adapter.dto.CreateCampaignInput;
import blue.steel.backend.story.campaign.entity.Campaign;
import java.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.graphql.execution.ErrorType;

/** Create campaign use case tests. */
class CreateCampaignUseCaseTest extends IntegrationTest {

  private static final String CREATE_CAMPAIGN_QUERY = "story/campaign/queries/createCampaign";

  @Test
  @DisplayName("Creating a valid campaign should return a not null campaign")
  void createValidCampaign() {
    // Given a valid create campaign input
    CreateCampaignInput createCampaignInput =
        new CreateCampaignInput("name", "description", "imageUrl");

    // When creating a campaign
    getGraphQlTesterWithAdminJwtToken(CREATE_CAMPAIGN_QUERY)
        .variable("input", createCampaignInput)
        .execute()
        .path("createCampaign.campaign")
        .entity(Campaign.class)

        // Then response should contain a campaign
        .satisfies(campaign -> assertThat(campaign).isNotNull());
  }

  @Test
  @DisplayName("Creating an invalid campaign should return bad request error")
  void createInvalidCampaign() {
    // Given an invalid create campaign input
    CreateCampaignInput createCampaignInput = new CreateCampaignInput("", "", "");
    String[] campaignFieldNamesWithErrors = {"name", "description", "imageUrl"};

    // When creating a campaign
    getGraphQlTesterWithAdminJwtToken(CREATE_CAMPAIGN_QUERY)
        .variable("input", createCampaignInput)
        .execute()
        .errors()

        // Then error response should contain a bad request error
        .expect(
            graphQLError ->
                graphQLError.getErrorType().equals(ErrorType.BAD_REQUEST)
                    && Arrays.stream(campaignFieldNamesWithErrors)
                        .allMatch(graphQLError.getMessage()::contains))
        .verify();
  }
}
