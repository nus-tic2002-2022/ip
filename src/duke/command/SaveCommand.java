package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.io.FileWriter;
import java.io.IOException;

public class SaveCommand extends Command {

    public SaveCommand() {

    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try{
            FileWriter myObj = new FileWriter(storage.getFile());
            for(Task t : tasks.getTasks()) {
                myObj.write(t.toFileString());
                myObj.write("\n");
            }
            myObj.close();
            System.out.println("Noted. I've saved " + tasks.getTasks().size() + " tasks");
            System.out.println("File saved successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred upon reading file.");
        }
    }
}
