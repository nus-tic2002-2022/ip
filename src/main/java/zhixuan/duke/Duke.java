package zhixuan.duke;

import java.util.Scanner;

import zhixuan.duke.storage.StorageFile;
import zhixuan.duke.ui.DukeUI;
import zhixuan.duke.commands.Command;
import zhixuan.duke.commands.ExitCommand;
import zhixuan.duke.parser.Parser;

public class Duke {

    private DukeUI ui;

    public static void main(String[] args) {
        new Duke().run();
    }

    public void run() {
        ui = new DukeUI();
        ui.printWelcomeMessage(StorageFile.loadFile());
        readCommandTillExitLoop();
    }

    private void readCommandTillExitLoop() {
        Parser pr = new Parser(new Scanner(System.in));
        Command command = null;
        while (!ExitCommand.selectedCommand(command)) {
            command = pr.processInput();
            boolean isUpdated = command.execute();
            if (isUpdated) {
                StorageFile.saveFile();
            }
        }
    }
}
