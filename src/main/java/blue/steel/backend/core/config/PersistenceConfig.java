package blue.steel.backend.core.config;

import java.util.Optional;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/** Persistence configuration. */
@Configuration
@EnableTransactionManagement
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class PersistenceConfig {

  @Bean
  public AuditorAware<String> auditorAware() {
    return () -> Optional.of("user name");
  }
}
