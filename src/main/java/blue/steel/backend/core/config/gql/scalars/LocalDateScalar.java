package blue.steel.backend.core.config.gql.scalars;

import graphql.language.StringValue;
import graphql.schema.Coercing;
import graphql.schema.CoercingParseLiteralException;
import graphql.schema.CoercingParseValueException;
import graphql.schema.CoercingSerializeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class LocalDateScalar implements Coercing<LocalDate, String> {
  @Override
  public String serialize(Object dataFetcherResult) throws CoercingSerializeException {
    if (dataFetcherResult instanceof LocalDate localDate) {
      return localDate.format(DateTimeFormatter.ISO_DATE);
    } else {
      throw new CoercingSerializeException("Not a valid Date");
    }
  }

  @Override
  public LocalDate parseValue(Object input) throws CoercingParseValueException {
    return LocalDate.parse(input.toString(), DateTimeFormatter.ISO_DATE);
  }

  @Override
  public LocalDate parseLiteral(Object input) throws CoercingParseLiteralException {
    if (input instanceof StringValue stringValue) {
      return LocalDate.parse(stringValue.getValue(), DateTimeFormatter.ISO_DATE);
    }

    throw new CoercingParseLiteralException("Value is not a valid ISO date");
  }
}
