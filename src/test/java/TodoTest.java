import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void TodoTest(){
        Todo todo = new Todo("Todo read book #2 (JUnit)");
        String TodoExpected = "Todo read book #2 (JUnit)";
        assertEquals(TodoExpected, todo.description);
    }
}