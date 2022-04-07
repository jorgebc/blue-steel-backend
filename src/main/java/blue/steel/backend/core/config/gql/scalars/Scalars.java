package blue.steel.backend.core.config.gql.scalars;

import graphql.schema.GraphQLScalarType;

/** Scalars. */
public class Scalars {

  private Scalars() {}

  /**
   * UUID scalar type.
   *
   * @return GraphQLScalarType
   */
  public static GraphQLScalarType uuidType() {
    return GraphQLScalarType.newScalar()
        .name("UUID")
        .description("UUID type")
        .coercing(new UUIDScalar())
        .build();
  }

  /**
   * LocalDate scalar type.
   *
   * @return GraphQLScalarType
   */
  public static GraphQLScalarType localDateType() {
    return GraphQLScalarType.newScalar()
        .name("LocalDate")
        .description("LocalDate type")
        .coercing(new LocalDateScalar())
        .build();
  }

  /**
   * LocalTime scalar type.
   *
   * @return GraphQLScalarType
   */
  public static GraphQLScalarType localDateTimeType() {
    return GraphQLScalarType.newScalar()
        .name("LocalDateTime")
        .description("LocalDateTime type")
        .coercing(new LocalDateTimeScalar())
        .build();
  }
}
