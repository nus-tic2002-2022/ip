package duke.task;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDosTest {

    @Test
    public void todosStringToSaveFileTest() {
        ToDos testToDos =new ToDos("test task");
        String expectedOutputString = "T|0|test task";

        assertEquals(testToDos.taskToSaveFile(),expectedOutputString);

        testToDos.setDone(true);
        expectedOutputString = "T|1|test task";

        assertEquals(testToDos.taskToSaveFile(),expectedOutputString);
    }

    @Test
    public void todosStringToUiTest() {
        ToDos testToDos =new ToDos("test task");
        String expectedOutputString = "[T][ ] test task";

        assertEquals(testToDos.toString(),expectedOutputString);
    }
}
