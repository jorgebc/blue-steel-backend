package blue.steel.backend.story.campaign.entity;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/** Campaign JPA entity. */
@Entity
@Getter
@Setter
public class Campaign {

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
}
