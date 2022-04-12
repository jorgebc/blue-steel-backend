package blue.steel.backend.story.summary.adapter.dto;

import blue.steel.backend.story.summary.persistence.Summary;
import lombok.Builder;
import lombok.Value;

/** Create campaign summary response. */
@Value
@Builder
public class CreateCampaignSummaryPayload {
  Summary summary;
}
