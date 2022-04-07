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

/** Create campaign summary use case tests. */
class CreateCampaignSummaryUseCaseTest extends IntegrationTest {

  private static final String CREATE_CAMPAIGN_SUMMARY_QUERY =
      "story/summary/queries/createCampaignSummary";

  @Autowired private CampaignRepository campaignRepository;

  @Test
  @DisplayName("Creating a valid campaign summary should return a not null summary")
  void createValidCampaignSummary() {

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
}
