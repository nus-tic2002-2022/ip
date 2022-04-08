package commands;

import storage.Storage;
import tasks.TaskList;
import ui.UI;

/**
 * Unmarks the task as done
 */
public class UnmarkDoneCommand extends Command {
    public static final String COMMAND_WORD = "unmark";

    public static final String MESSAGE_USAGE = "Unmarks provided task as done \n"
                                               + "usage: unmark <task id> \n\n"
                                               + "Eg. unmark 1 \n"
                                               + "Unmarks the first task in the list as done";

    private final int index;

    public UnmarkDoneCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        try {
            if (taskList.getTask(index - 1).getDoneStatus()) {
                taskList.getTask(index - 1).unmarkDone();
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

