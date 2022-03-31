package duke.commands;

import duke.data.entity.Task;
import duke.data.entity.TaskList;
import duke.data.exception.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;

import static duke.common.Messages.*;

public class Command {

    private Commands commands;
    private Task task;
    private int index;

    public void setIndex(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public void setCommands(Commands commands) {
        this.commands = commands;
    }

    public Commands getCommands() {
        return commands;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Task getTask() {
        return task;
    }

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
                storage.save( add(tasks, ui).getTasks());
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

    public TaskList add(TaskList tasks, Ui ui) {
        if (task != null) {
            tasks.add(task);
        }
        ui.showAdded(task);
        ui.showTaskSize(tasks.getSize());
        return tasks;
    }


    public TaskList delete(TaskList tasks, Ui ui) throws DukeException {
        //Check if user key in invalid number
        if (index < 0 || index > tasks.getSize()) {
            throw new DukeException("☹ OOPS!!! There is no such task to delete.");
        }
        Task deleted = tasks.delete(index);
        ui.showDeleted(deleted);
        ui.showTaskSize(tasks.getSize());
        return tasks;
    }

    public TaskList mark(TaskList tasks, Ui ui) throws DukeException {
        //Check if user key in invalid number
        System.out.println("mark");
        if (index < 0 || index > tasks.getSize()) {
            throw new DukeException("☹ OOPS!!! The index that want to mark is not in the list.");
        }
        tasks.getTasks().get(index).setDone(true);
        ui.showMarked(tasks.getTasks().get(index));
        ui.showTaskSize(tasks.getSize());
        return tasks;
    }

    public TaskList unMark(TaskList tasks, Ui ui) throws DukeException {
        //Check if user key in invalid number
        System.out.println("unmark");
        if (index < 0 || index > tasks.getSize()) {
            throw new DukeException("☹ OOPS!!! The index that want to unmark is not in the list.");
        }
        Task toUnMark = tasks.getTasks().get(index);
        if (toUnMark.getStatusIcon().equals(" "))
            throw new DukeException("☹ OOPS!!! This task is not marked yet.");
        tasks.getTasks().get(index).setDone(false);
        ui.showUnMarked(tasks.getTasks().get(index));
        ui.showTaskSize(tasks.getSize());
        return tasks;
    }

    public void list(TaskList tasks, Ui ui) {
        if (tasks.getSize() == 0) {
            ui.showTaskSize(tasks.getSize());
        } else {
            ui.showTask(tasks);
        }
    }

    public boolean isExit() {
        if (this.commands != null) {
            if (commands.equals(Commands.BYE)) return true;
            else return false;
        } else return false;
    }
}