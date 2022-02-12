package blue.steel.backend;

import static org.mockito.Mockito.when;

import blue.steel.backend.core.config.SpringSecurityAuditorAware;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureWebGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.graphql.test.tester.WebGraphQlTester;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;

/** Base class for use case tests. */
@SpringBootTest
@AutoConfigureWebGraphQlTester
@Transactional
@WithMockAdminUser
public abstract class UseCaseTest {

  protected static final String VALID_JWT_TOKEN = "token";
  protected static final String ADMIN_USER_ID = "auth0|id";
  protected static final String ADMIN_USER_NAME = "admin";

  @Autowired protected WebGraphQlTester graphQlTester;
  @MockBean private JwtDecoder jwtDecoder;

  public static Jwt getAdminJwt() {
    Jwt jwt =
        Jwt.withTokenValue(VALID_JWT_TOKEN)
            .header("header", "header")
            .claim("sub", ADMIN_USER_ID)
            .claim(SpringSecurityAuditorAware.USER_NAME_CLAIM, ADMIN_USER_NAME)
            .build();
    return jwt;
  }

  protected WebGraphQlTester.WebRequestSpec getGraphQlTesterWithAdminJwtToken(String queryName) {
    mockJwtDecoderWithAdminJwt();
    return graphQlTester
        .queryName(queryName)
        .httpHeaders(headers -> headers.setBearerAuth(VALID_JWT_TOKEN));
  }

  private void mockJwtDecoderWithAdminJwt() {
    Jwt jwt = getAdminJwt();
    when(jwtDecoder.decode(VALID_JWT_TOKEN)).thenReturn(jwt);
  }
}
