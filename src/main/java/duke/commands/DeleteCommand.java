package duke.commands;

import duke.exception.DukeException;
import duke.tasklist.TaskList;

/**
 * Extended class of Command with methods involving deletion of Task into TaskList.
 */
public class DeleteCommand extends Command{

    /**
     * Removal of specified Task by index from TaskList.
     *
     * @param index index of Task, input obtained from UI (in String format), which to be removed from TaskList.
     * @param taskList TaskList which specified Task to be deleted.
     */
    public static void deleteTask (String index, TaskList taskList) throws DukeException {
        if (taskList.size() == 0) {
            throw new DukeException("emptyList");
        }

        boolean isDigit = false;
        for (int i = 0; i < index.length(); i++){
            if (!Character.isDigit(index.charAt(i))) {
                isDigit = true;
                break;
            }
        }
        if (isDigit) {
            throw new DukeException("invalidIndex");
        }

        int curIndex = Integer.parseInt(index) - 1;
        if (curIndex > taskList.size() - 1 || curIndex < 0) {
            throw new DukeException("outOfRangeIndex");
        }

        taskList.delete(curIndex);
    }

}
