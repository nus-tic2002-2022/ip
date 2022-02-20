public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
        setType("E");
    }


    @Override
    public void print(int index) {
        System.out.println(index + 1 + ".[" + getType() + "][" + getStatusIcon() + "]" + getDescription() + "(at:" + at + ")");

    }

}
