package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.io.FileWriter;
import java.io.IOException;

public class DeleteCommand extends Command {

    protected int indexToBeDeleted;

    public DeleteCommand(int indexToBeDeleted) {
        this.indexToBeDeleted = indexToBeDeleted;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task deletedTask = tasks.getTasks().get(indexToBeDeleted);

        tasks.getTasks().remove(this.indexToBeDeleted);

        System.out.println("Noted. I've removed this task:");
        System.out.println("    " + deletedTask.toString());
        System.out.println("Now you have " + tasks.getTasks().size() + " task in the list.");
    }
}
