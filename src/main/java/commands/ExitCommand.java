package commands;

import tasks.*;
import ui.UI;

public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "bye";


    @Override
    public void execute(TaskList taskList, UI ui) {
        ui.showBye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}