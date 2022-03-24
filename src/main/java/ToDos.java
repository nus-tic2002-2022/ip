public class ToDos extends Task {
    public ToDos (String task) {
        super(task);
    }

    @Override
    public String toString(){
        return String.format("[T][%s] %s", (done?"x":" "), this.task);
    }

    @Override
    public String taskToSaveFile() {
        return String.format("T|%d|%s", (done?1:0), this.task);
    };
}
