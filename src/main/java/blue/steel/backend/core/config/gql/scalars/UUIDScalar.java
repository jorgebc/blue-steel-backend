package blue.steel.backend.core.config.gql.scalars;

import graphql.language.StringValue;
import graphql.schema.Coercing;
import graphql.schema.CoercingParseLiteralException;
import graphql.schema.CoercingParseValueException;
import graphql.schema.CoercingSerializeException;
import java.util.UUID;

@SuppressWarnings("checkstyle:AbbreviationAsWordInName")
class UUIDScalar implements Coercing<UUID, String> {
  @Override
  public String serialize(Object o) throws CoercingSerializeException {
    if (o instanceof UUID uuid) {
      return uuid.toString();
    } else {
      throw new CoercingSerializeException("Not a valid UUID");
    }
  }

  @Override
  public UUID parseValue(Object o) throws CoercingParseValueException {
    return UUID.fromString(o.toString());
  }

  @Override
  public UUID parseLiteral(Object input) throws CoercingParseLiteralException {
    if (input instanceof StringValue stringValue) {
      return UUID.fromString(stringValue.getValue());
    }

    throw new CoercingParseLiteralException("Value is not a valid UUID string");
  }
}
