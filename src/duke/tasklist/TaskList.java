package duke.tasklist;

import duke.task.Task;

import java.util.List;

public class TaskList {

    protected List<Task> tasks;

    public TaskList() {
        this.tasks = tasks;
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
