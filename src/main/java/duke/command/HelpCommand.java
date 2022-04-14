package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class HelpCommand extends Command {

    private static final String REPLY_MESSAGE = "\nHere are the commands you can enter: \n"
                                              + "1. enter \"bye\" to exit the application.\n"
                                              + "2. enter \"clear\" to clear your task list.\n"
                                              + "3. enter \"deadline <description> \\by <date in YYYY-MM-DD format>\" to add a task with date.\n"
                                              + "4. enter \"delete <task number>\" to delete a task.\n"
                                              + "5. enter \"event <description> \\at <datetime in YYYY-MM-DD hh:mmm format>\" to add a task with date and time. \n"
                                              + "6. enter \"find <keyword>\" to search the tasks that match the keyword.\n"
                                              + "7. enter \"list\" to see your task list.\n"
                                              + "8. enter \"mark <task number>\" to update a task as done.\n"
                                              + "9. enter \"todo <description>\" to add a task.\n"
                                              + "10. enter \"unmark <task number>\" to update a task as not done.\n";
    /**
     * Execute HelpCommand to show the supported commands
     * @param taskList is not used on this method
     * @param ui is not used on this method
     * @param storage is not used on this method
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.show(REPLY_MESSAGE);
    }

}
