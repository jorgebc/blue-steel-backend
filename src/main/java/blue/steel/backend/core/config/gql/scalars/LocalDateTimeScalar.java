package blue.steel.backend.core.config.gql.scalars;

import graphql.language.StringValue;
import graphql.schema.Coercing;
import graphql.schema.CoercingParseLiteralException;
import graphql.schema.CoercingParseValueException;
import graphql.schema.CoercingSerializeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class LocalDateTimeScalar implements Coercing<LocalDateTime, String> {
  @Override
  public String serialize(Object dataFetcherResult) throws CoercingSerializeException {
    if (dataFetcherResult instanceof LocalDateTime localDateTime) {
      return localDateTime.format(DateTimeFormatter.ISO_DATE_TIME);
    } else {
      throw new CoercingSerializeException("Not a valid DateTime");
    }
  }

  @Override
  public LocalDateTime parseValue(Object input) throws CoercingParseValueException {
    return LocalDateTime.parse(input.toString(), DateTimeFormatter.ISO_DATE_TIME);
  }

  @Override
  public LocalDateTime parseLiteral(Object input) throws CoercingParseLiteralException {
    if (input instanceof StringValue stringValue) {
      return LocalDateTime.parse(stringValue.getValue(), DateTimeFormatter.ISO_DATE_TIME);
    }

    throw new CoercingParseLiteralException("Value is not a valid ISO date time");
  }
}
