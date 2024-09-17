package duke.tasklist;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void DeadlineTest(){
        String expectedResult = "[D]\t[ ]\t\tread book\tby : 2022/12/12 06:00 PM";
        String input1 = "read book";

        String input2 = "12-12-2022 1800";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        LocalDateTime formattedInput2 = LocalDateTime.parse(input2, formatter);

        Deadline newTask = new Deadline(input1, formattedInput2);
        assertEquals(newTask.toString(), expectedResult);
    }
}
