package blue.steel.backend.story;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Data
@Entity
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
