package duke.commands;

import duke.exception.DukeException;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class ListCommand extends Command{

    public static void showList (TaskList taskList) throws DukeException {
        if (taskList.size() == 0) {
            throw new DukeException("emptyList");
        }
        else {
            Ui.showTaskList(taskList);
        }
    }

}
