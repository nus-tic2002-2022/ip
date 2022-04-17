package duke.task;

public class Task {
    public int id;
    protected String description;
    protected boolean isDone;

    public Task(Integer id, String description) {
        this.description = description;
        this.id = id; // will get last number in the future
        this.isDone = false;
    }

    public Task(Integer id, String description, boolean isDone) {
        this.description = description;
        this.id = id; // will get last number in the future
        this.isDone = isDone;
    }

    /**
     * Create an initial string for printing
     *
     * @return output display of task objects e.g: [X] write documentation
     */
    @Override
    public String toString() {
        return ("[" + getStatusIcon() + "] " + description.split(" /")[0]);
    }

    /**
     * Use task object output and add parameters related to todo object
     *
     * @return output display of todo object
     */
    public String getDesc() {
        return this.description;
    }

    /**
     * Use task object output and add parameters related to todo object
     *
     * @return status icon of task e.g: [X]
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Get the done status of the task
     *
     * @return a string to display if the work is done
     */
    public String getStatus() {
        return (isDone ? "1" : "0");
    }

    /**
     * Mark task as done
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Mark task as undone
     */
    public void markAsUnDone() {
        isDone = false;
    }

    /**
     * Get the id of the task
     *
     * @return id of task
     */
    public int getId() {
        return id;
    }

    /**
     * Set the id for the task
     *
     * @param id to be set for the task
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Used to format the task for storage purposes in csv format
     *
     * @return Formatted response to store in file
     */
    public String toStorage() {
        return String.format("%s,%s,%s,%s%s", this.getStatus(), this.getInstanceOf(), this.getId(), this.getDesc(), this.getTimings());
    }

    /**
     * Used for parsing task type into storing in csv
     *
     * @return the corresponding characters related to the instance of the task
     */
    public String getInstanceOf() {
        if (this.getClass() == Deadline.class) {
            return "d";
        } else if (this.getClass() == Events.class) {
            return "e";
        } else {
            return "t";
        }
    }

    /**
     * Get the timing parameters of deadline and event type
     *
     * @return the timing of corresponding task time and their fields related to time
     */
    public String getTimings() {
        if (this.getClass() == Deadline.class) {
            return ("," + ((Deadline) this).getDate());
        } else if (this.getClass() == Events.class) {
            return ("," + ((Events) this).getDateTime());
        } else {
            return "";
        }
    }
}