package blue.steel.backend.core.persistence;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/** Campaign JPA entity. */
@Entity
@Getter
@Setter
public class Users {
  @Id private String id;
  @NotNull private String userName;
}
