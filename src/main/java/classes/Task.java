package classes;

public abstract class Task {
    protected boolean isDone;
    protected String description;

    public Task(String description) {
        isDone = false;
        this.description = description;
    }

    /**
     * Getter for isDone
     * **/
    public boolean getDoneStatus() {
        return isDone;
    }

    /**
     * Getter for description
     * **/
    public abstract String getDescription();

    /**
     * Getter for type of task
     * **/
    public abstract String getType();

    /**
     * Setter for isDone
     *
     * @param isDone New Status
     * **/
    public void setDoneStatus(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Setter for description
     *
     * @param description New description
     * **/
    public void setDescription(String description) {
        this.description = description;
    }
}
