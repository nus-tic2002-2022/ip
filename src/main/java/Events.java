public class Events extends Task {
    protected String date;
    protected String time;

    public Events(String description, String by, String at) {
        super(description);
        this.date = by;
        this.time = at;
    }

    @Override
    public String toString() {
        String datetime = this.date + " " + this.time;
        return "[E]" + super.toString() + " (at: " + datetime + ")";
    }
}
