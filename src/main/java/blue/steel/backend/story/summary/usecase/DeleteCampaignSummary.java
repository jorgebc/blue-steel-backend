package blue.steel.backend.story.summary.usecase;

import blue.steel.backend.core.usecase.UseCase;
import blue.steel.backend.story.summary.persistence.SummaryRepository;
import blue.steel.backend.story.summary.usecase.dto.DeleteSummaryUseCaseInput;
import blue.steel.backend.story.summary.usecase.dto.DeleteSummaryUseCaseOutput;
import java.util.UUID;
import javax.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/** Delete campaign summary use case. */
@Service
@Transactional
@AllArgsConstructor
public class DeleteCampaignSummary
    implements UseCase<DeleteSummaryUseCaseInput, DeleteSummaryUseCaseOutput> {

  private final SummaryRepository summaryRepository;

  @Override
  public DeleteSummaryUseCaseOutput execute(DeleteSummaryUseCaseInput input) {
    UUID summaryId = input.getSummaryId();
    summaryRepository.deleteById(summaryId);
    return DeleteSummaryUseCaseOutput.builder().summaryId(summaryId).build();
  }
}
