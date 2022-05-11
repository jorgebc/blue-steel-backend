package blue.steel.backend.story.campaign.persistence;

import blue.steel.backend.core.persistence.Auditable;
import blue.steel.backend.core.persistence.Versionable;
import blue.steel.backend.story.summary.persistence.Summary;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** Campaign JPA entity. */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Campaign extends Auditable implements Versionable {

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
      cascade = CascadeType.ALL,
      orphanRemoval = true,
      fetch = FetchType.LAZY)
  private Set<Summary> summaries = new HashSet<>();
}
