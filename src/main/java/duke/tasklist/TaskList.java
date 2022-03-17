package duke.tasklist;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    List<Task> taskList;

    public TaskList(ArrayList<Task> tasks) {
        this.taskList = tasks;
    }

    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    public List<Task> getTaskList(){
        return this.taskList;
    }

    public int size(){
        return this.taskList.size();
    }

    public Task get(int index){
        return this.taskList.get(index);
    }

    public void delete(int index){
        this.taskList.remove(index);
    }

    public void add(Task task){
        this.taskList.add(task);
    }

    @Override
    public String toString() {
        String output = "";
        if (taskList.isEmpty()) {
            output = "Empty";
        }
        else {
            for (int index = 0; index < taskList.size(); index++) {
                int cur_index = index + 1;
                output = output + "\t\t\t\t\t\t\t\t\t "+ cur_index + ". " + taskList.get(index) + "\n";
            }
        }
        return output;
    }
}