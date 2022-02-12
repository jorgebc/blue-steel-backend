package blue.steel.backend.story.campaign.integration;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import blue.steel.backend.UseCaseTest;
import blue.steel.backend.story.campaign.adapter.dto.CreateCampaignInput;
import blue.steel.backend.story.campaign.entity.Campaign;
import java.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.graphql.test.tester.WebGraphQlTester;

/** Create campaign use case tests. */
class CreateCampaignUseCaseTest extends UseCaseTest {

  private static final String CREATE_CAMPAIGN_QUERY = "story/campaign/queries/createCampaign";

  @Autowired private WebGraphQlTester graphQlTester;

  @Test
  @DisplayName("Creating a valid campaign should return a not null campaign")
  void createValidCampaign() {
    // Given a valid create campaign input
    CreateCampaignInput createCampaignInput =
        new CreateCampaignInput("name", "description", "imageUrl");
    // And a valid token
    mockJwtDecoderDecode();

    // When creating a campaign
    graphQlTester
        .queryName(CREATE_CAMPAIGN_QUERY)
        .httpHeaders(headers -> headers.setBearerAuth(TOKEN))
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
    graphQlTester
        .queryName(CREATE_CAMPAIGN_QUERY)
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
