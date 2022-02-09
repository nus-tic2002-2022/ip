public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    public Task() {
        this.description = "";
        this.isDone = false;
    }

    public String getTask(){
        return this.description;
    }

    public boolean setTaskStatus(boolean isDone) {
        if (this.isDone == isDone) {
            return false;
        }
        else {
            this.isDone = isDone;
            return true;
        }
    }
    public boolean getTaskStatus() {
        return this.isDone;
    }

    @Override
    public String toString() {
        String type = "T";
        String status = "";
        if (this.getTaskStatus()) {
            status = "[Done]\t";
        }
        else {
            status = "[ ]\t\t";
        }

        return "[" + type + "]\t" + status + "" + this.description;
    }
}