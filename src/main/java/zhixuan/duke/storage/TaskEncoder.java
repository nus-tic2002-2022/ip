package zhixuan.duke.storage;

import zhixuan.duke.data.task.Task;
import java.util.ArrayList;

/**
 * Handler for encoding of task
 *
 **/
public class TaskEncoder {

    /**
     * Iterates through taskList and saves encoded task into new taskList
     *
     * @param taskList to iterate through
     *
     * @return taskList with encoded task
     **/
    public static ArrayList<String> encodeTaskList(ArrayList<Task> taskList) {
        ArrayList<String> taskStringList = new ArrayList<>();
        for (Task task: taskList) {
            taskStringList.add(task.toFile());
        }
        return taskStringList;
    }

}
