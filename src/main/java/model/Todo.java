package model;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public void printTask(){
        String status = " ";
        if(done){
            status = "X";
        }
        System.out.println("[T][" + status + "] " + description);
    }

    @Override
    public String toFileString(){
        String status = done ? "1" : "0";
        return "T|" + status + "|" + description;
    }
}