package classes;

public class ToDos extends Task {
    public ToDos (String description) {
        super(description);
    }

    /**
     * Getter for description
     * **/
    public String getDescription() {
        return description;
    }

    /**
     * Getter for type of task
     * **/
    public String getType() {
        return "T";
    }
}
