package blue.steel.backend.story.summary.persistence;

import blue.steel.backend.IntegrationTest;
import blue.steel.backend.story.campaign.persistence.Campaign;
import blue.steel.backend.story.campaign.persistence.CampaignRepository;
import blue.steel.backend.story.campaign.persistence.CampaignRepositoryTest;
import java.time.LocalDate;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/** Campaign summary repository tests. */
public class SummaryRepositoryTest extends IntegrationTest {

  @Autowired private CampaignRepository campaignRepository;
  @Autowired private SummaryRepository summaryRepository;

  /**
   * Creates a campaign summary, no id set.
   *
   * @return a new campaign summary
   */
  public static Summary createSummary(Campaign campaign) {
    Summary summary = new Summary();
    summary.setName("name");
    summary.setDescription("description");
    summary.setGameDate(LocalDate.now());
    summary.setCampaign(campaign);
    return summary;
  }

  @Test
  @DisplayName("Should save a campaign summary")
  void createCampaignSummary() {
    // Given a campaign
    Campaign campaign = CampaignRepositoryTest.createCampaign();
    campaignRepository.save(campaign);

    // When saving a summary
    Summary summary = createSummary(campaign);
    summary = summaryRepository.save(summary);

    // Then the summary id should not be null
    Assertions.assertThat(summary.getId()).isNotNull();
  }
}
