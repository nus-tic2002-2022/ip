package tasks;
import java.time.*;
/**
 * This is the main class for all task types.
 * It is also the default class for type 'todo'.
 */

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getStatusIcon() {
        return (isDone? "X" : " ");
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDate() {
        return null;
    }

    public String toString() {
        return " [T]"+"[" +getStatusIcon()+ "] " + getDescription();
    }
}
