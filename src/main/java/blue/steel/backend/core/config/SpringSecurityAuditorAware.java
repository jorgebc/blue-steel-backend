package blue.steel.backend.core.config;

import blue.steel.backend.core.entity.User;
import blue.steel.backend.core.entity.UserRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

@Component
class SpringSecurityAuditorAware implements AuditorAware<User> {

  @Autowired private UserRepository userRepository;

  @Override
  public Optional<User> getCurrentAuditor() {

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    String id = authentication.getName();

    Jwt principal = (Jwt) authentication.getPrincipal();
    String username = principal.getClaim("https://blue-steel.com/username");

    User newUser = new User();
    newUser.setId(id);
    newUser.setUserName(username);

    User user = userRepository.findById(id).orElse(userRepository.save(newUser));
    return Optional.of(user);
  }
}
