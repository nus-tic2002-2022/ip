package duke;

import duke.task.Deadline;
import duke.task.Events;
import duke.task.Task;
import duke.task.Todo;

import java.util.ArrayList;

public class TaskList {
    static ArrayList<Task> tasks = new ArrayList<>();
    static TaskStorage storage = new TaskStorage();

    public TaskList() {
    }

    /**
     * Print all tasks
     */
    protected void printList() {
        for (Task task : this.getAllTasks()) {
            int id = task.getId();
            System.out.println(id + ": " + task);
        }
    }

    /**
     * Get next id by using the total number of tasks + 1
     *
     * @return the integer value for the new task
     */
    protected Integer getNextId() {
        return tasks.size() + 1;
    }

    /**
     * Insert task directly into the arraylist of tasks
     * Used for loaded task
     *
     * @param task that would be inserted
     */
    protected void insertTask(Task task) {
        tasks.add(task);
    }

    /**
     * Insert task directly into the arraylist of tasks
     * Used for new task created by user
     *
     * @param task  that would be inserted
     * @param input parsed user input
     */
    protected void insertTask(Task task, UserInput input) {
        if (input.category == UserInput.Category.DEADLINE) {
            tasks.add(new Deadline(task, input.day));
        } else if (input.category == UserInput.Category.EVENT) {
            tasks.add(new Events(task, input.day, input.time));
        } else if (input.category == UserInput.Category.TODO) {
            tasks.add(new Todo(task));
        }
        printList();
        storage.save(tasks);
    }

    /**
     * Return all tasks in the arraylist
     *
     * @return all tasks
     */
    protected ArrayList<Task> getAllTasks() {
        return tasks;
    }

    /**
     * Load task list from storage
     */
    protected void init() {
        storage.load();
    }

    /**
     * Get task from task id
     *
     * @param id of task
     * @return a single task object
     */
    protected Task getTask(int id) {
        for (Task task : this.getAllTasks()) {
            if (task.id == id) {
                return task;
            }
        }
        return null;
    }

    /**
     * Mark task object
     *
     * @param input of user for which task to mark
     */
    protected void mark(UserInput input) {
        int index = 0;
        if (input.item != null && !input.item.toString().equals("")) {
            index = Integer.parseInt(input.item.toString());
        }
        Task task = getTask(index);
        if (task != null) {
            task.markAsDone();
        }
        storage.save(tasks);
        printList();
    }

    /**
     * Unmark task object
     *
     * @param input of user for which task to mark
     */
    protected void unmark(UserInput input) {
        int index = 0;
        if (input.item != null) {
            index = Integer.parseInt(input.item.toString());
        }
        Task task = getTask(index);
        if (task != null) {
            task.markAsUnDone();
        }
        storage.save(tasks);
        printList();
    }

    /**
     * Update all the tasks id after changes has been made to the arraylist
     * Used after deletion of arraylist
     */
    protected void update() {
        for (int i = 0; i < tasks.size(); i++) {
            tasks.get(i).setId(i + 1);
        }
    }

    /**
     * Delete task based on user input
     *
     * @param input of user to indicate which object to delete
     */
    protected void delete(UserInput input) {
        int index = 0;
        if (input.item != null) {
            index = Integer.parseInt(input.item.toString());
        }
        Task task = getTask(index);
        if (task != null) {
            deleteTask(task);
        }
        update();
        storage.save(tasks);
        printList();
    }

    /**
     * Removed task from arraylist
     *
     * @param task to be removed from the arraylist
     */
    private void deleteTask(Task task) {
        tasks.remove(task.getId() - 1);
    }
}
