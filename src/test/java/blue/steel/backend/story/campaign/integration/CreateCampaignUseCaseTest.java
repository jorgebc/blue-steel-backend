package blue.steel.backend.story.campaign.integration;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import blue.steel.backend.story.campaign.adapter.dto.CreateCampaignInput;
import blue.steel.backend.story.campaign.entity.Campaign;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureWebGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.WebGraphQlTester;

/** Create campaign use case tests. */
@SpringBootTest
@AutoConfigureWebGraphQlTester
public class CreateCampaignUseCaseTest {

  @Autowired private WebGraphQlTester graphQlTester;

  @Test
  @DisplayName("Creating a valid campaign should return a not null campaign")
  void createValidCampaign() {
    // Given a valid create campaign input
    CreateCampaignInput createCampaignInput =
        new CreateCampaignInput("name", "description", "imageUrl");

    // When creating a campaign
    graphQlTester
        .queryName("story/campaign/queries/createCampaign")
        .variable("input", createCampaignInput)
        .execute()
        .path("createCampaign.campaign")
        .entity(Campaign.class)

        // Then response should contain a campaign
        .satisfies(campaign -> assertThat(campaign).isNotNull());
  }
}
