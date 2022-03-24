package duke.task;

public class Todo extends Task{

    //example "todo read book"
    public Todo(String description) {
        super(description);
        System.out.println(this);
        newTask();
    }

    public Todo(String description, boolean isDone){
        super(description, isDone);
        this.isDone = isDone;
    }

    @Override
    public String addToFile() {
        return toString();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
