package duke.commands;

import duke.exception.DukeException;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Extended class of Command with methods involving listing of Task into TaskList.
 */
public class ListCommand extends Command{

    /**
     * Listing Task in TaskList to UI.
     *
     * @param taskList TaskList to be displayed to UI.
     */
    public static void showList (TaskList taskList) throws DukeException {
        if (taskList.size() == 0) {
            throw new DukeException("emptyList");
        }
        else {
            Ui.showTaskList(taskList);
        }
    }

}
