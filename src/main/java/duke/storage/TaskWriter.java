package duke.storage;

import duke.data.entity.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * This class will write the data to tex file.
 */
public class TaskWriter {

    public static List<String> writeTaskList(List<Task> taskstoSave) {
        final List<String> taskList = new ArrayList<>();
        taskstoSave.forEach(task -> taskList.add(task.toSaveStr()));
        return taskList;
    }
}
