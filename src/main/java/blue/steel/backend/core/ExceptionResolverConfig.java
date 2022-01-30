package blue.steel.backend.core;

import graphql.GraphqlErrorBuilder;
import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.DataFetcherExceptionResolver;
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.graphql.execution.ErrorType;

@Configuration
public class ExceptionResolverConfig {

  @Bean
  public DataFetcherExceptionResolver exceptionResolver() {
    return DataFetcherExceptionResolverAdapter.from(
        (ex, env) -> {
          if (ex instanceof EntityNotFoundException) {
            return GraphqlErrorBuilder.newError(env)
                .message("Entity not found")
                .errorType(ErrorType.NOT_FOUND)
                .build();
          } else if (ex instanceof ConstraintViolationException) {
            return GraphqlErrorBuilder.newError(env)
                .message(ex.getMessage())
                .errorType(ErrorType.BAD_REQUEST)
                .build();
          } else {
            return null;
          }
        });
  }
}
