package duke.commands;

import duke.exception.DukeException;
import duke.parser.Parser;
import duke.tasklist.Task;
import duke.tasklist.TaskList;

/**
 * Extended class of Command with methods involving update of Task in TaskList.
 */
public class UpdateCommand extends Command{

    /**
     * Return the updated Task after updating the specified Task in TaskList with the new Description provided.
     *
     * @param userInput User input with information of index of Task and new description obtained from UI.
     * @param taskList TaskList which specified Task to be marked.
     * @return Updated task in the TaskList in Task format.
     */
    public static Task updateDescription (Parser userInput, TaskList taskList) throws DukeException {
        if (taskList.size() == 0) {
            throw new DukeException("emptyList");
        }

        String index = userInput.getUserInput(1);
        boolean check = false;
        for (int i = 0; i < index.length(); i++){
            if (!Character.isDigit(userInput.getUserInput(1).charAt(i))) {
                check = true;
                break;
            }
        }
        if (check) {
            throw new DukeException("invalidIndex");
        }

        int curIndex = Integer.parseInt(index) - 1;
        if (curIndex > taskList.size() - 1 || curIndex < 0) {
            throw new DukeException("outOfRangeIndex");
        }

        String newDescription = "";
        if (userInput.getUserInputSize() < 3) {
            throw new DukeException("missingInformation");
        } else {
            for (int j = 2; j < userInput.getUserInputSize(); j++) {
                if (j != 2) {
                    newDescription = newDescription + " ";
                }
                newDescription = newDescription + userInput.getUserInput(j);
            }
        }
        if (newDescription.equalsIgnoreCase("")) {
            throw new DukeException("missingInformation");
        }

        return taskList.update(curIndex, newDescription);
    }
}
