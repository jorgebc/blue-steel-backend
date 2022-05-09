package blue.steel.backend.user.adapter.dto;

import blue.steel.backend.user.usecase.dto.UpdateUserUseCaseInput;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Value;

/** Update user input. */
@Value
@Builder
public class UpdateUserInput {
  @NotEmpty String userId;
  @NotEmpty String name;
  @NotEmpty String imageUrl;

  /**
   * Convert to use case input.
   *
   * @return the use case input
   */
  @JsonIgnore
  public UpdateUserUseCaseInput toUpdateUseCaseInput() {
    return UpdateUserUseCaseInput.builder().userId(userId).name(name).imageUrl(imageUrl).build();
  }
}
