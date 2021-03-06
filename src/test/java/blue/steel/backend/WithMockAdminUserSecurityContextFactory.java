package blue.steel.backend;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

/** WithMockAdminUser factory. */
public class WithMockAdminUserSecurityContextFactory
    implements WithSecurityContextFactory<WithMockAdminUser> {

  @Override
  public SecurityContext createSecurityContext(WithMockAdminUser annotation) {
    SecurityContext context = SecurityContextHolder.createEmptyContext();

    Jwt jwt = IntegrationTest.getAdminJwt();
    Authentication auth = new JwtAuthenticationToken(jwt);
    context.setAuthentication(auth);
    return context;
  }
}
