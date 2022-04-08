package model;

public class Task {
    protected String description;
    protected boolean done;

    
    public Task(String description) {
        this.description = description;
        this.done = false;
    }

    /**
     * Check the status of the task.
     *
     * @return Status of the task
     */
    public boolean isDone(){
        return done;
    }

    /**
     * Set the status of task.
     *
     * @param done Status of the task
     */
    public void setDone(boolean done){
        this.done = done;
    }

    /**
     * Get description.
     *
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Print the task.
     *
     */
    public void printTask(){
        String status = " ";
        if(done){
            status = "X";
        }
        System.out.println("[" + status + "] " + description);
    }


    /**
     * Format the task to store into file.
     *
     */
    public String toFileString(){
        return "";
    }
}
