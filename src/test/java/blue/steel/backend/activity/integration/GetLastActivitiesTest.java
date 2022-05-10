package blue.steel.backend.activity.integration;

import blue.steel.backend.IntegrationTest;
import blue.steel.backend.activity.usecase.dto.Activity;
import blue.steel.backend.story.campaign.persistence.Campaign;
import blue.steel.backend.story.campaign.persistence.CampaignRepository;
import blue.steel.backend.story.campaign.persistence.CampaignRepositoryTest;
import blue.steel.backend.story.summary.persistence.Summary;
import blue.steel.backend.story.summary.persistence.SummaryRepository;
import blue.steel.backend.story.summary.persistence.SummaryRepositoryTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.transaction.TestTransaction;

class GetLastActivitiesTest extends IntegrationTest {

  private static final String GET_LAST_ACTIVITY_QUERY = "activity/queries/getLastActivities";

  @Autowired private CampaignRepository campaignRepository;
  @Autowired private SummaryRepository summaryRepository;

  @Test
  @DisplayName("Fetching for last activities should return a list of activities")
  void getLastActivities() {
    // Given a campaign with a summary
    Campaign campaign = CampaignRepositoryTest.createCampaign();
    campaign = campaignRepository.save(campaign);
    TestTransaction.flagForCommit();
    TestTransaction.end();
    TestTransaction.start();

    Summary summary = SummaryRepositoryTest.createSummary(campaign);
    summaryRepository.save(summary);
    TestTransaction.flagForCommit();
    TestTransaction.end();
    TestTransaction.start();

    // When fetching the last activities
    getGraphQlTesterWithAdminJwtToken(GET_LAST_ACTIVITY_QUERY)
        .execute()
        .path("getLastActivities.lastActivities[*]")
        .entityList(Activity.class)

        // Then response should contain a list with two elements
        .hasSize(2);
  }
}
