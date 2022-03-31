package main.java.duke.storage;

import main.java.duke.data.entity.Task;

import java.util.ArrayList;
import java.util.List;


public class TaskWriter {

    public static List<String> writeTaskList(List<Task> taskstoSave) {
        final List<String> taskList = new ArrayList<>();
        taskstoSave.forEach(task -> taskList.add(task.toSaveStr()));
        return taskList;
    }
}
