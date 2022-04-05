package blue.steel.backend.story.campaign.persistence;

import blue.steel.backend.core.persistence.AuditMetadata;
import blue.steel.backend.core.persistence.Versionable;
import blue.steel.backend.story.summary.persistence.Summary;
import java.util.List;
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
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
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
  @Fetch(FetchMode.JOIN)
  private List<Summary> summaries;

  @Embedded private AuditMetadata auditingMetadata = new AuditMetadata();
}
