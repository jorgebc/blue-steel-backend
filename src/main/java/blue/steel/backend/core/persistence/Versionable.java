package blue.steel.backend.core.persistence;

/** Interface for manually checking optimistic locking. */
public interface Versionable {
  Integer getVersion();
}
