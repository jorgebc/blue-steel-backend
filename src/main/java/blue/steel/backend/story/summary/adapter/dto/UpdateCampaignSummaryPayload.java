package blue.steel.backend.story.summary.adapter.dto;

import blue.steel.backend.story.summary.persistence.Summary;
import lombok.Value;

/** Update campaign summary response. */
@Value
public class UpdateCampaignSummaryPayload {
  Summary summary;
}
