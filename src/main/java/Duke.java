import commands.Command;
import parser.Parser;
import tasks.TaskList;
import ui.UI;
import storage.Storage;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private UI ui;

    public Duke() {
        ui = new UI();
        storage = new Storage();
        storage.init();
        tasks = storage.loadTasks();
        //tasks = new TaskList();

    }

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



