import duke.task.ToDos;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDosTest {
    private ToDos todo = new ToDos ("lala");
    @Test
    public void testToDos() {

        assertEquals("lala", todo.getTask());
    }
}
