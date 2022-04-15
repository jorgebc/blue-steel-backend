package blue.steel.backend.story.summary.integration;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import blue.steel.backend.IntegrationTest;
import blue.steel.backend.story.campaign.persistence.Campaign;
import blue.steel.backend.story.campaign.persistence.CampaignRepository;
import blue.steel.backend.story.campaign.persistence.CampaignRepositoryTest;
import blue.steel.backend.story.summary.adapter.dto.CreateCampaignSummaryInput;
import blue.steel.backend.story.summary.persistence.Summary;
import java.time.LocalDate;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.execution.ErrorType;

/** Create campaign summary use case tests. */
class CreateCampaignSummaryTest extends IntegrationTest {

  private static final String CREATE_CAMPAIGN_SUMMARY_QUERY =
      "story/summary/queries/createCampaignSummary";

  @Autowired private CampaignRepository campaignRepository;

  @Test
  @DisplayName("Creating a campaign summary should return a not null summary")
  void createCampaignSummary() {

    // Given a campaign
    Campaign campaign = CampaignRepositoryTest.createCampaign();
    campaign = campaignRepository.save(campaign);
    UUID campaignId = campaign.getId();

    // And a valid creation campaign summary input
    CreateCampaignSummaryInput createCampaignSummaryInput =
        CreateCampaignSummaryInput.builder()
            .campaignId(campaignId)
            .name("name")
            .description("description")
            .gameDate(LocalDate.now())
            .build();

    // When creating a campaign summary
    getGraphQlTesterWithAdminJwtToken(CREATE_CAMPAIGN_SUMMARY_QUERY)
        .variable("input", createCampaignSummaryInput)
        .execute()
        .path("createCampaignSummary.summary")
        .entity(Summary.class)

        // Then response should contain a summary
        .satisfies(summary -> assertThat(summary).isNotNull());
  }

  @Test
  @DisplayName("Creating with invalid input should return an error")
  void createCampaignSummaryWithInvalidInput() {
    // Given an invalid creation campaign summary input
    CreateCampaignSummaryInput createCampaignSummaryInput =
        CreateCampaignSummaryInput.builder().build();

    // When creating a campaign summary with invalid input
    getGraphQlTesterWithAdminJwtToken(CREATE_CAMPAIGN_SUMMARY_QUERY)
        .variable("input", createCampaignSummaryInput)
        .execute()
        .errors()

        // Then error response should contain a validation error
        .expect(
            graphQLError -> graphQLError.getErrorType().equals(graphql.ErrorType.ValidationError))
        .verify();
  }

  @Test
  @DisplayName(
      "Creating a campaign summary for a non existing campaign should return a not found error")
  void createCampaignSummaryWithNotFoundCampaign() {

    // Given no campaign
    UUID campaignId = UUID.randomUUID();

    // And a valid creation campaign summary input
    CreateCampaignSummaryInput createCampaignSummaryInput =
        CreateCampaignSummaryInput.builder()
            .campaignId(campaignId)
            .name("name")
            .description("description")
            .gameDate(LocalDate.now())
            .build();

    // When creating a campaign summary
    getGraphQlTesterWithAdminJwtToken(CREATE_CAMPAIGN_SUMMARY_QUERY)
        .variable("input", createCampaignSummaryInput)
        .execute()
        .errors()

        // Then error response should contain a not found error
        .expect(graphQLError -> graphQLError.getErrorType().equals(ErrorType.NOT_FOUND))
        .verify();
  }
}
