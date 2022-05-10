package blue.steel.backend.story.summary.persistence;

import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/** Campaign summary JPA repository. */
@Repository
public interface SummaryRepository extends PagingAndSortingRepository<Summary, UUID> {
  List<Summary> findByOrderByAuditingMetadataLastModifiedDateDesc(Pageable pageable);
}
