package blue.steel.backend.story.campaign.integration;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import blue.steel.backend.UseCaseTest;
import blue.steel.backend.story.campaign.adapter.dto.DeleteCampaignInput;
import blue.steel.backend.story.campaign.entity.Campaign;
import blue.steel.backend.story.campaign.entity.CampaignRepository;
import blue.steel.backend.story.campaign.entity.CampaignRepositoryTest;
import java.util.Arrays;
import java.util.UUID;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.execution.ErrorType;

/** Delete campaign use case tests. */
class DeleteCampaignUseCaseTest extends UseCaseTest {

  private static final String DELETE_CAMPAIGN_QUERY = "story/campaign/queries/deleteCampaign";

  @Autowired private CampaignRepository campaignRepository;

  @Test
  @DisplayName("Deleting an existing campaign should return a not null id")
  void deleteValidCampaign() {
    // Given an existing campaign
    Campaign campaign = CampaignRepositoryTest.createCampaign();
    campaign = campaignRepository.save(campaign);

    // And a valid delete campaign input
    UUID id = campaign.getId();
    DeleteCampaignInput deleteCampaignInput = new DeleteCampaignInput(id);

    // When deleting a campaign
    getGraphQlTesterWithAdminJwtToken(DELETE_CAMPAIGN_QUERY)
        .variable("input", deleteCampaignInput)
        .execute()
        .path("deleteCampaign.campaignId")
        .entity(UUID.class)

        // Then response should contain an id
        .satisfies(deletedCampaignId -> assertThat(deletedCampaignId).isNotNull());
  }

  @Test
  @DisplayName("Deleting a non existing campaign should return a not found error")
  void deleteNotFoundCampaign() {
    // Given no campaigns

    // And a valid delete campaign input
    DeleteCampaignInput deleteCampaignInput = new DeleteCampaignInput(UUID.randomUUID());

    // When deleting a campaign
    getGraphQlTesterWithAdminJwtToken(DELETE_CAMPAIGN_QUERY)
        .variable("input", deleteCampaignInput)
        .execute()
        .errors()

        // Then error response should contain a not found error
        .expect(graphQLError -> graphQLError.getErrorType().equals(ErrorType.NOT_FOUND))
        .verify();
  }

  @Disabled("Not null annotation does not behave as Not empty and others when attribute is null")
  @Test
  @DisplayName("Deleting a campaign with invalid payload should return bad request error")
  void deleteInvalidCampaign() {
    // Given an invalid delete campaign input
    DeleteCampaignInput deleteCampaignInput = new DeleteCampaignInput(null);
    String[] inputFieldNamesWithErrors = {"id"};

    // When deleting a campaign
    getGraphQlTesterWithAdminJwtToken(DELETE_CAMPAIGN_QUERY)
        .variable("input", deleteCampaignInput)
        .execute()
        .errors()

        // Then error response should contain a bad request error
        .expect(
            graphQLError ->
                graphQLError.getErrorType().equals(ErrorType.BAD_REQUEST)
                    && Arrays.stream(inputFieldNamesWithErrors)
                        .allMatch(graphQLError.getMessage()::contains))
        .verify();
  }
}
