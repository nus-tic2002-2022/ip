package tasks;

import java.util.ArrayList;

/** A TaskList Object that holds tasks */
public class TaskList {
    private final ArrayList<Task> taskList;

    /** Constructor for the TaskList Class */
    public TaskList() {
        taskList = new ArrayList<>();
    }

    /** Constructor for the TaskList Class with a loaded task list */
    public TaskList(TaskList loadedTaskList) {
        this.taskList = loadedTaskList.taskList;

    }

    public int getNumberOfTask() {
        return taskList.size();
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public void deleteTask(int index) throws IndexOutOfBoundsException {
        taskList.remove(index);
    }

    public TaskList getSearchResults(String searchTerm) {
        TaskList tempList = new TaskList();
        for (Task task: this.taskList) {
            if (task.getDescription().toLowerCase().contains(searchTerm.toLowerCase())) {
                tempList.addTask(task);
            }
        }
        return tempList;
    }

    public Task get(int index) throws IndexOutOfBoundsException {
        try {
            return taskList.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw e;
        }
    }

    public ArrayList<Task> getAllTask() {
        return taskList;
    }

}
