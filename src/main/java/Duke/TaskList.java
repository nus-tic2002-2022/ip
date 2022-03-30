package duke;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private static List<Task> taskLists;

    public TaskList() {
        taskLists = new ArrayList<>();
    }
    public static List<Task> getTaskLists() {
        return taskLists;
    }
    public void add(Task task) {
        taskLists.add(task);
    }
}
