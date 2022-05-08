package blue.steel.backend.user.persistence;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/** Campaign JPA entity. */
@Entity
@Getter
@Setter
@Table(name = "users")
public class User {
  @Id private String id;
  @NotNull private String name;
}
