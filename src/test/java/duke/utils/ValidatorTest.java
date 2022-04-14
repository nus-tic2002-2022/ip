package duke.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

class ValidatorTest {

    @Test
    void taskDescriptionValidatorTest(){
        String task = "todo";
        try {
            String description = task.substring(0, task.indexOf(' '));
        }catch (StringIndexOutOfBoundsException s) {
            Assertions.assertEquals("todo",task);
        }
    }

    @Test
    void taskDateValidatorTest(){
        String date = "/at 2004-04-04 0400";
        int delimiter = date.indexOf("/at");
        date = date.substring(delimiter+3).trim();
        Assertions.assertEquals("2004-04-04 0400", date);
    }

    @Test
    void taskDateFormatValidatorTest(){
        String date = "2004-04-04 0400";
        DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("uuuu-MM-dd HHmm").withResolverStyle(ResolverStyle.STRICT);
        LocalDateTime stringToDateTime = LocalDateTime.parse(date, DATE_TIME_FORMAT);
        String dateTimeToString = stringToDateTime.format(DATE_TIME_FORMAT);
        Assertions.assertEquals("2004-04-04 0400", dateTimeToString);
    }

}