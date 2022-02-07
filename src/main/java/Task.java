public class Task {
    private boolean isDone;
    private String description;

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
    public String getDescription() {
        return description;
    }

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
