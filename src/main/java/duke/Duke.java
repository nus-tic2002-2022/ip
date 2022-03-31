package main.java.duke;


import main.java.duke.commands.Command;
import main.java.duke.data.entity.TaskList;
import main.java.duke.data.exception.DukeException;
import main.java.duke.parser.Parser;
import main.java.duke.storage.Storage;
import main.java.duke.ui.Ui;

import java.text.ParseException;

import static main.java.duke.common.Messages.MESSAGE_DATE_FORMAT_ERROR;


public class Duke {
    private Storage storage;
    private TaskList tasks = new TaskList();
    private Ui ui;

    public Duke() {
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
    public Duke(String filePath) {
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
            } catch (ParseException e) {
                ui.showError(e.getMessage());
                ui.showError(MESSAGE_DATE_FORMAT_ERROR);
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("tasks.txt").run();
    }
}
