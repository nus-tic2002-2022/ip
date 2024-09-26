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
    void run() throws IOException {//[Nice! I've removed this task as done: 4]
        String[] removeString = new Task("remove 3").toString().split(" ");
        List<String> expected = List.of("Noted. I've removed this task: " +
                removeString[2]);
        List<String> actual = new DeleteCommand(taskListTemp, storageTemp).run(
                new String[]{"delete", "3"});
        assertEquals(expected, actual);
    }
}