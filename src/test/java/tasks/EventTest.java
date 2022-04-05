package tasks;

import java.util.Date;

import exceptions.TooManyDatesException;
import org.junit.jupiter.api.BeforeAll;
import parser.DateParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    private static Date date;
    private static Event event;

    @BeforeAll
    static public void EventTest() {
        try{
            date = DateParser.parseDate("jan 1 12:00am");
        } catch (TooManyDatesException e) {
            e.printStackTrace();
        }
        event = new Event("test", date);
    }

    @Test
    public void toString_validDeadline_success() {
        assertEquals("[E][ ] test (at: Jan 01 0AM)", event.toString());
    }

    @Test
    public void getSaveFormat_validDeadline_success() {
        assertEquals("E|0|test|Sat Jan 01 00:00:00 SGT 2022", event.getSaveFormat());
    }

    @Test
    public void printDate_validDeadline_success() {
        assertEquals("Jan 01 0AM", event.printDate(date));
    }
}