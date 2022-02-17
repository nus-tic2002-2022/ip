public class ToDo extends Task {

    protected boolean isDone;

    public ToDo(String description) {
        super(description);
        isDone = false;
        setType("T");
    }

    public void print(int index) {
        System.out.println(index + 1 + ".[" + getType() + "][" + getStatusIcon() + "]" + getDescription());
    }

}
