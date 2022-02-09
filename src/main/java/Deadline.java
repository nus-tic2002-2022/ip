public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.description = description;
        this.by = by;
    }

    public String getBy(){
        return this.by;
    }

    public void setBy(String by){
        this.by = by;
    }

    @Override
    public String toString() {
        String output = super.toString();
       return output.substring(0,1) + 'D' + output.substring(2) + "\t" + by;
    }
}
