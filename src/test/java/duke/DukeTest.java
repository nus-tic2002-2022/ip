package duke;

import duke.task.Task;
import duke.task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {
    @Test
    public void parseTodoInputTest() {
        String testInput = "todo borrow book";

        UserInput input = new UserInput();
        input.parseInput(testInput.split(" "));

        assertEquals(input.category, UserInput.Category.TODO);
        assertEquals(input.item.toString(), "borrow book ");
    }

    @Test
    public void createTodoTest() {
        Integer id = 0;
        String todoDesc = "test todo";

        Task t = new Task(id, todoDesc);
        Todo td = new Todo(t);

        System.out.println(td);

        assertEquals(td.getId(), 0);
        assertEquals(td.getDesc(), "test todo");
    }
}
