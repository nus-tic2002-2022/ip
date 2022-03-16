package Duke;
public class Deadline extends Task{
    public String time;
    public Deadline(String description, String time) {
        super(description);
        this.time = time;
    }
    @Override
    public String toString(){
        return String.format("[D]%s (by: %s)", super.toString(), time);
    }
}
