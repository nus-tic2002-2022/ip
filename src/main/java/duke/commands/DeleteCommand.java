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

        boolean check = false;
        for (int i = 0; i < index.length(); i++){
            if (!Character.isDigit(index.charAt(i))) {
                check = true;
                break;
            }
        }
        if (check) {
            throw new DukeException("invalidIndex");
        }

        int cur_index = Integer.parseInt(index) - 1;
        if (cur_index > taskList.size() - 1 || cur_index < 0) {
            throw new DukeException("outOfRangeIndex");
        }

        taskList.delete(cur_index);
    }

}
