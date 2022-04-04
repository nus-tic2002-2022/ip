public class UnmarkDoneCommand extends Command {
    public static final String COMMAND_WORD = "unmark";

    private int index;

    public UnmarkDoneCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, UI ui) {
        if (taskList.get(index - 1).getDoneStatus()) {
            taskList.get(index - 1).unmarkDone();
            System.out.println("Unmarked task");
        } else {
            System.out.println("Task is not yet done");
        }
    }
}

