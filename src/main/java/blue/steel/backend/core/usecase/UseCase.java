package blue.steel.backend.core.usecase;

/**
 * Interface for use cases.
 *
 * @param <E> use case input
 * @param <T> use case output
 */
public interface UseCase<E extends UseCaseInput, T extends UseCaseOutput> {
  T execute(E input);
}
