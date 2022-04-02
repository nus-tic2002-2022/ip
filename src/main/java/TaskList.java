
import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> taskList;


    public TaskList(){
        taskList = new ArrayList<>();
    }

    public int getNumberOfTask(){
        return taskList.size();
    }

    public void addTask(Task task){
        taskList.add(task);
    }

    public void deleteTask(int index) throws IndexOutOfBoundsException{
        taskList.remove(index);
    }

    public void printTask(){
        if(this.getNumberOfTask()==0)
            System.out.println("There is currently nothing in the list");
        else{
            System.out.println("Here are the tasks in your list:");
            for(int i = 0; i < getNumberOfTask(); i++){
                System.out.println((i+1) + ". " + taskList.get(i));
            }
        }
    }

    public Task get(int index) throws IndexOutOfBoundsException{
        return taskList.get(index);
    }

}