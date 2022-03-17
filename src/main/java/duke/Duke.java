package duke;

import duke.commands.Command;
import duke.commands.ResultCommand;
import duke.exception.DukeException;
import duke.storage.*;
import duke.tasklist.*;
import duke.ui.*;
import duke.parser.*;

import java.io.IOException;
import java.lang.*;

public class Duke {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load());
        } catch (IOException e) {
            Ui.ioErrorMessage();
            exit();
        } catch (DukeException e) {
            Ui.errorMessage(e);
            exit();
        }
    }

    public void run(String[] launchArgs) {
        start(launchArgs);
        runCommandLoopUntilExitCommand();
        exit();
    }

    private void start(String[] launchArgs) {
        Ui.showWelcomeMessage();
    }

    private void runCommandLoopUntilExitCommand() {
        Command command = new Command(-1);
        do {
            Ui.displayUsername();
            String userInputUi = Ui.getUserInput();
            Parser userInputParser = new Parser(userInputUi);
            command.setData(userInputParser, taskList);
            executeCommand(command);

        } while (true);
    }

    private void executeCommand(Command command) {
        try {
            ResultCommand output = command.execute();
            if (output.getFeedback().equalsIgnoreCase("save")) {
                storage.save(taskList);
            }
            if (!output.getFeedback().equalsIgnoreCase("noReply") && !output.getFeedback().equalsIgnoreCase("goodbye")) {
                Ui.replyMessage(output);
            }
            if (output.getFeedback().equalsIgnoreCase("goodbye")) {
                exit();
            }
        } catch (DukeException e) {
            Ui.errorMessage(e);
        } catch (IOException e) {
            Ui.ioErrorMessage();
        }
    }

    private void exit() {
        Ui.goodbyeMessage();
        System.exit(0);
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run(args);
    }

}
