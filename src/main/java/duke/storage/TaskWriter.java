package duke.storage;

import java.util.ArrayList;
import java.util.List;

import duke.data.entity.Task;

public class TaskWriter {

    public static List<String> writeTaskList(List<Task> taskstoSave) {
        final List<String> taskList = new ArrayList<>();
        for(int i = 0 ; i<taskstoSave.size();i++){
            taskList.add(taskstoSave.get(i).toSaveStr());
            System.out.println(taskstoSave.get(i).toSaveStr());
        }
        //taskstoSave.forEach(task -> taskList.add(task.toSaveStr()));
        return taskList;
    }
}
