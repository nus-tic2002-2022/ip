public class Todo extends Task{

    //example "todo read book"
    public Todo(String description) {
        super(description);
        System.out.println("Got it. I've added this task:");
        System.out.println(this);
        newTask();
    }

    public Todo(String description, boolean isDone){
        super(description);
        this.isDone = isDone;
        System.out.println("Got it. I've added this task:");
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
