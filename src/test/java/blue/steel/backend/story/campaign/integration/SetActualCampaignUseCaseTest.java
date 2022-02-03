package blue.steel.backend.story.campaign.integration;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import blue.steel.backend.story.campaign.adapter.dto.SetActualCampaignInput;
import blue.steel.backend.story.campaign.entity.Campaign;
import blue.steel.backend.story.campaign.entity.CampaignRepository;
import blue.steel.backend.story.campaign.entity.CampaignRepositoryTest;
import java.util.UUID;
import javax.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureWebGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.graphql.test.tester.WebGraphQlTester;

/** Set actual campaign use case tests. */
@SpringBootTest
@AutoConfigureWebGraphQlTester
@Transactional
class SetActualCampaignUseCaseTest {

  private static final String SET_ACTUAL_CAMPAIGN_QUERY =
      "story/campaign/queries/setActualCampaign";

  @Autowired private WebGraphQlTester graphQlTester;
  @Autowired private CampaignRepository campaignRepository;

  @Test
  @DisplayName("Set actual campaign when campaign does not exist should return not found error")
  void setActualCampaignWhenNotFound() {
    // Given an actual campaign
    Campaign actualCampaign = CampaignRepositoryTest.createCampaign();
    actualCampaign.setActual(true);
    campaignRepository.save(actualCampaign);

    // And a valid set actual campaign input
    UUID campaignId = UUID.randomUUID();
    SetActualCampaignInput input = new SetActualCampaignInput(campaignId);

    // When setting the campaign to be the actual
    graphQlTester
        .queryName(SET_ACTUAL_CAMPAIGN_QUERY)
        .variable("input", input)
        .execute()
        .errors()

        // Then error response should contain a not found error
        .expect(graphQLError -> graphQLError.getErrorType().equals(ErrorType.NOT_FOUND))
        .verify();
  }

  @Test
  @DisplayName("Set actual campaign with no actual campaign should return the actual campaign")
  void setActualCampaignWhenNoActualCampaign() {
    // Given a campaign
    Campaign campaign = CampaignRepositoryTest.createCampaign();
    campaign = campaignRepository.save(campaign);

    // And a valid set actual campaign input
    UUID campaignId = campaign.getId();
    SetActualCampaignInput input = new SetActualCampaignInput(campaignId);

    // When setting the campaign to be the actual
    graphQlTester
        .queryName(SET_ACTUAL_CAMPAIGN_QUERY)
        .variable("input", input)
        .execute()
        .path("setActualCampaign.campaign")
        .entity(Campaign.class)

        // Then response should contain a campaign
        .satisfies(actualCampaign -> assertThat(actualCampaign.getId()).isEqualTo(campaignId));
  }

  @Test
  @DisplayName("Set actual campaign with an actual campaign should return the new actual campaign")
  void setActualCampaignWhenActualCampaign() {
    // Given an actual campaign
    Campaign actualCampaign = CampaignRepositoryTest.createCampaign();
    actualCampaign.setActual(true);
    campaignRepository.save(actualCampaign);
    // And a campaign
    Campaign campaign = CampaignRepositoryTest.createCampaign();
    campaign = campaignRepository.save(campaign);

    // And a valid set actual campaign input
    UUID campaignId = campaign.getId();
    SetActualCampaignInput input = new SetActualCampaignInput(campaignId);

    // When setting the campaign to be the actual
    graphQlTester
        .queryName(SET_ACTUAL_CAMPAIGN_QUERY)
        .variable("input", input)
        .execute()
        .path("setActualCampaign.campaign")
        .entity(Campaign.class)

        // Then response should contain a campaign
        .satisfies(
            newActualCampaign -> assertThat(newActualCampaign.getId()).isEqualTo(campaignId));
  }
}
