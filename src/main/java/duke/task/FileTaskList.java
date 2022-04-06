package duke.task;

public class FileTaskList extends Task{

    public FileTaskList (String description){
        super(description);
    }
    public FileTaskList (String isDone,String description){
        super(isDone,description);
    }
    @Override
    public String toString(){
        return super.toString();
    }
}