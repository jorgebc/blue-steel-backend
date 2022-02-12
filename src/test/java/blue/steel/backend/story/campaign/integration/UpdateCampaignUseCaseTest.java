package blue.steel.backend.story.campaign.integration;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import blue.steel.backend.story.campaign.adapter.dto.UpdateCampaignInput;
import blue.steel.backend.story.campaign.entity.Campaign;
import blue.steel.backend.story.campaign.entity.CampaignRepository;
import blue.steel.backend.story.campaign.entity.CampaignRepositoryTest;
import java.util.Arrays;
import java.util.UUID;
import javax.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureWebGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.graphql.test.tester.WebGraphQlTester;

/** Update campaign use case tests. */
@SpringBootTest
@AutoConfigureWebGraphQlTester
@Transactional
public class UpdateCampaignUseCaseTest {

  private static final String UPDATE_CAMPAIGN_QUERY = "story/campaign/queries/updateCampaign";

  @Autowired private WebGraphQlTester graphQlTester;
  @Autowired private CampaignRepository campaignRepository;

  @Test
  @DisplayName("Updating a valid campaign should return a not null campaign")
  void updateValidCampaign() {
    // Given an existing campaign
    Campaign campaign = CampaignRepositoryTest.createCampaign();
    campaign = campaignRepository.save(campaign);

    // And a valid update campaign input
    UUID id = campaign.getId();
    UpdateCampaignInput createCampaignInput =
        new UpdateCampaignInput(id, "new name", "new description", "new imageUrl");

    // When updating a campaign
    graphQlTester
        .queryName(UPDATE_CAMPAIGN_QUERY)
        .variable("input", createCampaignInput)
        .execute()
        .path("updateCampaign.campaign")
        .entity(Campaign.class)

        // Then response should contain a campaign
        .satisfies(updatedCampaign -> assertThat(updatedCampaign).isNotNull());
  }

  @Test
  @DisplayName("Updating a non existing campaign should return a not found error")
  void updateNotFoundCampaign() {
    // Given no campaigns

    // And a valid update campaign input
    UpdateCampaignInput createCampaignInput =
        new UpdateCampaignInput(UUID.randomUUID(), "new name", "new description", "new imageUrl");

    // When updating a campaign
    graphQlTester
        .queryName(UPDATE_CAMPAIGN_QUERY)
        .variable("input", createCampaignInput)
        .execute()
        .errors()

        // Then error response should contain a not found error
        .expect(graphQLError -> graphQLError.getErrorType().equals(ErrorType.NOT_FOUND))
        .verify();
  }

  @Test
  @DisplayName("Updating an invalid campaign should return bad request error")
  void updateInvalidCampaign() {
    // Given an invalid update campaign input
    UpdateCampaignInput updateCampaignInput =
        new UpdateCampaignInput(UUID.randomUUID(), "", "", "");
    String[] campaignFieldNamesWithErrors = {"name", "description", "imageUrl"};

    // When updating a campaign
    graphQlTester
        .queryName(UPDATE_CAMPAIGN_QUERY)
        .variable("input", updateCampaignInput)
        .execute()
        .errors()

        // Then error response should contain a bad request error
        .expect(
            graphQLError ->
                graphQLError.getErrorType().equals(ErrorType.BAD_REQUEST)
                    && Arrays.stream(campaignFieldNamesWithErrors)
                        .allMatch(graphQLError.getMessage()::contains))
        .verify();
  }
}