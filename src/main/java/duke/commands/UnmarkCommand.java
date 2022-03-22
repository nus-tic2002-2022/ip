package duke.commands;

import duke.exception.DukeException;
import duke.tasklist.TaskList;

/**
 * Extended class of Command with methods involving un-marking of Task into TaskList.
 */
public class UnmarkCommand extends Command{

    /**
     * Return the successfulness in change of status of Task
     * If change is made, true is returned.
     * Else change is not made, false is returned.
     *
     * @param index index of Task, input obtained from UI (in String format), which to be marked.
     * @param taskList TaskList which specified Task to be marked.
     * @return True or False based on successfulness in change of status of Task.
     */
    public static boolean unmarkTask (String index, TaskList taskList) throws DukeException {
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

        return taskList.get(cur_index).setTaskStatus(false);
    }

}
