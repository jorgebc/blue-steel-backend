package blue.steel.backend.story.campaign.entity;

import blue.steel.backend.core.entity.AuditMetadata;
import blue.steel.backend.core.entity.Versionable;
import blue.steel.backend.story.summary.entity.Summary;
import java.util.Collection;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/** Campaign JPA entity. */
@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Campaign implements Versionable {

  @Version private Integer version;

  @Id
  @Column(length = 16)
  @GeneratedValue(generator = "UUID")
  private UUID id;

  @NotNull private String name;

  @NotNull
  @Column(columnDefinition = "TEXT")
  private String description;

  @NotNull private String imageUrl;
  private boolean actual;

  @OneToMany(
      mappedBy = "campaign",
      cascade = {CascadeType.ALL})
  private Collection<Summary> summaries;

  @Embedded private AuditMetadata auditingMetadata = new AuditMetadata();
}
