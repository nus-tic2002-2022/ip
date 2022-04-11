package duke.command;


import duke.task.StorageTemp;
import duke.task.Task;

import duke.task.TaskListTemp;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeleteCommandTest {
    private static final TaskListTemp taskListTemp = new TaskListTemp();
    private static final StorageTemp storageTemp = new StorageTemp();

    @Test
    void run() throws IOException {
        String removeString = new Task("remove 3").toString();
        List<String> expected = List.of("Noted. I've removed this task: " + System.lineSeparator() + "     "
                + removeString);
        List<String> actual = new DeleteCommand(taskListTemp, storageTemp).run(
                new String[]{"delete", "4"});
        assertEquals(expected, actual);
        assertEquals(removeString, taskListTemp.doTask.toString());
        assertEquals(removeString, storageTemp.stored.get(0));
    }
}