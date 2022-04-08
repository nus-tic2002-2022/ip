package duke;

import duke.commands.Command;
import duke.data.entity.TaskList;
import duke.data.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * This class contains the main method to run the application.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks = new TaskList();
    private final Ui ui;

    /**
     * Constructor for Duke
     * Read the file and load list of task into tasks(TaskList)
     * Show error and exit the system when there is format error in reading the file
     *
     * @param filePath location of text file containing list of Task
     *                 which are loaded into TaskList
     */
    public Duke(String filePath) {
        ui = new Ui();
        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showError(e.getMessage());
            System.exit(0);
        }
    }

    /**
     * Get command from the user, parse it into Task and store it to the file.
     * And get another command until user type 'bye' or 'b'
     * Stop the application when user type 'bye' or 'b'.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("tasks.txt").run();
    }
}
