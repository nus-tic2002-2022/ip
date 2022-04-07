package commands;

import storage.Storage;
import tasks.TaskList;
import ui.UI;

/**
 * Represents an executable command.
 */
public abstract class Command {
    public Command() { }

    public abstract void execute(TaskList taskList, UI ui, Storage storage);

    public boolean isExit() {
        return false;
    }
}
