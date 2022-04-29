package zhixuan.duke.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

/**
 * Parser for date format
 *
 **/
public class DateParser {

    /**
     * Parse string to LocalDateTime
     * Parse date and/or time provided
     *
     * @param date string date and/or time
     *
     * @return converted LocalDateTime in yyyy-MM-dd HH:mm
     **/
    public static LocalDateTime parseStringToDateTime(String date) {
        DateTimeFormatter dateFormat = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd")
                .optionalStart()
                .appendPattern(" HH:mm")
                .optionalEnd()
                .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                .toFormatter();

        return LocalDateTime.parse(date, dateFormat);
    }

    /**
     * Compare LocalDateTime dates
     * Reasoning: LocalDateTime does not have any date comparators
     *
     * @param firstDate first date to compare
     * @param secondDate second date to compare
     *
     * @return true if same dates, else false
     **/
    public static boolean compareDateTime(LocalDateTime firstDate, LocalDateTime secondDate) {
        if (firstDate.getYear() != secondDate.getYear()) {
            return false;
        }
        return firstDate.getDayOfYear() == secondDate.getDayOfYear();
    }

    /**
     * Parse LocalDateTime to string to be displayed
     *
     * When saving LocalDateTime as toString, Java automatically adds character 'T'
     * between date and time. .replace to replace the 'T'
     *
     * @param dateTime LocalDateTime to be parsed
     *
     * @return display format for Event
     **/
    public static String parseDateToDisplayString(LocalDateTime dateTime) {
        DateTimeFormatter displayFormat = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");

        return dateTime.format(displayFormat).replace("T", " ");
    }

    /**
     * Parse LocalDateTime to string to be saved to file
     *
     * When saving LocalDateTime as toString, Java automatically adds character 'T'
     * between date and time. .replace to replace the 'T'
     *
     * @param dateTime LocalDateTime to be parsed
     *
     * @return display format for Event
     **/
    public static String parseDateToSaveString(LocalDateTime dateTime) {
        return dateTime.toString().replace("T", " ");
    }

}
