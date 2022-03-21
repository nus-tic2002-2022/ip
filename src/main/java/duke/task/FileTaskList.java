package duke.task;

public class FileTaskList extends Task{

    public FileTaskList (String description){
        super(description);
    }

    @Override
    public String toString(){
        return super.fileListToString();
    }
}