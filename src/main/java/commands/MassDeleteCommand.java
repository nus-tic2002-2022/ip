package commands;

import java.util.ArrayList;
import java.util.Collections;

import storage.Storage;
import tasks.TaskList;
import ui.UI;

/**
 * Deletes all tasks or all marked tasks as supplied by input
 */
public class MassDeleteCommand extends Command {
    private String deleteOption;

    public MassDeleteCommand (String deleteOption) {
        this.deleteOption = deleteOption;
    }

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        if (deleteOption.equalsIgnoreCase("all")) {
            int totalNumberOfTask = taskList.getNumberOfTask();
            for (int i = 0; i < totalNumberOfTask; i++) {
                taskList.deleteTask(0);
            }
            ui.printAllTasksDeleted();

        } else {
            ArrayList<Integer> indexesToDelete = new ArrayList<>();

            for (int i = 0; i < taskList.getNumberOfTask(); i++) {
                if (taskList.get(i).getDoneStatus()) {
                    indexesToDelete.add(i);
                }
            }

            Collections.sort(indexesToDelete, Collections.reverseOrder());

            for (int i = 0; i < indexesToDelete.size(); i++) {
                taskList.deleteTask(indexesToDelete.get(i));
            }

            ui.printAllMarkedTasksDeleted();

        }

        storage.save(taskList);
    }
}
