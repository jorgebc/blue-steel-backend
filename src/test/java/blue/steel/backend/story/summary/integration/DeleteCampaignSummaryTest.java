package blue.steel.backend.story.summary.integration;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import blue.steel.backend.IntegrationTest;
import blue.steel.backend.story.campaign.persistence.Campaign;
import blue.steel.backend.story.campaign.persistence.CampaignRepository;
import blue.steel.backend.story.campaign.persistence.CampaignRepositoryTest;
import blue.steel.backend.story.summary.adapter.dto.DeleteCampaignSummaryInput;
import blue.steel.backend.story.summary.persistence.Summary;
import blue.steel.backend.story.summary.persistence.SummaryRepository;
import blue.steel.backend.story.summary.persistence.SummaryRepositoryTest;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.execution.ErrorType;

/** Delete campaign summary use case tests. */
@DisplayName("Delete campaign summary use case tests")
class DeleteCampaignSummaryTest extends IntegrationTest {

  private static final String DELETE_CAMPAIGN_SUMMARY_QUERY =
      "story/summary/queries/deleteCampaignSummary";

  @Autowired private CampaignRepository campaignRepository;
  @Autowired private SummaryRepository summaryRepository;

  @Test
  @DisplayName("Delete campaign summary should return deleted summary id")
  void deleteCampaignSummary() {
    // Given a campaign
    Campaign campaign = CampaignRepositoryTest.createCampaign();
    campaign = campaignRepository.save(campaign);
    // And a summary
    Summary summary = SummaryRepositoryTest.createSummary(campaign);
    summary = summaryRepository.save(summary);

    UUID summaryId = summary.getId();
    DeleteCampaignSummaryInput input =
        DeleteCampaignSummaryInput.builder().summaryId(summaryId).build();

    // When deleting the campaign summary
    getGraphQlTesterWithAdminJwtToken(DELETE_CAMPAIGN_SUMMARY_QUERY)
        .variable("input", input)
        .execute()
        .path("deleteCampaignSummary.summaryId")
        .entity(UUID.class)

        // Then response should contain a summary
        .satisfies(deletedSummaryId -> assertThat(deletedSummaryId).isEqualTo(summaryId));

    // And summary should not be found
    assertThat(summaryRepository.findById(summaryId)).isEmpty();
  }

  @Test
  @DisplayName("Delete a non existing campaign summary should return a not found error")
  void deleteNotFoundSummary() {
    // Given no campaigns or summaries

    // And a valid delete campaign summary input
    DeleteCampaignSummaryInput input =
        DeleteCampaignSummaryInput.builder().summaryId(UUID.randomUUID()).build();

    // When deleting a campaign
    getGraphQlTesterWithAdminJwtToken(DELETE_CAMPAIGN_SUMMARY_QUERY)
        .variable("input", input)
        .execute()
        .errors()

        // Then error response should contain a not found error
        .expect(graphQLError -> graphQLError.getErrorType().equals(ErrorType.NOT_FOUND))
        .verify();
  }
}
