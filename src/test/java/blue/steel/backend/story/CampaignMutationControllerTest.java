package blue.steel.backend.story;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureWebGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.WebGraphQlTester;

/** Campaign mutation controller tests. */
@SpringBootTest
@AutoConfigureWebGraphQlTester
public class CampaignMutationControllerTest {

  @Autowired private WebGraphQlTester graphQlTester;

  @Test
  @DisplayName("Creating a valid campaign should a not null campaign")
  void createValidCampaign() {
    // Given a valida create campaign input
    CreateCampaignInput createCampaignInput =
        new CreateCampaignInput("name", "description", "imageUrl");

    // When creating a campaign
    graphQlTester
        .queryName("story/queries/createCampaign")
        .variable("input", createCampaignInput)
        .execute()
        .path("createCampaign.campaign")
        .entity(Campaign.class)

        // Then response should contain a campaign
        .satisfies(campaign -> assertThat(campaign).isNotNull());
  }
}
