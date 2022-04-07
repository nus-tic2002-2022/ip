package commands;

import storage.Storage;
import tasks.TaskList;
import ui.UI;


/**
 * Marks the task as done
 */
public class MarkDoneCommand extends Command {
    public static final String COMMAND_WORD = "mark";

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
