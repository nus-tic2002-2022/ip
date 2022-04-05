package tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    private Todo todo = new Todo("test");

    @Test
    public void toString_validTodo_success() {
        assertEquals("[T][ ] test", todo.toString());
    }

    @Test
    public void getSaveFormat_validTodo_success() {
        assertEquals("T|0|test| ", todo.getSaveFormat());
    }
}