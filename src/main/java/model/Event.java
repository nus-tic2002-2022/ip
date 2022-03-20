package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task  {

    protected LocalDate period;

    public Event(String description, LocalDate period) {
        super(description);
        this.period = period;
    }

    public Event(String description, String period) {
        super(description);
        this.period = LocalDate.parse(period);
    }

    /**
     *  Return the value of period
     *
     */
    public LocalDate getPeriod() {
        return period;
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
        String formattedPeriod = period.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return ("[E][" + status + "] " + description + " (at: " + formattedPeriod + ")");
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
