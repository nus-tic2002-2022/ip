public class Deadlines extends Task{

    protected String by;

    public Deadlines (String description, String by)
    {
        super(description);
        this.by = by;
    }
    public String getStatus() {
        return (isDone ? "[D][X] " + this.description  + " (by: " +by +")": "[D][ ]" + this.description  + " (by: " +by +")");
    }
}
