package duke.data.entity;

import java.util.ArrayList;
import java.util.List;
/**
 * This class stores List of Tasks and methods to access and modify them.
 */
public class TaskList {

    static List<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(List<Task> load) {
        tasks = load;
    }
    /**
     * @return tasks in TaskList.
     */
    public static List<Task> getTasks() {
        return tasks;
    }
    /**
     * @return tasks in TaskList in string.
     */
    public List<String> getTasksString() {
        List<String> str = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i) != null)
                str.add((i + 1) + "." + tasks.get(i).toString());
        }
        return str;
    }
    /**
     * @param  tsk Task to add into TaskList.
     */
    public void add(Task tsk) {
        tasks.add(tsk);
    }
    /**
     * @return number of task in TaskList.
     */
    public int getSize() {
        return tasks.size();
    }
    /**
     * @return index to delete task from TaskList.
     */
    public Task delete(int index) {
        Task toDelete = tasks.get(index);
        tasks.remove(index);
        return toDelete;
    }


}
