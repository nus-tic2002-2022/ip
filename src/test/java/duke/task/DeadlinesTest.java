package duke.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class DeadlinesTest {

    @Test
    public void deadLinesStringToSaveFileTest() {
        Deadlines testDeadline =new Deadlines("test task", "2022-04-05");
        String expectedOutputString = "D|1|test task|2022-04-05";

        assertEquals(testDeadline.taskToSaveFile(),expectedOutputString);

        testDeadline.setDone(true);
        expectedOutputString = "D|1|test task|2022-04-05";

        assertEquals(testDeadline.taskToSaveFile(),expectedOutputString);

    }
}
