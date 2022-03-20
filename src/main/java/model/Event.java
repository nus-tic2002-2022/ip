package model;

import java.time.format.DateTimeFormatter;

public class Event extends Task  {

    protected String period;

    public Event(String description, String period) {
        super(description);
        this.period = period;
    }

    /**
     * Format task to String
     *
     */
    @Override
    public String toString(){
        String status = " ";
        if(done){
            status = "X";
        }
        return ("[E][" + status + "] " + description + " (at: " + period + ")");
    }

    /**
     * Print the task in certain format
     *
     */
    @Override
    public void printTask(){
        System.out.println(toString());
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
