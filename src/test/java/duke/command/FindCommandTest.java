package duke.command;

import duke.task.TaskListTemp;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FindCommandTest {
    private static final TaskListTemp taskListTemp = new TaskListTemp();

    @Test
    void run() {
        List<String> expected = List.of("Here are the matching tasks in your list:");
        List<String> actual = new FindCommand(taskListTemp).run(new String[]{"find", "ww"});
        assertEquals(expected, actual);
    }

}