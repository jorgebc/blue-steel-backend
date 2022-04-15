package blue.steel.backend.core.config;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.Formatter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/** WebMvcConfig. */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

  /**
   * Formatter for LocalDate.
   *
   * @return Formatter
   */
  @Bean
  public Formatter<LocalDate> localDateFormatter() {
    return new Formatter<>() {
      @Override
      public LocalDate parse(String text, Locale locale) {
        return LocalDate.parse(text, DateTimeFormatter.ISO_DATE);
      }

      @Override
      public String print(LocalDate object, Locale locale) {
        return DateTimeFormatter.ISO_DATE.format(object);
      }
    };
  }

  /**
   * Formatter for LocalDateTime.
   *
   * @return Formatter
   */
  @Bean
  public Formatter<LocalDateTime> localDateTimeFormatter() {
    return new Formatter<>() {

      @Override
      public String print(LocalDateTime object, Locale locale) {
        return DateTimeFormatter.ISO_DATE_TIME.format(object);
      }

      @Override
      public LocalDateTime parse(String text, Locale locale) {
        return LocalDateTime.parse(text, DateTimeFormatter.ISO_DATE_TIME);
      }
    };
  }
}
