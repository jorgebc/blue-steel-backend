package blue.steel.backend.story;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/** Campaign JPA entity. */
@Entity
@Getter
@Setter
@ToString
public class Campaign {

  @Id
  @Column(length = 16)
  @GeneratedValue(generator = "UUID")
  private UUID id;

  private String name;

  @Column(columnDefinition = "TEXT")
  private String description;

  private String imageUrl;
  private boolean actual;
}
