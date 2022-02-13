package blue.steel.backend.story.summary.usecase;

import blue.steel.backend.core.usecase.UseCase;
import blue.steel.backend.story.summary.entity.Summary;
import blue.steel.backend.story.summary.entity.SummaryRepository;
import blue.steel.backend.story.summary.usecase.dto.CreateSummaryUseCaseInput;
import blue.steel.backend.story.summary.usecase.dto.CreateSummaryUseCaseOutput;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

/** Create campaign summary use case. */
@Service
@Transactional
public class CreateCampaignSummary
    implements UseCase<CreateSummaryUseCaseInput, CreateSummaryUseCaseOutput> {

  private final SummaryRepository summaryRepository;

  public CreateCampaignSummary(SummaryRepository summaryRepository) {
    this.summaryRepository = summaryRepository;
  }

  @Override
  public CreateSummaryUseCaseOutput execute(CreateSummaryUseCaseInput input) {
    Summary summary = input.getSummary();
    summary = summaryRepository.save(summary);
    return new CreateSummaryUseCaseOutput(summary);
  }
}
