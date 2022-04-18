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
    public static LocalDateTime parseDate(String date) {
        DateTimeFormatter fmt = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd")
                .optionalStart()
                .appendPattern(" HH:mm")
                .optionalEnd()
                .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                .toFormatter();

        return LocalDateTime.parse(date, fmt);
    }


}
