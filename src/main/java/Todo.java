public class Todo extends Task{

    protected String todotask;

    public Todo (String description, String todotask){
        super(description);
        this.todotask = todotask;
    }

    public String getTodo(){
        return this.todotask;
    }

    public void setTodo(String todotask){
        this.todotask = todotask;
    }

    @Override
    public String toString() {
        return " [T]   " + super.toString();
    }
}