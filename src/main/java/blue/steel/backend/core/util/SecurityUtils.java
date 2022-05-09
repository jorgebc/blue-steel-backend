package blue.steel.backend.core.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/** Security utils. */
public class SecurityUtils {

  private SecurityUtils() {
    throw new IllegalStateException("Utility class");
  }

  /**
   * Gets the current user id.
   *
   * @return the current user id
   */
  public static String getUserId() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return authentication.getName();
  }
}
