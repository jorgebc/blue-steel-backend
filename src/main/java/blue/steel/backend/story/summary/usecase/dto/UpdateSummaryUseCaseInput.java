package blue.steel.backend.story.summary.usecase.dto;

import blue.steel.backend.core.persistence.Versionable;
import blue.steel.backend.core.usecase.UseCaseInput;
import java.time.LocalDate;
import java.util.UUID;
import lombok.Builder;
import lombok.Value;

/** Update campaign summary use case input. */
@Value
@Builder
public class UpdateSummaryUseCaseInput implements UseCaseInput, Versionable {
  UUID campaignId;
  UUID id;
  String name;
  String description;
  LocalDate gameDate;
  Integer version;
}
