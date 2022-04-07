package commands;

import storage.Storage;
import tasks.TaskList;
import ui.UI;

/**
 * Unmarks the task as done
 */
public class UnmarkDoneCommand extends Command {
    public static final String COMMAND_WORD = "unmark";

    private int index;

    public UnmarkDoneCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        try {
            if (taskList.get(index - 1).getDoneStatus()) {
                taskList.get(index - 1).unmarkDone();
                ui.printUnmarkedTask();
                storage.save(taskList);
            } else {
                ui.printErrorTaskAlreadyUnmarked();
            }
        } catch (IndexOutOfBoundsException e) {
            ui.printErrorTaskDoesNotExist(String.valueOf(index));
        }
    }
}

