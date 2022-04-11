package duke.task;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventsTest {

    @Test
    public void eventsStringToSaveFileTest() {
        Events testEvents =new Events("test task", "2022-04-05");
        String expectedOutputString = "E|0|test task|2022-04-05";

        assertEquals(testEvents.taskToSaveFile(),expectedOutputString);

        testEvents.setDone(true);
        expectedOutputString = "E|1|test task|2022-04-05";

        assertEquals(testEvents.taskToSaveFile(),expectedOutputString);
    }
}
