package blue.steel.backend.story.campaign.integration;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import blue.steel.backend.IntegrationTest;
import blue.steel.backend.story.campaign.adapter.dto.UpdateCampaignInput;
import blue.steel.backend.story.campaign.persistence.Campaign;
import blue.steel.backend.story.campaign.persistence.CampaignRepository;
import blue.steel.backend.story.campaign.persistence.CampaignRepositoryTest;
import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.execution.ErrorType;

/** Update campaign use case tests. */
class UpdateCampaignUseCaseTest extends IntegrationTest {

  private static final String UPDATE_CAMPAIGN_QUERY = "story/campaign/queries/updateCampaign";

  @Autowired private CampaignRepository campaignRepository;

  @Test
  @DisplayName("Updating a valid campaign should return a not null campaign")
  void updateValidCampaign() {
    // Given an existing campaign
    Campaign campaign = CampaignRepositoryTest.createCampaign();
    campaign = campaignRepository.save(campaign);

    // And a valid update campaign input
    UUID id = campaign.getId();
    Integer version = campaign.getVersion();
    UpdateCampaignInput createCampaignInput =
        UpdateCampaignInput.builder()
            .id(id)
            .name("new name")
            .description("new description")
            .imageUrl("new imageUrl")
            .version(version)
            .build();

    // When updating a campaign
    getGraphQlTesterWithAdminJwtToken(UPDATE_CAMPAIGN_QUERY)
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
        UpdateCampaignInput.builder()
            .id(UUID.randomUUID())
            .name("new name")
            .description("new description")
            .imageUrl("new imageUrl")
            .version(0)
            .build();

    // When updating a campaign
    getGraphQlTesterWithAdminJwtToken(UPDATE_CAMPAIGN_QUERY)
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
        UpdateCampaignInput.builder()
            .id(UUID.randomUUID())
            .name("")
            .description("")
            .imageUrl("")
            .version(0)
            .build();
    String[] campaignFieldNamesWithErrors = {"name", "description", "imageUrl"};

    // When updating a campaign
    getGraphQlTesterWithAdminJwtToken(UPDATE_CAMPAIGN_QUERY)
        .variable("input", updateCampaignInput)
        .execute()
        .errors()

        // Then error response should contain a bad request error
        .expect(
            graphQLError ->
                graphQLError.getErrorType().equals(ErrorType.BAD_REQUEST)
                    && Arrays.stream(campaignFieldNamesWithErrors)
                        .allMatch(Objects.requireNonNull(graphQLError.getMessage())::contains))
        .verify();
  }

  @Test
  @DisplayName("Updating a campaign with invalid version should return bad request error")
  void updateValidCampaignWithInvalidVersion() {
    // Given an existing campaign
    Campaign campaign = CampaignRepositoryTest.createCampaign();
    campaign = campaignRepository.save(campaign);

    // And a valid update campaign input with incorrect version
    UUID id = campaign.getId();
    UpdateCampaignInput createCampaignInput =
        UpdateCampaignInput.builder()
            .id(id)
            .name("new name")
            .description("new description")
            .imageUrl("new imageUrl")
            .version(4)
            .build();

    // When updating a campaign
    getGraphQlTesterWithAdminJwtToken(UPDATE_CAMPAIGN_QUERY)
        .variable("input", createCampaignInput)
        .execute()
        .errors()

        // Then error response should contain a bad request error
        .expect(graphQLError -> graphQLError.getErrorType().equals(ErrorType.BAD_REQUEST))
        .verify();
  }
}
