package blue.steel.backend.core.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/** User JPA repository. */
@Repository
public interface UserRepository extends JpaRepository<User, String> {}
