import java.time.*;

public class Task {
    protected String description;
    protected boolean isDone;
    protected String enter;

    public Task(String description, String enter) {
        this.description = description;
        this.enter = enter;
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
    public String getEnter() {
        return this.enter;
    }

    public String printTask() {
        return " [T]"+"["+getStatusIcon()+"] " + getDescription();
    }
}
