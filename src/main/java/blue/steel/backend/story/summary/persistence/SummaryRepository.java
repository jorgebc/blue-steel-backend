package blue.steel.backend.story.summary.persistence;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/** Campaign summary JPA repository. */
@Repository
public interface SummaryRepository extends JpaRepository<Summary, UUID> {}
