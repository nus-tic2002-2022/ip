package commands;

import java.util.Date;

import storage.Storage;
import tasks.TaskList;
import ui.UI;


/**
 * Finds and lists all task in the Task List which contains the date provided.
 */
public class ViewCommand extends Command {

    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_USAGE = "View the tasks scheduled on a particular day \n"
            + "usage: view <date> \n\n"
            + "Eg. view today \n"
            + "Returns all deadline/even tasks where date is today";

    private Date date;

    public ViewCommand (Date date) {
        this.date = date;
    }


    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        TaskList tempList;
        tempList = taskList.getSearchDateResults(date);
        ui.printSearchResults(tempList);
    }

}
