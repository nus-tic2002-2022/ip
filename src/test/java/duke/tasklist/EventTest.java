package duke.tasklist;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void EventTest(){
        String expectedResult = "[E]\t[ ]\t\tread book\tat : 2022/02/12 10:00 AM";
        String input1 = "read book";

        String input2 = "12-02-2022 1000";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        LocalDateTime formattedInput2 = LocalDateTime.parse(input2, formatter);

        Event newTask = new Event(input1, formattedInput2);
        assertEquals(newTask.toString(), expectedResult);
    }
}
