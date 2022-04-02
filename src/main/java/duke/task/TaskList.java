package duke.task;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.ArrayList;
import java.util.List;

public class TaskList {

    public static int taskCount = 0;
    private static ArrayList<Task> tasks;

    public TaskList(){
        tasks = new ArrayList<>();
    }

    public TaskList(TaskList tasks) {}

    private void addToList(Task t) {
        tasks.add(t);
        taskCount++;
    }

    public void addTask(Task task) {
        addToList(task);
    }

    public void deleteTask(int taskNumber) {
        tasks.remove(taskNumber);
        taskCount--;
    }

    public void markDone(int taskNumber) {
        tasks.get(taskNumber).setDone();
    }

    public void unmarkDone(int taskNumber) {
        tasks.get(taskNumber).setUndone();
    }

    public ArrayList<Task> getListOfSavedTask() {
        return tasks;
    }
}
