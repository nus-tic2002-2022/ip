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

/**
 * This class contains the main method and it handles processes from start to end of the application.
 */
public class Duke {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Constructor for Duke
     *
     * @param filePath Filepath for text file containing TaskList content
     *                 which will be loaded into taskList variable in Duke class.
     */
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

    /**
     * Run the application by starting it and looping it until the exit command is given by user.
     *
     * @param launchArgs variable passed from the main method.
     */
    public void run(String[] launchArgs) {
        start(launchArgs);
        runCommandLoopUntilExitCommand();
        exit();
    }

    /**
     * Start the application by showing welcome message on UI.
     *
     * @param launchArgs variable passed from the main method.
     */
    private void start(String[] launchArgs) {
        Ui.showWelcomeMessage();
    }

    /**
     * Loop the process of asking user for input, executing the user command
     * and responding to user based on result of execution.
     */
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

    /**
     * Execute command provided and respond to user correctly based on the command executed.
     *
     * @param command Command provided by the user.
     */
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

    /**
     * End the application by showing goodbye message on UI and stop the program.
     */
    private void exit() {
        Ui.goodbyeMessage();
        System.exit(0);
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run(args);
    }

}
