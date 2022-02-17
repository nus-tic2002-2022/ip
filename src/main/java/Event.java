public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
        setType("E");
    }

    public void setAt(String at) {
        this.at = at;
    }
    public String getAt() {
        return at;
    }

    @Override
    public void print(int index) {
        System.out.println(index + 1 + ".[" + getType() + "][" + getStatusIcon() + "]" + getDescription() + "(at:" + at + ")");

    }

}
