package blue.steel.backend.core.config;

import graphql.GraphqlErrorBuilder;
import javax.persistence.EntityNotFoundException;
import javax.persistence.OptimisticLockException;
import javax.validation.ConstraintViolationException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.graphql.execution.DataFetcherExceptionResolver;
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.security.access.AccessDeniedException;

/** Exception resolver configuration. */
@Configuration
public class ExceptionResolverConfig {

  /**
   * Resolves certain exceptions to custom graphql errors.
   *
   * <ul>
   *   <li>EntityNotFoundException and EmptyResultDataAccessException with error type NOT_FOUND
   *   <li>ConstraintViolationException with error BAD_REQUEST and validation errors in message
   * </ul>
   *
   * @return DataFetcherExceptionResolver with custom exceptions resolvers.
   */
  @Bean
  public DataFetcherExceptionResolver exceptionResolver() {
    return DataFetcherExceptionResolverAdapter.from(
        (ex, env) -> {
          if (ex instanceof EntityNotFoundException
              || ex instanceof EmptyResultDataAccessException) {
            return GraphqlErrorBuilder.newError(env)
                .message("Entity not found")
                .errorType(ErrorType.NOT_FOUND)
                .build();
          } else if (ex instanceof ConstraintViolationException) {
            return GraphqlErrorBuilder.newError(env)
                .message(ex.getMessage())
                .errorType(ErrorType.BAD_REQUEST)
                .build();
          } else if (ex instanceof AccessDeniedException) {
            return GraphqlErrorBuilder.newError(env)
                .message(ex.getMessage())
                .errorType(ErrorType.FORBIDDEN)
                .build();
          } else if (ex instanceof OptimisticLockException) {
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
