package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void toStringTest() {
        String description = "unit test";
        Task todo = new Todo(description);
        assertEquals("[T][ ] unit test", todo.toString());
    }
}
