package blue.steel.backend.story.summary.adapter.dto;

import java.util.UUID;
import lombok.Builder;
import lombok.Data;

/** Delete campaign summary payload. */
@Data
@Builder
public class DeleteCampaignSummaryPayload {
  UUID summaryId;
}
