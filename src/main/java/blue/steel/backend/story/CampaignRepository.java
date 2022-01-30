package blue.steel.backend.story;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/** Campaign JPA repository. */
@Repository
public interface CampaignRepository extends JpaRepository<Campaign, UUID> {

  Optional<Campaign> findByActual(boolean actual);
}
