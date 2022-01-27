package blue.steel.backend.campaign;

import blue.steel.backend.campaing.Campaign;
import blue.steel.backend.campaing.CampaignRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
public class CampaignRepositoryTest {

  @Autowired private CampaignRepository campaignRepository;

  @Test
  @DisplayName("Find campaign by actual should not find a campaign")
  void findByActualShouldNotFindCampaign() {
    Optional<Campaign> campaign = campaignRepository.findByActual(true);
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

  private Campaign createCampaign() {
    Campaign campaign = new Campaign();
    campaign.setName("Campaign");
    campaign.setDescription("Description");
    campaign.setImageUrl("url");
    return campaign;
  }
}
