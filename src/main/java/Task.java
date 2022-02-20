public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected String m_type;

    protected void setType(String type) {
        m_type = type;
    }

    public String getType() {
        return m_type;
    }


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

    public void markAsNotDone(){
        this.isDone = false;
    }

    public abstract void print(int index);

}
