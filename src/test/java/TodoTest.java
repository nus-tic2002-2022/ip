import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This method takes in 1 parameter and create the task.
 * @param description Description of the task
 */


public class TodoTest {
    @Test
    public void TodoTest(){
        Todo todo = new Todo("Todo read book (JUnit)","Todo read book (JUnit)");
        String TodoExpected = "Todo read book (JUnit)";
        assertEquals(TodoExpected, todo.description);
    }
}