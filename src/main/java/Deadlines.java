public class Deadlines extends Task{
    protected String deadline;

    public Deadlines (String task, String deadline ){
        super(task);
        this.deadline = deadline;
    }

    @Override
    public String toString(){
        return String.format("[D][%s] %s (by: %s)", (done?"x":" "), this.task,this.deadline);
    }
}
