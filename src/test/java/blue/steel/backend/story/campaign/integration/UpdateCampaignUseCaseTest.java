package blue.steel.backend.story.campaign.integration;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import blue.steel.backend.story.campaign.adapter.dto.UpdateCampaignInput;
import blue.steel.backend.story.campaign.entity.Campaign;
import blue.steel.backend.story.campaign.entity.CampaignRepository;
import blue.steel.backend.story.campaign.entity.CampaignRepositoryTest;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureWebGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.WebGraphQlTester;

/** Update campaign use case tests. */
@SpringBootTest
@AutoConfigureWebGraphQlTester
public class UpdateCampaignUseCaseTest {

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
        .queryName("story/campaign/queries/updateCampaign")
        .variable("input", createCampaignInput)
        .execute()
        .path("updateCampaign.campaign")
        .entity(Campaign.class)

        // Then response should contain a campaign
        .satisfies(updatedCampaign -> assertThat(updatedCampaign).isNotNull());
  }
}
