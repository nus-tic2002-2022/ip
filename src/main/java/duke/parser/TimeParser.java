package duke.parser;

import duke.utils.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.Date;

public class TimeParser extends Parser {

    public static String convertDateToString (LocalDate datetoString) {
        String formattedDateString = datetoString.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        return formattedDateString;
    }
}
