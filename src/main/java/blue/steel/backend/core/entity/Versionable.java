package blue.steel.backend.core.entity;

/** Interface for manually checking optimistic locking. */
public interface Versionable {
  Integer getVersion();
}
