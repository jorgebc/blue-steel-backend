package blue.steel.backend.story.campaign.entity;

import blue.steel.backend.IntegrationTest;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/** Campaign repository tests. */
public class CampaignRepositoryTest extends IntegrationTest {

  @Autowired private CampaignRepository campaignRepository;

  /**
   * Creates a Campaign, no id set and actual to false.
   *
   * @return Campaign
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
}
