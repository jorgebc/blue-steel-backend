package blue.steel.backend.core.config;

import blue.steel.backend.core.persistence.User;
import blue.steel.backend.core.persistence.UserRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

/**
 * Extracts auditor data from db if user exists, else extracts data from authentication and saves a
 * new user.
 */
@Component
public class SpringSecurityAuditorAware implements AuditorAware<User> {

  public static final String USER_NAME_CLAIM = "https://blue-steel.com/username";

  @Autowired private UserRepository userRepository;

  @Override
  public Optional<User> getCurrentAuditor() {

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    String id = authentication.getName();

    Jwt principal = (Jwt) authentication.getPrincipal();
    String username = principal.getClaim(USER_NAME_CLAIM);

    User newUser = new User();
    newUser.setId(id);
    newUser.setName(username);

    User user = userRepository.findById(id).orElse(userRepository.save(newUser));
    return Optional.of(user);
  }
}
