public class ExitCommand extends Command {
    @Override
    public void execute(TaskList taskList, UI ui) {
        ui.showBye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
