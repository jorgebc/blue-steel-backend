package blue.steel.backend.activity.usecase;

import blue.steel.backend.activity.usecase.dto.Activity;
import blue.steel.backend.story.campaign.persistence.Campaign;
import blue.steel.backend.story.campaign.persistence.CampaignRepository;
import blue.steel.backend.story.summary.persistence.Summary;
import blue.steel.backend.story.summary.persistence.SummaryRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/** Activity query service. */
@Service
@AllArgsConstructor
public class ActivityQuery {

  private static final int LAST_ACTIVITY_LIMIT = 10;

  private CampaignRepository campaignRepository;
  private SummaryRepository summaryRepository;

  /**
   * Get last modified elements (Campaigns, Summaries, ...).
   *
   * @return Last modified elements
   */
  public List<Activity> getLastActivities() {

    Pageable last = PageRequest.of(0, LAST_ACTIVITY_LIMIT);
    List<Campaign> campaigns =
        campaignRepository.findByOrderByAuditingMetadataLastModifiedDateDesc(last);
    List<Summary> summaries =
        summaryRepository.findByOrderByAuditingMetadataLastModifiedDateDesc(last);

    return ActivityMapper.getLastTenLastModified(LAST_ACTIVITY_LIMIT, campaigns, summaries);
  }
}
