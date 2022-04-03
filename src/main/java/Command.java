public abstract class Command {
    public Command(){}

    public abstract void execute(TaskList taskList, UI ui);

    public boolean isExit(){
        return false;
    }
}
