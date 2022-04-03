public class AddCommand extends Command{
    Task task;

    public AddCommand(Task task){
        this.task = task;
    }

    @Override
    public void execute(TaskList taskList, UI ui) {
        taskList.addTask(task);
        ui.printTaskAdded(task.toString(), taskList.getNumberOfTask());
    }

}
