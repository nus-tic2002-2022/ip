package duke.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void toStringTest() {

        String description = "Meeting";
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse("2022-04-16 13:00", format);
        Task event = new Event(description, dateTime);
        assertEquals("[E][ ] Meeting (at: 16 Apr 2022 1:00 PM)", event.toString());

    }
}
