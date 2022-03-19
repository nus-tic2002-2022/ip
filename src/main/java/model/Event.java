package model;

public class Event extends Task  {

    protected String period;

    public Event(String description, String period) {
        super(description);
        this.period = period;
    }

    /**
     * Print the task in certain format
     *
     */
    @Override
    public void printTask(){
        String status = " ";
        if(done){
            status = "X";
        }
        System.out.println("[E][" + status + "] " + description + " (at: " + period + ")");
    }


    /**
     * Format the task to store into file
     *
     * @return Formatted string
     */
    @Override
    public String toFileString(){
        String status = done ? "1" : "0";
        return "E|" + status + "|" + description + "|" + period;
    }
}
