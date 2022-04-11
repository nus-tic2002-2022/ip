package duke.task;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class ScheduleTest {

    @Test
    public void scheduleStringToSaveFileTest() {
        Schedule testSchedule =new Schedule("test task", "2022-04-05","2022-04-07");
        String expectedOutputString = "S|0|test task|2022-04-05|2022-04-07";

        assertEquals(testSchedule.taskToSaveFile(),expectedOutputString);

        testSchedule.setDone(true);
        expectedOutputString = "S|1|test task|2022-04-05|2022-04-07";

        assertEquals(testSchedule.taskToSaveFile(),expectedOutputString);
    }
}
