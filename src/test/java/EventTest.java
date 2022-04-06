import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void EventTest(){
        Event event = new Event("Event Project Meeting (JUnit)", "23/04/2023 1250");

        //Create Date and Time Variables
        DateTimeFormatter Standard = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        String UserInput = event.at;
        LocalDateTime UserInputDT = LocalDateTime.parse(UserInput, Standard);

        String Format = UserInputDT.format(DateTimeFormatter.ofPattern("d MMM yyyy hh:mm"));

        String EventExpected = "23 Apr 2023 12:50PM";
        assertEquals(EventExpected, Format);
    }
}