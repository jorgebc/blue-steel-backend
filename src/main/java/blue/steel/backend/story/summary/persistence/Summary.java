package blue.steel.backend.story.summary.persistence;

import blue.steel.backend.core.persistence.AuditMetadata;
import blue.steel.backend.core.persistence.Versionable;
import blue.steel.backend.story.campaign.persistence.Campaign;
import java.time.LocalDate;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/** Campaign summary JPA entity. */
@Entity
@Getter
@Setter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Summary implements Versionable {

  @Version private Integer version;

  @Id
  @Column(length = 16)
  @GeneratedValue(generator = "UUID")
  private UUID id;

  @NotNull private String name;

  @NotNull
  @Column(columnDefinition = "TEXT")
  private String description;

  @NotNull private LocalDate gameDate;

  @ManyToOne(fetch = FetchType.LAZY)
  private Campaign campaign;

  @Embedded private AuditMetadata auditingMetadata = new AuditMetadata();
}
