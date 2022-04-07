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

    public static final String MESSAGE_USAGE = "Searches the list and returns all task with keyword provided \n"
                                               + "usage: find <keyword> \n\n"
                                               + "Eg. find book \n"
                                               + "Returns a list with all tasks with the word book";


    private final String searchTerm;

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
