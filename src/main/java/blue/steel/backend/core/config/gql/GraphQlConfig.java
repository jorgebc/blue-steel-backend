package blue.steel.backend.core.config.gql;

import blue.steel.backend.core.config.gql.scalars.Scalars;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

/** GraphQL Configuration. */
@Configuration
public class GraphQlConfig {

  /**
   * Configure the GraphQL runtime wiring.
   *
   * @return the runtime wiring configurer
   */
  @Bean
  public RuntimeWiringConfigurer runtimeWiringConfigurer() {
    return wiringBuilder ->
        wiringBuilder
            .scalar(Scalars.uuidType())
            .scalar(Scalars.localDateType())
            .scalar(Scalars.localDateTimeType());
  }
}
