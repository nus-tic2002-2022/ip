import commands.Command;
import parser.Parser;
import storage.Storage;
import tasks.TaskList;
import ui.UI;

/**
 * Entry point of the Duke Chat Bot application.
 * Initializes the application and starts prompting the user for input.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private UI ui;

    /**
     * Constructor of Duke.
     * Sets up the required objects and loads up the data from the storage file if exists.
     */
    public Duke() {
        ui = new UI();
        storage = new Storage();
        storage.init();
        tasks = storage.loadTasks();

    }

    /**
     * Shows the Welcome Message
     * Performs a loop to accept user input until termination.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = new Parser().parseCommand(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}



