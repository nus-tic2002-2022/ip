package duke.task;

import duke.exception.DukeException;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Manage tasks operation
 */
public class TaskList {

    private static ArrayList<Task> tasks;

    public TaskList(){
        tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<String> tasks) throws DukeException, IOException {
        TaskList.tasks = new ArrayList<>();
        categoriseTaskToAdd(tasks);
    }

    /**
     * Add a new task to list
     * @param task specified task
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Delete a task from list
     * @param taskNumber identifier of task
     */
    public void deleteTask(int taskNumber) {
        tasks.remove(taskNumber);
    }

    /**
     * Delete all task from list
     */
    public void deleteAllTask() {
        tasks.removeAll(tasks);
    }

    /**
     * Set a task status as done
     * @param taskNumber identifier of task
     */
    public void markDone(int taskNumber) {
        tasks.get(taskNumber).setDone();
    }

    /**
     * Set a task status as not done
     * @param taskNumber identifier of task
     */
    public void unmarkDone(int taskNumber) {
        tasks.get(taskNumber).setUndone();
    }

    /**
     * Retrieve the list of tasks
     * @return list of tasks
     */
    public ArrayList<Task> getListOfSavedTask() {
        return tasks;
    }

    /**
     * Get the number of tasks in the list
     * @return size of the task list
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Get a task by its position in the list
     * @param index position of the task
     * @return a task
     */
    public Task getTaskByIndex(int index) {
        return tasks.get(index);
    }

    /**
     * Categorise tasks to add to task list
     * @param data task details in String
     * @throws DukeException for showing IO exception message
     */
    public void categoriseTaskToAdd(ArrayList<String> data) throws DukeException {

        for (String d : data) {
            String[] dSplit = d.split("//|");
            TaskType taskType = TaskType.stringToTaskType(dSplit[0].trim());
            Task task = null;

            switch(taskType) {
                case TODO:
                    task = new Todo();
                    task.populateTaskDetails(d);
                    break;
                case DEADLINE:
                    task = new Deadline();
                    task.populateTaskDetails(d);
                    break;
                case EVENT:
                    task = new Event();
                    task.populateTaskDetails(d);
                    break;
            }
            this.addTask(task);
        }
    }

    /**
     * Retrieve tasks by keyword from user
     * @param keyword words to match
     * @return matching tasks
     */
    public ArrayList<Task> findTaskByKeyword(String keyword) {
        ArrayList<Task> matchedTasks = new ArrayList<>();

        for (Task t : tasks) {
            boolean matchedDescription;
            boolean matchedBy = false;
            boolean matchedAt = false;

            matchedDescription = t.description.toLowerCase().contains(keyword.toLowerCase());
            TaskType taskType = t.taskType;

            switch (taskType) {
                case DEADLINE:
                    Deadline deadline = (Deadline) t;
                    matchedBy = deadline.getBy().toString().contains(keyword);
                    break;
                case EVENT:
                    Event event = (Event) t;
                    matchedAt = event.getAt().toString().contains(keyword);
                    break;
            }

            if (matchedDescription || matchedBy || matchedAt) {
                matchedTasks.add(t);
            }
        }

        return matchedTasks;

    }

    /**
     * Check a task has duplicate
     * @param task task to be added
     * @return true if duplicate found
     */
    public boolean checkHasDuplicate(Task task) {
        boolean hasDuplicate = false;

        for (int i = 0; i < tasks.size() && !hasDuplicate; i++) {
            boolean taskTypeMatched;
            boolean descriptionMatched;
            boolean byMatched = false;
            boolean atMatched = false;

            Task current = tasks.get(i);
            taskTypeMatched = current.taskType.equals(task.taskType);

            if (taskTypeMatched) {
                descriptionMatched = current.description.equalsIgnoreCase(task.description);
                TaskType taskType = task.taskType;

                switch (taskType) {
                    case DEADLINE:
                        Deadline d1 = (Deadline) tasks.get(i);
                        Deadline d2 = (Deadline) task;
                        byMatched = d1.getBy().toString().equals(d2.getBy().toString());
                        break;
                    case EVENT:
                        Event e1 = (Event) tasks.get(i);
                        Event e2 = (Event) task;
                        atMatched = e1.getAt().toString().equals(e2.getAt().toString());
                        break;
                }

                switch (taskType) {
                    case DEADLINE:
                        if (descriptionMatched && byMatched) { hasDuplicate = true; }
                        break;
                    case EVENT:
                        if (descriptionMatched && atMatched) { hasDuplicate = true; }
                        break;
                    case TODO:
                        if (descriptionMatched) { hasDuplicate = true; }
                        break;
                }
            }
        }

        return hasDuplicate;

    }

}
