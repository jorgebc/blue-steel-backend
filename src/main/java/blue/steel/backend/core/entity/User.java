package blue.steel.backend.core.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/** Campaign JPA entity. */
@Entity
@Getter
@Setter
public class User {
  @Id private String id;
  @NotNull private String userName;
}
