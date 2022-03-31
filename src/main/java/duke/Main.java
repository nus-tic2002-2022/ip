package duke;

import duke.commands.Command;
import duke.data.entity.TaskList;
import duke.data.exception.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;
import duke.parser.Parser;

public class Main {
    private Storage storage;
    private TaskList tasks = new TaskList();
    private Ui ui;

    public Main() {
        ui = new Ui();
        try {
            storage = new Storage();
            tasks = new TaskList(storage.load());
        } catch (Storage.StorageOperationException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        } catch (DukeException e) {
            ui.showError(e.getMessage());
            System.exit(0);
        }
    }
    public Main(String filePath) {
        ui = new Ui();
        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage.load());
        } catch (Storage.StorageOperationException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        } catch (DukeException e) {
            ui.showError(e.getMessage());
            System.exit(0);
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
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
        new Main("tasks.txt").run();
    }
}
