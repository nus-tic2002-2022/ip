package commands;

import storage.Storage;
import tasks.TaskList;
import ui.UI;

/**
 * Finds and lists all task in the Task List which contains the argument as a keyword.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";

    private String searchTerm;

    public FindCommand(String searchTerm) {
        this.searchTerm = searchTerm;
    }


    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        TaskList tempList;
        tempList = taskList.getSearchResults(searchTerm);
        ui.printSearchResults(tempList);
    }

}
