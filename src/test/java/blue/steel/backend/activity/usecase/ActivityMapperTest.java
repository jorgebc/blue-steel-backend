package blue.steel.backend.activity.usecase;

import static org.assertj.core.api.Assertions.assertThat;

import blue.steel.backend.activity.usecase.dto.Activity;
import blue.steel.backend.core.persistence.AuditMetadata;
import blue.steel.backend.story.campaign.persistence.Campaign;
import blue.steel.backend.story.campaign.persistence.CampaignRepositoryTest;
import blue.steel.backend.story.summary.persistence.Summary;
import blue.steel.backend.story.summary.persistence.SummaryRepositoryTest;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ActivityMapperTest {

  @Test
  @DisplayName("Should map all activity types")
  void shouldMapAllActivityTypes() {

    // Given a campaign
    Campaign campaign = CampaignRepositoryTest.createCampaign();
    AuditMetadata campaignAuditingMetadata = new AuditMetadata();
    campaignAuditingMetadata.setLastModifiedDate(LocalDateTime.now().minusDays(2));
    campaign.setAuditingMetadata(campaignAuditingMetadata);
    List<Campaign> campaigns = Collections.singletonList(campaign);

    // And a summary
    Summary summary = SummaryRepositoryTest.createSummary(campaign);
    AuditMetadata summaryAuditingMetadata = new AuditMetadata();
    summaryAuditingMetadata.setLastModifiedDate(LocalDateTime.now().minusDays(2));
    summary.setAuditingMetadata(summaryAuditingMetadata);
    List<Summary> summaries = Collections.singletonList(summary);

    // When getting the last ten last modified elements
    List<Activity> lastActivities = ActivityMapper.getLastTenLastModified(2, campaigns, summaries);

    // Then the list should contain two activities
    assertThat(lastActivities).hasSize(2);
  }
}
