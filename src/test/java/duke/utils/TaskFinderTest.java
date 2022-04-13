//package duke.utils;
import duke.task.*;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import duke.utils.TaskFinder;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskFinderTest {
    ArrayList<Task> tasks = new ArrayList<Task>();
    ArrayList<Task> resultTasks = new ArrayList<Task>();

    @Test
    public void testTaskFinder() {
        TaskFinder tf = new TaskFinder();
        tasks.add(new ToDos("makan"));
        tasks.add(new Events("tzekiii","2022-04-15"));
        tasks.add(new Deadlines("sleep","2022-05-15"));

        resultTasks.add(new ToDos("makan"));
        System.out.println(resultTasks.size());

        assertEquals(resultTasks, tf.findTask(tasks, "makan"));
    }
}
