package blue.steel.backend.core.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/** User JPA repository. */
@Repository
public interface UserRepository extends JpaRepository<Users, String> {}
