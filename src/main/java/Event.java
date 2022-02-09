public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.description = description;
        this.at = at;
    }

    public String getAt(){
        return this.at;
    }

    public void setAt(String by){
        this.at = at;
    }

    @Override
    public String toString() {
        String output = super.toString();
       return output.substring(0,1) + 'E' + output.substring(2) + "\t" + at;
    }
}
