package duke.task;

public abstract class Task {

    enum taskType {
        TODOS,DEADLINES,EVENTS
    }

    protected String task;
    protected boolean done;

    public Task(String task) {
        this.task = task;
        this.done = false;
    };

    public void setDone(boolean status) {
        this.done = status;
    };

    public String getTask(){
        return this.task;
    }

    public boolean getStatus(){
        return this.done;
    }

    public String toString(){
        return String.format("[%s] %s", (done?"x":" "), this.task);
    }

    public abstract String taskToSaveFile();

}
//to consider: make tasks an abstract class?