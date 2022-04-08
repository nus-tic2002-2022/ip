package model;

import java.time.format.DateTimeFormatter;

public class Duration extends Task {

    protected int effort;

    public Duration(String description, String effort) {
        super(description);
        this.effort = Integer. parseInt(effort);
    }

    /**
     * Format task to String.
     *
     */
    @Override
    public String toString(){
        String status = " ";
        if(done){
            status = "X";
        }
        return ("[DR][" + status + "] " + description + " (effort: " + Integer.toString(effort) + " hour)");
    }

    /**
     * Print the task in certain format.
     *
     */
    @Override
    public void printTask(){
        System.out.println(toString());
    }

    /**
     * Format the task to store into file.
     *
     * @return Formatted string
     */
    @Override
    public String toFileString(){
        String status = done ? "1" : "0";
        return "DR|" + status + "|" + description + "|" + Integer.toString(effort);
    }
}
