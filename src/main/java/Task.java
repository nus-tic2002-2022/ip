import java.time.*;

public class Task {
    protected String description;
    protected String enter;
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
