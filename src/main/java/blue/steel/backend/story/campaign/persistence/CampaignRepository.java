package blue.steel.backend.story.campaign.persistence;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/** Campaign JPA repository. */
@Repository
public interface CampaignRepository extends PagingAndSortingRepository<Campaign, UUID> {

  Optional<Campaign> findByActual(boolean actual);

  List<Campaign> findByOrderByAuditingMetadataLastModifiedDateDesc(Pageable pageable);
}
