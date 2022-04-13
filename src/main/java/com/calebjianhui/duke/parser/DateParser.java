package com.calebjianhui.duke.parser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.calebjianhui.duke.common.Pair;
import com.calebjianhui.duke.enums.TaskDateStructure;

/**
 * A parser that handles date input
 */
public class DateParser {
    private static final String OUTPUT_STRING_FORMAT = "dd/MM/yyyy hh.mm a";
    private static final List<String> datetimeFormats = Arrays.asList(
            "dd/MM/yyyy HHmm", "dd/MM/yyyy HH:mm", "dd/MM/yyyy hh.mma", "dd/MM/yyyy hh.mm a", "dd/MM/yyyy hha",
            "dd/MM/yyyy hh a", "dd/MM/yyyy",
            "dd-MM-yyyy HHmm", "dd-MM-yyyy HH:mm", "dd-MM-yyyy hh.mma", "dd-MM-yyyy hh.mm a", "dd-MM-yyyy hha",
            "dd-MM-yyyy hh a", "dd-MM-yyyy"
    );

    /**
     * Parse a given string to determine if there is a specific datetime specified
     *
     * @param input String input
     * @return Return a pair of &lt;T, U&gt;<br />
     * &nbsp;&nbsp;&nbsp;&nbsp;T: TaskDateStructure - Structure of the given input<br />
     * &nbsp;&nbsp;&nbsp;&nbsp;U: LocalDateTime - Datetime of the task
     */
    public static Pair<TaskDateStructure, LocalDateTime> parseDateTimeString(String input) {
        for (String formatString: datetimeFormats) {
            try {
                Date formattedDate = new SimpleDateFormat(formatString).parse(input);
                LocalDateTime convertedDateTime = LocalDateTime.ofInstant(formattedDate.toInstant(),
                        ZoneId.of("Asia/Singapore"));
                return new Pair<>(TaskDateStructure.VALID_DATE, convertedDateTime);
            } catch (ParseException ignore) {
                continue;
            }
        }
        return new Pair<>(TaskDateStructure.UNSTRUCTURED_DATE_STRING, null);
    }

    /**
     * Return a formatted string from a given LocalDateTime input
     *
     * @param input LocalDateTime input
     * @return Return a formatted String based on the given input
     */
    public static String convertToDateString(LocalDateTime input) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(OUTPUT_STRING_FORMAT);
        return input.format(formatter);
    }

}
