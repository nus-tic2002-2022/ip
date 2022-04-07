package commands;

import java.util.Date;

import storage.Storage;
import tasks.FixedDuration;
import tasks.TaskList;
import tasks.Todo;
import ui.UI;

/**
 * Updates a task's description or date depending on user input
 */
public class UpdateCommand extends Command {
    public static final String COMMAND_WORD = "update";

    public static final String MESSAGE_USAGE = "Updates the description or date of the task provided \n"
                                               + "usage: update <taskID> <date/desc> <new date/new description> \n\n"
                                               + "Eg. update 1 date tomorrow \n"
                                               + "Updates the date of the first task to tomorrow \n\n"
                                               + "Eg. update 1 desc sell book \n"
                                               + "Updates the description of the first task to sell book.";

    private final int index;
    private String newContent;
    private final String partToUpdate;
    private Date date;

    /**
     * Constructor for the Update Command Class
     *
     * @param index        index of the task to be updated
     * @param partToUpdate which part of the task you want updated: date or description
     * @param newContent   the new description to be updated
     */
    public UpdateCommand(int index, String partToUpdate, String newContent) {
        this.index = index;
        this.partToUpdate = partToUpdate;
        this.newContent = newContent;
    }

    /**
     * Constructor for the Update Command Class
     *
     * @param index        index of the task to be updated
     * @param partToUpdate which part of the task you want updated: date or description
     * @param date         the new date to be updated
     */
    public UpdateCommand(int index, String partToUpdate, Date date) {
        this.index = index;
        this.partToUpdate = partToUpdate;
        this.date = date;
    }


    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        try {
            if (partToUpdate.equalsIgnoreCase("desc")) {
                taskList.get(index - 1).updateDescription(newContent);
                ui.printUpdatedTask(taskList.get(index - 1).toString());
            } else if (taskList.get(index - 1).getClass().equals(Todo.class)) {
                ui.printTaskNotUpdated(taskList.get(index - 1).toString());
            } else if (taskList.get(index - 1).getClass().equals(FixedDuration.class)) {
                ui.printTaskNotUpdated(taskList.get(index - 1).toString());
            } else {
                taskList.get(index - 1).updateDate(date);
                ui.printUpdatedTask(taskList.get(index - 1).toString());
            }

        } catch (IndexOutOfBoundsException e) {
            ui.printErrorTaskDoesNotExist(String.valueOf(index));
        } finally {
            storage.save(taskList);
        }
    }

}
