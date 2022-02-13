package blue.steel.backend.core.usecase;

import blue.steel.backend.core.entity.Versionable;
import javax.persistence.OptimisticLockException;
import org.springframework.beans.BeanUtils;

/** Entity utils. */
public class EntityUtils {

  private EntityUtils() {
    throw new IllegalStateException("Utility class");
  }

  public static <T extends Versionable> void copyVersionableEntityProperties(T input, T entity) {
    if (entity.getVersion() != input.getVersion()) {
      throw new OptimisticLockException("Entity and input version does not match");
    }
    BeanUtils.copyProperties(input, entity, "id", "version");
  }
}
