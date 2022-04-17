import java.util.Arrays;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.LocalDate;


  //  public static LocalDate parse​(CharSequence text)
// parses dates using using DateTimeFormatter.ISO_LOCAL_DATE

  //  public static LocalDate parse​(CharSequence text, DateTimeFormatter formatter)
// parses dates using the provided formatter

/*
public class DateValidatorUsingLocalDate implements DateValidator {
    private DateTimeFormatter dateFormatter;

    public DateValidatorUsingLocalDate(DateTimeFormatter dateFormatter) {
        this.dateFormatter = dateFormatter;
    }

    @Override
    public boolean isValid(String dateStr) {
        try {
            LocalDate.parse(dateStr, this.dateFormatter);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }
}*/