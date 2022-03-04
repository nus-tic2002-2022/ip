public class ToDos extends Task {
    public ToDos (String task) {
        super(task);
    }

    @Override
    public String toString(){
        return String.format("[T][%s] %s", (done?"x":" "), this.task);
    }
}
