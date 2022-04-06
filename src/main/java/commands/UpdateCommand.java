package commands;

import storage.Storage;
import tasks.Deadline;
import tasks.Event;
import tasks.TaskList;
import tasks.Todo;
import ui.UI;

import java.util.Date;

public class UpdateCommand extends Command{
    public static final String COMMAND_WORD = "update";

    private int index;
    private String newContent;
    private String partToUpdate;
    private Date date;

    public UpdateCommand(int index, String partToUpdate, String newContent){
        this.index = index;
        this.partToUpdate = partToUpdate;
        this.newContent = newContent;
    }

    public UpdateCommand(int index, String partToUpdate, Date date){
        this.index = index;
        this.partToUpdate = partToUpdate;
        this.date = date;
    }


    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        try {
            if (partToUpdate.equalsIgnoreCase("desc")) {
                taskList.get(index - 1).updateDescription(newContent);
                ui.printUpdatedTask(taskList.get(index-1).toString());
            }

            if (taskList.get(index - 1).getClass().equals(Todo.class)) {
                ui.printTaskNotUpdated(taskList.get(index-1).toString());
            } else {
                taskList.get(index - 1).updateDate(date);
                ui.printUpdatedTask(taskList.get(index-1).toString());
            }

        } catch (IndexOutOfBoundsException e) {
            ui.printErrorTaskDoesNotExist(String.valueOf(index));
        }finally {
            storage.save(taskList);
        }
    }

}
