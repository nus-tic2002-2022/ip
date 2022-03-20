package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task  {

    protected LocalDate by;

    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by);
    }

    /**
     * Return the value of by
     *
     */
    public LocalDate getBy() {
        return by;
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
        String formattedBy = by.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
       return ("[D][" + status + "] " + description + " (by: " + formattedBy + ")");
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
        return "D|" + status + "|" + description + "|" + by;
    }
    
}
