package duke.tasklist;

import java.util.ArrayList;

public class tasklist {
    private final ArrayList<task> TL;

    public tasklist() {
        TL = new ArrayList<>();
    }
    public tasklist(tasklist loadedTaskList) {
        this.TL = loadedTaskList.TL;

    }
    public void addTask(task task) {
        TL.add(task);
    }

    public void deleteTask(int index) throws IndexOutOfBoundsException {
        TL.remove(index);
    }

    public int getSize(){return TL.size();}
    public boolean isEmpty() {
        return TL.isEmpty();
    }

    public task getTask(int index){
        return TL.get(index);
    }

    public tasklist findTask(String word){
        tasklist newTL= new tasklist();
        for(int i=0; i<TL.size();i++){
            if(TL.get(i).getDescription().contains(word)){
                newTL.addTask(TL.get(i));
            }
        }
        return newTL;
    }
}
