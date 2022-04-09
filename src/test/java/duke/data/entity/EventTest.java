package duke.data.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    @Test
    public void toStringTest() {
        String expectedResult = "[E][ ] Expo 2022  (at: 10/10/2022 09:00)";
        String input1 = "Expo 2022";
        String input2 = "10/10/2022 9:00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy H:m");
        Task t = new Event(input1, LocalDateTime.parse(input2, formatter));
        assertEquals(t.toString(), expectedResult);
    }


    @Test
    public void toSaveStrTest() {
        String expectedResult = "E , 0 , Expo 2022 , 10/10/2022 09:00";
        String input1 = "Expo 2022";
        String input2 = "10/10/2022 9:00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy H:m");
        Task t = new Event(input1, LocalDateTime.parse(input2, formatter));
        assertEquals(t.toSaveStr(), expectedResult);
    }


}