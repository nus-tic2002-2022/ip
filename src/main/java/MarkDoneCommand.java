public class MarkDoneCommand extends Command {
    public static final String COMMAND_WORD = "mark";

    private int index;

    public MarkDoneCommand(int index){
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, UI ui) {
            if (taskList.get(index - 1).getDoneStatus()) {
                System.out.println("task is already done");
            } else {
                taskList.get(index - 1).markDone();
                System.out.println("marked task as done");
            }
    }

}
