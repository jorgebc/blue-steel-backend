package blue.steel.backend.story.campaign.usecase;

import blue.steel.backend.story.campaign.persistence.Campaign;
import blue.steel.backend.story.campaign.persistence.CampaignRepository;
import java.util.List;
import java.util.UUID;
import javax.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/** Campaign query service. */
@Service
@AllArgsConstructor
public class CampaignQuery {

  private final CampaignRepository campaignRepository;

  public Campaign findById(UUID id) {
    return campaignRepository.findById(id).orElseThrow(EntityNotFoundException::new);
  }

  public Campaign findActualCampaign() {
    return campaignRepository.findByActual(true).orElseThrow(EntityNotFoundException::new);
  }

  public List<Campaign> findAll() {
    Pageable last = PageRequest.of(0, 20);
    return campaignRepository.findAll(last).stream().toList();
  }
}
