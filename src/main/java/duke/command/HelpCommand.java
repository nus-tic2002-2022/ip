package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class HelpCommand extends Command {

    private static final String REPLY_MESSAGE = "\nHere are the commands you can enter: \n"
                                              + "1. to exit the application:                      bye\n"
                                              + "2. to clear your task list:                      clear\n"
                                              + "3. to add a task with date:                      deadline <description> \\by <YYYY-MM-DD>\n"
                                              + "4. to delete a task:                             delete <task number>\n"
                                              + "5. to add a task with date and time:             event <description> \\at <YYYY-MM-DD hh:mm>\n"
                                              + "6. to search the tasks that match the keyword:   find <keyword>\n"
                                              + "7. to see your task list:                        list\n"
                                              + "8. to update a task as done:                     mark <task number>\n"
                                              + "9. to add a task:                                todo <description>\n"
                                              + "10. to update a task as not done:                unmark <task number>\n";
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
