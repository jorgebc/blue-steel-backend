package blue.steel.backend.core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/** Persistence configuration. */
@Configuration
@EnableTransactionManagement
@EnableJpaAuditing
public class PersistenceConfig {}
