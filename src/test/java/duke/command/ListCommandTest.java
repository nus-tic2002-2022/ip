package duke.command;

import duke.task.TaskListTemp;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ListCommandTest {
    private static final TaskListTemp taskListTemp = new TaskListTemp();

    @Test
    void run() {
        List<String> expected = new ArrayList<>();
        expected.add("Here are the tasks in your list:");
        for (int i = 0; i < taskListTemp.size(); i++) {
            expected.add(i + 1 + "." + taskListTemp.get(i));
        }
        List<String> actual = new ListCommand(taskListTemp).run(new String[]{"list"});
        assertEquals(expected, actual);
    }
}