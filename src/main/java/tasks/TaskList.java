package tasks;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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

    public TaskList getSearchDateResults(Date date) {
        TaskList tempList = new TaskList();
        for (int i = 0; i < this.taskList.size(); i++) {
            if (taskList.get(i).getClass().equals(Deadline.class) || taskList.get(i).getClass().equals(Event.class)) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                if (sdf.format(date).equals(sdf.format(taskList.get(i).getDate()))) {
                    tempList.addTask(taskList.get(i));
                }
            }
        }
        return tempList;
    }

    public Task getTask(int index) throws IndexOutOfBoundsException {
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
