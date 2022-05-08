package blue.steel.backend.core.config;

import blue.steel.backend.user.persistence.User;
import blue.steel.backend.user.usecase.FindOrCreateUser;
import blue.steel.backend.user.usecase.dto.CreateUserInput;
import blue.steel.backend.user.usecase.dto.CreateUserOutput;
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

  @Autowired private FindOrCreateUser findOrCreateUser;

  @Override
  public Optional<User> getCurrentAuditor() {

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    Jwt principal = (Jwt) authentication.getPrincipal();

    String id = authentication.getName();
    String username = principal.getClaim(USER_NAME_CLAIM);

    CreateUserInput newUser = CreateUserInput.builder().id(id).name(username).build();
    CreateUserOutput findOrCreateOutput = findOrCreateUser.execute(newUser);

    return Optional.of(findOrCreateOutput.getUser());
  }
}
