package blue.steel.backend.user.usecase;

import blue.steel.backend.core.usecase.UseCase;
import blue.steel.backend.user.persistence.User;
import blue.steel.backend.user.persistence.UserRepository;
import blue.steel.backend.user.usecase.dto.UpdateUserUseCaseInput;
import blue.steel.backend.user.usecase.dto.UpdateUserUseCaseOutput;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/** Update user use case. */
@Service
@Transactional
@AllArgsConstructor
public class UpdateUser implements UseCase<UpdateUserUseCaseInput, UpdateUserUseCaseOutput> {

  @Autowired private UserRepository userRepository;

  @Override
  public UpdateUserUseCaseOutput execute(UpdateUserUseCaseInput input) {
    User user =
        userRepository.findById(input.getUserId()).orElseThrow(EntityNotFoundException::new);

    user.setName(input.getName());
    user.setImageUrl(input.getImageUrl());

    return UpdateUserUseCaseOutput.builder().user(user).build();
  }
}
