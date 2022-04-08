import duke.Task;
import duke.TaskHandler;
import duke.TaskList;
import duke.Todo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

class TaskHandlerTest {
    @Test
    public void TaskTestBye(){
        TaskHandler th = new TaskHandler();
        Assertions.assertEquals("Bye. Hope to see you again soon!\n", th.bye());
    }
    @Test
    public void TaskTestGreeting() {
        TaskHandler th = new TaskHandler();
        Assertions.assertEquals("Hello! I'm Duke, What can I do for you?", th.hello());
    }
    @Test
    public void TaskTestTodo() {
        TaskHandler th = new TaskHandler();
        TaskList tl = new TaskList();
        Task t1 = new Task("borrow book");
        tl.add(t1);
        Assertions.assertEquals("Got it, I've added this task:\n" +
                "  [ ] borrow book\n" +
                "Now you have 1 tasks in the list.\n", th.printTask(t1));
    }
}
