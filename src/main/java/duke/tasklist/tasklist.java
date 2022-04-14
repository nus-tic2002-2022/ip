package duke.tasklist;

import java.util.ArrayList;

public class tasklist {
    private final ArrayList<Task> TL;

    public tasklist() {
        TL = new ArrayList<>();
    }
    public tasklist(tasklist loadedTaskList) {
        this.TL = loadedTaskList.TL;

    }

    public void addTask(Task task) {
        TL.add(task);
    }


    public void deleteTask(int index) throws IndexOutOfBoundsException {
        TL.remove(index);
    }
    public ArrayList<Task> getAllTask() {
        return TL;
    }

    public int getSize(){return TL.size();}
    public boolean isEmpty() {
        return TL.isEmpty();
    }

    public Task getTask(int index){
        return TL.get(index);
    }
}
