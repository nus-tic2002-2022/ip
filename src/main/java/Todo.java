public class Todo extends Task{

    //example "todo read book"
    public Todo(String description) {
        super(description);
        System.out.println("Got it. I've added this task:");
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
