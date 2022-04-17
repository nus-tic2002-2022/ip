package duke;

import duke.command.Command;
import duke.command.ExitCommand;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;
import duke.parser.Parser;
import duke.task.TaskList;
import java.io.IOException;


public class Duke {

    private Ui ui;
    private TaskList taskList;
    private Storage storage;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            storage.init();
            taskList = new TaskList(storage.load());
        } catch (DukeException | IOException e) {
            ui.show(e.getMessage());
            taskList = new TaskList();
        }
    }

    public void run() {
        ui.showWelcomeMessage();
        loopUntilExitCommand();
        exit();
    }

    private void loopUntilExitCommand() {
        Command command;
        do {
            try {
                String userInput = ui.getUserCommand();
                command = Parser.parseCommand(userInput);
                command.execute(taskList, ui, storage);
            } catch (DukeException e) {
                ui.show(e.getMessage());
            }
        } while (!ExitCommand.getIsExit());
    }

    private void exit() {
        System.exit(0);
    }

    public static void main(String[] args) {

        new Duke("data/tasks.txt").run();

    }

    public String reply(String input) {
        try {
            Command command = Parser.parseCommand(input);
            return command.execute(taskList, ui, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
