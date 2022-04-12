public class Todo extends Task {
    protected boolean isTodo;

    public Todo(String description) {
        super (description);
        isTodo = false;
    }

    public void markAsTodo() {isTodo = true;}

    public void unmarkTodo() {isTodo = false;}

    public String getTodoStatusIcon() {
        return (isTodo? "T" : " ");
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}