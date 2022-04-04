package commands;


import tasks.*;
import ui.UI;

public class MarkDoneCommand extends Command {
    public static final String COMMAND_WORD = "mark";

    private int index;

    public MarkDoneCommand(int index){
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, UI ui) {
        try {
            if (taskList.get(index - 1).getDoneStatus()) {
                ui.printErrorTaskAlreadyMarked();
            } else {
                taskList.get(index - 1).markDone();
                ui.printMarkedTask();
            }
        }catch(IndexOutOfBoundsException e){
            ui.printErrorTaskDoesNotExist(String.valueOf(index));
        }
    }

}
