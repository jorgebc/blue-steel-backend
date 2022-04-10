package blue.steel.backend.story.summary.usecase.dto;

import blue.steel.backend.core.usecase.UseCaseOutput;
import blue.steel.backend.story.summary.persistence.Summary;
import lombok.Value;

/** Update summary use case output. */
@Value
public class UpdateSummaryUseCaseOutput implements UseCaseOutput {
  Summary summary;
}
