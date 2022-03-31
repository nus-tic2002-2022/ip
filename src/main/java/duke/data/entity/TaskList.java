package main.java.duke.data.entity;

import java.util.ArrayList;
import java.util.List;

public class TaskList {

    static List<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<Task>();
    }

    public TaskList(List<Task> load) {
        tasks = load;
    }

    public static List<Task> getTasks() {
        return tasks;
    }

    public List<String> getTasksString() {
        List<String> str = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i) != null)
                str.add((i + 1) + "." + tasks.get(i).toString());
        }
        return str;
    }

    public void add(Task tsk) {
        tasks.add(tsk);
    }

    public int getSize() {
        return tasks.size();
    }

    public Task delete(int index) {
        Task toDelete = tasks.get(index);
        tasks.remove(index);
        return toDelete;
    }


}
