package duke.command;

import duke.storage.Storage;
import duke.ui.Ui;
import duke.exception.DukeException;
import duke.task.TaskList;

import java.util.ArrayList;

public abstract class Command {

    protected String[] args;

    public Command() {}

    public Command(String[] args) {
        this.args = args;
    }

    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;

}
