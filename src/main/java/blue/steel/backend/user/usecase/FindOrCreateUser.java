package blue.steel.backend.user.usecase;

import blue.steel.backend.core.usecase.UseCase;
import blue.steel.backend.user.persistence.User;
import blue.steel.backend.user.persistence.UserRepository;
import blue.steel.backend.user.usecase.dto.CreateUserInput;
import blue.steel.backend.user.usecase.dto.CreateUserOutput;
import javax.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/** Create user use case. */
@Service
@Transactional
@AllArgsConstructor
public class FindOrCreateUser implements UseCase<CreateUserInput, CreateUserOutput> {

  @Autowired private UserRepository userRepository;

  @Override
  public CreateUserOutput execute(CreateUserInput input) {

    String id = input.getId();
    User user =
        userRepository
            .findById(id)
            .orElseGet(
                () -> {
                  User entity = input.toUser();
                  return userRepository.save(entity);
                });

    return CreateUserOutput.builder().user(user).build();
  }
}
