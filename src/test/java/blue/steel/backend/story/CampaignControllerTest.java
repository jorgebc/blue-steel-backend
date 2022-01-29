package blue.steel.backend.story;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import javax.persistence.EntityNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureWebGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.WebGraphQlTester;

@SpringBootTest
@AutoConfigureWebGraphQlTester
class CampaignControllerTest {

  @Autowired private WebGraphQlTester graphQlTester;
  @Autowired private CampaignRepository campaignRepository;

  @Test
  @DisplayName("Fetching for the actual campaign should return an actual campaign")
  void getActualCampaign() {

    // Given a campaign with actual set to true
    Campaign actualCampaign = CampaignRepositoryTest.createCampaign();
    actualCampaign.setActual(true);
    campaignRepository.save(actualCampaign);

    // When fetching for actual campaign
    this.graphQlTester
        .queryName("story/actualCampaign")
        .execute()
        .path("actualCampaign")
        .entity(Campaign.class)

        // Then response should contain a campaign
        .satisfies(campaign -> assertThat(campaign).isNotNull());
  }

  @Test
  @DisplayName("Fetching for the actual campaign should return error")
  void getActualCampaignWhenNoActualCampaign() {

    // Given no actual campaign

    // When fetching for actual campaign
    this.graphQlTester
        .queryName("story/actualCampaign")
        .execute()
        .errors()

        // Then error response should contain EntityNotFoundException message
        .expect(
            graphQLError ->
                graphQLError.getMessage().equals(EntityNotFoundException.class.getSimpleName()));
  }
}
