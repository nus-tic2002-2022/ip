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

    public void printList() {
        for (Task task : this.getAllTasks()) {
            int id = task.getId();
            System.out.println(id + ": " + task);
        }
    }

    public Integer getNextId() {
        return tasks.size() + 1;
    }

    public void insertTask(Task task) {
        tasks.add(task);
    }

    public void insertTask(Task task, UserInput input) {
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

    public ArrayList<Task> getAllTasks() {

        return tasks;
    }

    public void init() {
        storage.load();
    }

    public Task getTask(int id) {
        for (Task task : this.getAllTasks()) {
            if (task.id == id) {
                return task;
            }
        }
        return null;
    }

    public void mark(UserInput input) {
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

    public void unmark(UserInput input) {
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

    public void update() {
        for (int i = 0; i < tasks.size(); i++) {
            tasks.get(i).setId(i + 1);
        }
    }

    public void delete(UserInput input) {
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

    public void deleteTask(Task task) {
        tasks.remove(task.getId() - 1);
    }
}
