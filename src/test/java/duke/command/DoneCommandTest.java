package duke.command;


import duke.task.StorageTemp;
import duke.task.TaskListTemp;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DoneCommandTest {
    private static final TaskListTemp taskListTemp = new TaskListTemp();
    private static final StorageTemp storageTemp = new StorageTemp();

    @Test
    void run() throws IOException {
        List<String> expected = List.of("Nice! I've marked this task as done: " + System.lineSeparator()
                + "     " + "[x]get 2");
        List<String> actual = new DoneCommand(taskListTemp, storageTemp).run(new String[]{"done", "3"});
        assertEquals(expected, actual);
    }
}