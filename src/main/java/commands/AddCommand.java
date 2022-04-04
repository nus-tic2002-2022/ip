package commands;

import tasks.*;
import ui.UI;


public class AddCommand extends Command{
    public static final String COMMAND_WORD_TODO = "todo";
    public static final String COMMAND_WORD_DEADLINE = "deadline";
    public static final String COMMAND_WORD_EVENT = "event";

    private Task task;


    public AddCommand(Task task){
        this.task = task;
    }

    @Override
    public void execute(TaskList taskList, UI ui) {
        taskList.addTask(task);
        ui.printTaskAdded(task.toString(), taskList.getNumberOfTask());
    }

}
