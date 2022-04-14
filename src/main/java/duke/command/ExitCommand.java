package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Command to exit the program
 */
public class ExitCommand extends Command {

    private static final String REPLY_MESSAGE = "\tBye. Hope to see you again soon!";
    private static boolean isExit = false;

    /**
     * Get isExit boolean value
     * @return exit status
     */
    public static boolean getIsExit() {
        return isExit;
    }

    /**
     * Execute ExitCommand to exit the {@link duke.Duke} application
     * @param taskList is not used on this method
     * @param ui is not used on this method
     * @param storage is not used on this method
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.show(REPLY_MESSAGE);
        isExit = true;
    }
}
