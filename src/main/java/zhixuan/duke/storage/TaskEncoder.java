package zhixuan.duke.storage;

import zhixuan.duke.data.task.Task;

import java.util.ArrayList;

public class TaskEncoder {

    public static ArrayList<String> encodeTaskList(ArrayList<Task> taskList) {
        ArrayList<String> taskStringList = new ArrayList<>();
        for (Task task: taskList) {
            taskStringList.add(task.toFile());
        }
        return taskStringList;
    }

}
