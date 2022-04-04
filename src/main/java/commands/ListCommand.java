package commands;

import tasks.*;
import ui.UI;

public class ListCommand extends Command{
    public static final String COMMAND_WORD = "list";


    @Override
    public void execute(TaskList taskList, UI ui) {
        taskList.printTask();
    }
}