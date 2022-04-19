package zhixuan.duke;

import java.io.File;
import java.util.Scanner;

import zhixuan.duke.storage.StorageFile;
import zhixuan.duke.ui.DukeUI;
import zhixuan.duke.commands.Command;
import zhixuan.duke.commands.ExitCommand;
import zhixuan.duke.parser.Parser;

/**
 * Entry point of the Duke application.
 * Initializes the application and starts the interaction with the user.
 */
public class Duke {

    private DukeUI ui;
    private final File defaultFileLocation = StorageFile.getDefaultLocation();
    private File fileLocation = defaultFileLocation;

    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     * Main method that starts the application
     *
     * Displays welcome message & checks for existing list file
     * Loads list file if available
     * Read & act on user input
     **/
    public void run() {
        ui = new DukeUI();
        ui.printWelcomeMessage(StorageFile.loadFile(defaultFileLocation));
        readCommandTillExitLoop();
    }

    /**
     * Constantly read & process user input till exit command is sent
     **/
    private void readCommandTillExitLoop() {
        Parser pr = new Parser(new Scanner(System.in));
        Command command = null;
        while (!ExitCommand.selectedCommand(command)) {
            command = pr.processInput();
            boolean isUpdated = command.execute();
            if (isUpdated) {
                if (StorageFile.isLoadedNewFile) {
                    fileLocation = StorageFile.loadedFile;
                }
                StorageFile.saveFile(fileLocation);
            }
        }
    }
}
