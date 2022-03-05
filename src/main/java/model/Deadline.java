package model;

public class Deadline extends Task  {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public void printTask(){
        String status = " ";
        if(done){
            status = "X";
        }
        System.out.println("[D][" + status + "] " + description + " (by: " + by + ")");
    }

    @Override
    public String toFileString(){
        String status = done ? "1" : "0";
        return "D|" + status + "|" + description + "|" + by;
    }
    
}
