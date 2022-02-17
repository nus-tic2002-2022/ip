public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        setType("D");
    }

    public void setBy(String by) {
        this.by = by;
    }
    public String getBy() {
        return by;
    }

    public void print(int index) {
        System.out.println(index + 1 + ".[" + getType() + "][" + getStatusIcon() + "]" + getDescription() + "(by:" + by + ")");

    }

}