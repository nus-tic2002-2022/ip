package model;

public class Event extends Task  {

    protected String period;

    public Event(String description, String period) {
        super(description);
        this.period = period;
    }

    @Override
    public void printTask(){
        String status = " ";
        if(done){
            status = "X";
        }
        System.out.println("[E][" + status + "] " + description + " (at: " + period + ")");
    }

    @Override
    public String toFileString(){
        String status = done ? "1" : "0";
        return "E|" + status + "|" + description + "|" + period;
    }
}
