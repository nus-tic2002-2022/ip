import Task.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void TodoTest() {
        TaskList taskList = new TaskList();
        taskList.createTodoTask("todo read book");
    }

    @Test
    public void DeadlineTest() throws DukeException {
        TaskList taskList = new TaskList();
        taskList.createDeadlineTask("return book ", "/by 2022-04-10 12:00");
    }
    @Test
    public void EventTest() throws DukeException {
        TaskList taskList = new TaskList();
        taskList.createEventTask("event walk the plank ", "/at 2022-03-21 23:14");
    }

    @Test
    public void DeleteTest() throws DukeException, IOException {
        TaskList taskList = new TaskList();
        taskList.createTodoTask("todo read book");
        taskList.deleteTask("delete 1");
    }

    @Test
    public void MarkDoneNotDoneTest() throws DukeException, IOException {
        TaskList taskList = new TaskList();
        taskList.createTodoTask("todo read book");
        taskList.markTaskAsDone("mark 1");
        taskList.markTaskAsNotDone("unmark 1");
    }

    @Test
    public void TagTest() throws DukeException, IOException {
        TaskList taskList = new TaskList();
        taskList.createTodoTask("todo read book");
        taskList.markTaskAsDone("tag 1 fun");
        taskList.markTaskAsNotDone("untag 1");
    }
    @Test
    public void FindTest() throws DukeException, IOException {
        TaskList taskList = new TaskList();
        taskList.createTodoTask("todo read book");
        taskList.findTask("find book");
    }
}