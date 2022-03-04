public class Events extends Task{
    protected String eventTime;

    public Events (String task, String eventTime) {
        super(task);
        this.eventTime = eventTime;
    }

    @Override
    public String toString(){
        return String.format("[E][%s] %s (at %s)", (done?"x":" "), this.task,this.eventTime);
    }
}
