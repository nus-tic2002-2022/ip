import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void EventTest(){
        Event event = new Event("Event Project Meeting (TBA2101)", "25/04/2022 1200");

        //Create Date and Time Variables
        DateTimeFormatter Standard = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        String UserInput = event.event;
        LocalDateTime UserInputDT = LocalDateTime.parse(UserInput, Standard);

        String Format = UserInputDT.format(DateTimeFormatter.ofPattern("d MMM yyyy hh:mma"));

        String EventExpected = "25 Apr 2022 12:00PM";
        assertEquals(EventExpected, Format);
    }
}