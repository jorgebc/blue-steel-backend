package blue.steel.backend.story.campaign.persistence;

import blue.steel.backend.IntegrationTest;
import blue.steel.backend.story.summary.persistence.Summary;
import blue.steel.backend.story.summary.persistence.SummaryRepository;
import blue.steel.backend.story.summary.persistence.SummaryRepositoryTest;
import java.util.Optional;
import java.util.Set;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.transaction.TestTransaction;

/** Campaign repository tests. */
public class CampaignRepositoryTest extends IntegrationTest {

  @Autowired private CampaignRepository campaignRepository;
  @Autowired private SummaryRepository summaryRepository;

  /**
   * Creates a campaign, no id set and actual to false.
   *
   * @return a new campaign
   */
  public static Campaign createCampaign() {
    Campaign campaign = new Campaign();
    campaign.setName("Campaign");
    campaign.setDescription("Description");
    campaign.setImageUrl("url");
    return campaign;
  }

  @Test
  @DisplayName("Find campaign by actual should not find a campaign")
  void findByActualShouldNotFindCampaign() {
    // Given no actual campaign
    // When searching by actual
    Optional<Campaign> campaign = campaignRepository.findByActual(true);

    // Then the campaign should not be present
    Assertions.assertThat(campaign).isNotPresent();
  }

  @Test
  @DisplayName("Find campaign by actual should find a campaign")
  void findByActualShouldFindCampaign() {
    // Given a campaign with actual to true
    Campaign campaign = createCampaign();
    campaign.setActual(true);
    campaignRepository.save(campaign);

    // When searching by actual
    Optional<Campaign> actualCampaign = campaignRepository.findByActual(true);

    // Then the campaign should be present
    Assertions.assertThat(actualCampaign).isPresent();
  }

  @Test
  @DisplayName("Find campaign by id should find a campaign")
  void findByIdShouldFindCampaign() {
    // Given a campaign
    Campaign campaign = createCampaign();
    campaignRepository.save(campaign);

    // When searching by id
    Optional<Campaign> actualCampaign = campaignRepository.findById(campaign.getId());

    // Then the campaign should be present
    Assertions.assertThat(actualCampaign).isPresent();
  }

  @Test
  @DisplayName("Find campaign with summary should contain a summary")
  void findWithSummaryShouldContainSummary() {
    // Given a campaign
    Campaign campaign = createCampaign();
    campaignRepository.save(campaign);
    TestTransaction.flagForCommit();
    TestTransaction.end();
    TestTransaction.start();

    // And a summary
    Summary summary = SummaryRepositoryTest.createSummary(campaign);
    summaryRepository.save(summary);
    TestTransaction.flagForCommit(); // need this, otherwise the next line does a rollback
    TestTransaction.end();
    TestTransaction.start();

    // When searching by id
    Optional<Campaign> campaignWithSummary = campaignRepository.findById(campaign.getId());

    // Then the campaign should be present
    Assertions.assertThat(campaignWithSummary).isPresent();
    // And the summary should be present
    Set<Summary> summaries = campaignWithSummary.get().getSummaries();
    Assertions.assertThat(summaries).isNotEmpty();
  }
}
