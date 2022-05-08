package blue.steel.backend.user.usecase;

import blue.steel.backend.user.persistence.User;
import blue.steel.backend.user.persistence.UserRepository;
import javax.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/** User query service. */
@Service
@AllArgsConstructor
public class UserQuery {

  private final UserRepository userRepository;

  public User findById(String id) {
    return userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
  }
}
