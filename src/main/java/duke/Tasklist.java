package duke;

import java.util.ArrayList;
import duke.*;

public class Tasklist {
    protected ArrayList<Task> taskArr;

    public Tasklist(){
    }

    //This class takes in an ArrayList and passes the value to the protected ArrayList taskArr.
    public Tasklist(ArrayList<Task> taskArr){
        this.taskArr = taskArr;
    }

    //This method prints out all values found in the taskArr
    public void list(){
        for(int i=0;i<taskArr.size();i++){
            System.out.println((i+1) + ". " + taskArr.get(i).getDescription());
        }
    }

    public void list(String find){
        int number = 1;
        for(int i=0;i<taskArr.size();i++){
            if(taskArr.get(i).getDescription().contains(find)){
                System.out.println(number + ". " + taskArr.get(i).getDescription());
                number++;
            }
        }
    }

    //This method is used to mark an item on the taskArr. There is an input validation to check if the number supplied is within bounds of the Array List.
    public void mark(int i){
        if(i <= taskArr.size() && i != 0){
            taskArr.get(i-1).setDone(true);
            System.out.println("Nice! I've marked this task as done:\n" + taskArr.get(i-1).getDescription());
        }else{
            System.out.println("Out of range!");
        }
    }

    //This method is used to unmark an item on the taskArr. There is an input validation to check if the number supplied is within bounds of the Array List.
    public void unmark(int i){
        if(i <= taskArr.size() && i != 0){
            taskArr.get(i-1).setDone(false);
            System.out.println("OK, I've marked this task as not done yet:\n" + taskArr.get(i-1).getDescription());
        }else{
            System.out.println("Out of range!");
        }
    }

    //This method is used to delete an item on the taskArr. There is an input validation to check if the number supplied is within bounds of the Array List.
    public void delete(int i){
        if(i <= taskArr.size() && i != 0){
            System.out.println("Noted. I've removed this task: \n" + taskArr.get(i-1).getDescription() + "\nNow you have " + (taskArr.size() - 1) + " tasks in the list.");
            taskArr.remove(i-1);
        }else{
            System.out.println("Out of range!");
        }
    }

    //function to delete all tasks in tasklist, used for the C-Archive feature.
    public void deleteAll(){
        taskArr.clear();
        System.out.println("The tasks list will now start new!");
    }

    //Used to add a new task.
    public void add(Task task){
        taskArr.add(task);
    }

    //Used to get a new task.
    public Task get(int i){
        return taskArr.get(i);
    }

    //Used to return the size of the taskArr.
    public int size(){
        return taskArr.size();
    }
}
