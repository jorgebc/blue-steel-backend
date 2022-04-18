package blue.steel.backend.story.summary.integration;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import blue.steel.backend.IntegrationTest;
import blue.steel.backend.story.campaign.persistence.Campaign;
import blue.steel.backend.story.campaign.persistence.CampaignRepository;
import blue.steel.backend.story.campaign.persistence.CampaignRepositoryTest;
import blue.steel.backend.story.summary.adapter.dto.UpdateCampaignSummaryInput;
import blue.steel.backend.story.summary.persistence.Summary;
import blue.steel.backend.story.summary.persistence.SummaryRepository;
import blue.steel.backend.story.summary.persistence.SummaryRepositoryTest;
import java.time.LocalDate;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/** Update campaign summary use case tests. */
@DisplayName("Update campaign summary use case tests")
class UpdateCampaignSummaryTest extends IntegrationTest {

  private static final String UPDATE_CAMPAIGN_SUMMARY_QUERY =
      "story/summary/queries/updateCampaignSummary";

  @Autowired private CampaignRepository campaignRepository;
  @Autowired private SummaryRepository summaryRepository;

  @Test
  @DisplayName("Updating a valid campaign summary should return a valid campaign summary")
  void updateCampaignSummary() {
    // Given a campaign
    Campaign campaign = CampaignRepositoryTest.createCampaign();
    campaign = campaignRepository.save(campaign);
    // And a summary
    Summary summary = SummaryRepositoryTest.createSummary(campaign);
    summary = summaryRepository.save(summary);

    // And an update campaign summary request
    UUID campaignId = campaign.getId();
    UUID summaryId = summary.getId();
    Integer version = summary.getVersion();
    UpdateCampaignSummaryInput input =
        UpdateCampaignSummaryInput.builder()
            .campaignId(campaignId)
            .id(summaryId)
            .name("new name")
            .description("new description")
            .gameDate(LocalDate.now())
            .version(version)
            .build();

    // When updating the campaign summary
    getGraphQlTesterWithAdminJwtToken(UPDATE_CAMPAIGN_SUMMARY_QUERY)
        .variable("input", input)
        .execute()
        .path("updateCampaignSummary.summary")
        .entity(Summary.class)

        // Then response should contain a summary
        .satisfies(updatedSummary -> assertThat(updatedSummary).isNotNull());
  }
}
