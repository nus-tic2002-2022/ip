package duke.task;

import duke.parser.Parser;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void toStringTest() {

        String description = "Quiz 10";
        LocalDate date = LocalDate.parse("2022-04-06");
        Task deadline = new Deadline(description, date);
        assertEquals("[D][ ] Quiz 10 (by: 6 Apr 2022)", deadline.toString());
    }
}
