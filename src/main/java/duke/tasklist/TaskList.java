package duke.tasklist;

import java.util.ArrayList;
import java.util.List;

/**
 * This class stores List of Task and contains methods to access and modify them.
 */
public class TaskList {
    List<Task> taskList;

    /**
     * Constructor for TaskList
     *
     * @param tasks ArrayList of Tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.taskList = tasks;
    }

    /**
     * Constructor for TaskList
     */
    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    /**
     * Return taskList variable in TaskList class.
     *
     * @return taskList variable as List of Task.
     */
    public List<Task> getTaskList(){
        return this.taskList;
    }

    /**
     * Return size of taskList
     *
     * @return number of Tasks in taskList.
     */
    public int size(){
        return this.taskList.size();
    }

    /**
     * Return specific Task in taskList with index provided.
     *
     * @param index index of task to be accessed.
     * @return specific Task in taskList.
     */
    public Task get(int index){
        return this.taskList.get(index);
    }

    /**
     * Remove specific Task in taskList with index provided.
     *
     * @param index index of task to be removed.
     */
    public void delete(int index){
        this.taskList.remove(index);
    }

    /**
     * Add provided Task into taskList.
     *
     * @param task Task to be added into taskList.
     */
    public void add(Task task){
        this.taskList.add(task);
    }

    @Override
    public String toString() {
        String output = "";
        if (taskList.isEmpty()) {
            output = "Empty";
        }
        else {
            for (int index = 0; index < taskList.size(); index++) {
                int cur_index = index + 1;
                output = output + "\t\t\t\t\t\t\t\t\t "+ cur_index + ". " + taskList.get(index) + "\n";
            }
        }
        return output;
    }
}