package blue.steel.backend.story.campaign.integration;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import blue.steel.backend.story.campaign.entity.Campaign;
import blue.steel.backend.story.campaign.entity.CampaignRepository;
import blue.steel.backend.story.campaign.entity.CampaignRepositoryTest;
import javax.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureWebGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.graphql.test.tester.WebGraphQlTester;

@SpringBootTest
@AutoConfigureWebGraphQlTester
@Transactional
class GetActualCampaignTest {

  public static final String ACTUAL_CAMPAIGN_QUERY = "story/campaign/queries/getActualCampaign";

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
        .queryName(ACTUAL_CAMPAIGN_QUERY)
        .execute()
        .path("getActualCampaign.campaign")
        .entity(Campaign.class)

        // Then response should contain a campaign
        .satisfies(
            fetchedCampaign -> assertThat(fetchedCampaign.getId().equals(actualCampaign.getId())));
  }

  @Test
  @DisplayName("Fetching for the actual campaign should return not found error")
  void getActualCampaignWhenNoActualCampaign() {
    // Given no actual campaign
    Campaign actualCampaign = CampaignRepositoryTest.createCampaign();
    actualCampaign.setActual(false);
    campaignRepository.save(actualCampaign);

    // When fetching for actual campaign
    this.graphQlTester
        .queryName(ACTUAL_CAMPAIGN_QUERY)
        .execute()
        .errors()

        // Then error response should contain a not found error
        .expect(graphQLError -> graphQLError.getErrorType().equals(ErrorType.NOT_FOUND))
        .verify();
  }
}