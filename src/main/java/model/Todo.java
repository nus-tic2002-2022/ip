package model;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
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
        return ("[T][" + status + "] " + description);
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
        return "T|" + status + "|" + description;
    }
}