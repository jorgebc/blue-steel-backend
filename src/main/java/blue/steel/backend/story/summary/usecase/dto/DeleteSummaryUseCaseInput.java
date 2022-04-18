package blue.steel.backend.story.summary.usecase.dto;

import blue.steel.backend.core.usecase.UseCaseInput;
import java.util.UUID;
import lombok.Builder;
import lombok.Value;

/** Delete campaign summary use case input. */
@Value
@Builder
public class DeleteSummaryUseCaseInput implements UseCaseInput {
  UUID summaryId;
}
