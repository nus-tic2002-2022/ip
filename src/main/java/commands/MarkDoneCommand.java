package commands;

import storage.Storage;
import tasks.TaskList;
import ui.UI;


/**
 * Marks the task as done
 */
public class MarkDoneCommand extends Command {
    public static final String COMMAND_WORD = "mark";

    public static final String MESSAGE_USAGE = "Marks provided task as done \n"
                                               + "usage: mark <task id> \n\n"
                                               + "Eg. mark 1 \n"
                                               + "Marks the first task in the list as done";

    private int index;

    public MarkDoneCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        try {
            if (taskList.get(index - 1).getDoneStatus()) {
                ui.printErrorTaskAlreadyMarked();
            } else {
                taskList.get(index - 1).markDone();
                ui.printMarkedTask();
                storage.save(taskList);
            }
        } catch (IndexOutOfBoundsException e) {
            ui.printErrorTaskDoesNotExist(String.valueOf(index));
        }

    }

}
