public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";

    private int index;

    public DeleteCommand(int index){
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, UI ui) {
            Task taskToDelete = taskList.get(index-1);
            taskList.deleteTask(index-1);
            ui.printTaskDeleted(taskToDelete.toString(), taskList.getNumberOfTask());
    }

}
