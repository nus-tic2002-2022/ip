package main.java.duke.commands;


import main.java.duke.data.entity.Task;
import main.java.duke.data.entity.TaskList;
import main.java.duke.data.exception.DukeException;
import main.java.duke.storage.Storage;
import main.java.duke.ui.Ui;

import static main.java.duke.common.Messages.MESSAGE_ERROR;
import static main.java.duke.common.Messages.MESSAGE_GOODBYE;

/**
 * Represents an executable command.
 */
public class Command {

    private Commands commands;
    private Task task;
    private int index;

    public void setIndex(int index) {
        this.index = index;
    }

    public void setCommands(Commands commands) {
        this.commands = commands;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Task getTask() {
        return task;
    }
    /**
     * Executes the command and returns the result.
     */
    public TaskList execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (this.commands != null) switch (this.commands) {

            case BYE:
                ui.showToUser(MESSAGE_GOODBYE);
                break;
            case MARK:
                storage.save(mark(tasks, ui).getTasks());
                break;
            case UNMARK:
                storage.save(unMark(tasks, ui).getTasks());
                break;
            case TODO:
            case DEADLINE:
            case EVENT:
                storage.save(add(tasks, ui).getTasks());
                break;
            case DELETE:
                storage.save(delete(tasks, ui).getTasks());
                break;
            case LIST:
                list(tasks, ui);
                break;
            default:
                ui.showError(MESSAGE_ERROR);
        }
        else ui.showError(MESSAGE_ERROR);
        return tasks;
    }
    /**
     * Add the task into List.
     */
    public TaskList add(TaskList tasks, Ui ui) {
        if (task != null) {
            tasks.add(task);
        }
        ui.showAdded(task);
        ui.showTaskSize(tasks.getSize());
        return tasks;
    }
    /**
     * Delete the task from List.
     */
    public TaskList delete(TaskList tasks, Ui ui) throws DukeException {
        /**
         * Check if user key in invalid number
         */
        if (index < 0 || index > tasks.getSize()) {
            throw new DukeException("☹ OOPS!!! There is no such task to delete.");
        }
        Task deleted = tasks.delete(index);
        ui.showDeleted(deleted);
        ui.showTaskSize(tasks.getSize());
        return tasks;
    }
    /**
     * Mark the task from List.
     */
    public TaskList mark(TaskList tasks, Ui ui) throws DukeException {
        /**
         * Check if user key in invalid number and show error
         */
        if (index < 0 || index > tasks.getSize()) {
            throw new DukeException("☹ OOPS!!! The index that want to mark is not in the list.");
        }
        TaskList.getTasks().get(index).markAsDone();
        ui.showMarked(TaskList.getTasks().get(index));
        ui.showTaskSize(tasks.getSize());
        return tasks;
    }
    /**
     * Unmark the task from List.
     */
    public TaskList unMark(TaskList tasks, Ui ui) throws DukeException {
        /**
         * Check if user key in invalid number
         */
        if (index < 0 || index > tasks.getSize()) {
            throw new DukeException("☹ OOPS!!! The index that want to unmark is not in the list.");
        }
        Task toUnMark = TaskList.getTasks().get(index);
        if (toUnMark.getStatusIcon().equals(" "))
            throw new DukeException("☹ OOPS!!! This task is not marked yet.");
        TaskList.getTasks().get(index).markAsNotDone();
        ui.showUnMarked(TaskList.getTasks().get(index));
        ui.showTaskSize(tasks.getSize());
        return tasks;
    }
    /**
     * Show the task list.
     */
    public void list(TaskList tasks, Ui ui) {
        if (tasks.getSize() == 0) {
            ui.showTaskSize(tasks.getSize());
        } else {
            ui.showTask(tasks);
        }
    }
    /**
     * Exit the program when user type 'Bye'.
     */
    public boolean isExit() {
        if (this.commands != null) {
            if (commands.equals(Commands.BYE)) return true;
            else return false;
        } else return false;
    }
}