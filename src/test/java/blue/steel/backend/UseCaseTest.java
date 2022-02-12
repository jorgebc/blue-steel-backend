package blue.steel.backend;

import static org.mockito.Mockito.when;

import java.time.Instant;
import java.util.Map;
import javax.transaction.Transactional;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureWebGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;

@SpringBootTest
@AutoConfigureWebGraphQlTester
@Transactional
public abstract class UseCaseTest {

  protected static final String TOKEN = "token";

  @MockBean
  @SuppressWarnings("unused")
  private JwtDecoder jwtDecoder;

  protected void mockJwtDecoderDecode() {
    Map<String, Object> headers =
        Map.ofEntries(Map.entry("kid", "RTkyOTU4ODE3QTFDODc4MUQzRUM5NTAzNTQ1RDNBQUNCNTEzOEMyNQ"));
    Map<String, Object> claims =
        Map.ofEntries(
            Map.entry("sub", "auth0|id"), Map.entry("https://blue-steel.com/username", "admin"));
    Jwt jwt = new Jwt(TOKEN, Instant.now(), Instant.MAX, headers, claims);
    when(jwtDecoder.decode(TOKEN)).thenReturn(jwt);
  }
}
