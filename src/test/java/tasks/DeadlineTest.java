package tasks;

import java.util.Date;

import exceptions.TooManyDatesException;
import org.junit.jupiter.api.BeforeAll;
import parser.DateParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    private static Date date;
    private static Deadline deadline;

    @BeforeAll
    static public void DeadlineTest() {
        try{
            date = DateParser.parseDate("jan 1 12:00am");
        } catch (TooManyDatesException e) {
            e.printStackTrace();
        }
        deadline = new Deadline("test", date);
    }

    @Test
    public void toString_validDeadline_success() {
        assertEquals("[D][ ] test (by: Jan 01)", deadline.toString());
    }

    @Test
    public void getSaveFormat_validDeadline_success() {
        assertEquals("D|0|test|Sat Jan 01 00:00:00 SGT 2022", deadline.getSaveFormat());
    }

    @Test
    public void printDate_validDeadline_success() {
        assertEquals("Jan 01", deadline.printDate(date));
    }
}
