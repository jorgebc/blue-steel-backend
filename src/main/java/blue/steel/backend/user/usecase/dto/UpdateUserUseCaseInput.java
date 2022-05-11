package blue.steel.backend.user.usecase.dto;

import blue.steel.backend.core.usecase.UseCaseInput;
import lombok.Builder;
import lombok.Value;

/** Update campaign use case input. */
@Value
@Builder
public class UpdateUserUseCaseInput implements UseCaseInput {
  String userId;
  String name;
  String imageUrl;
}
