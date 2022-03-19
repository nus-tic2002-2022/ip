package input;

import java.util.ArrayList;

import exception.*;
import model.*;
import static file.Data.*;

public class User {
    private static ArrayList<Task> taskList = new ArrayList<>();

    /**
     * Adding task to the array list based on the type
     *
     * @param desc Description of the task
     * @param type Type of the task
     * @return Created new task
     */
    public static Task addSpecificTask(String desc, String type) throws InvalidInputException{
        if(type.equals("todo")){
            taskList.add(new Todo(desc));
        }
        else if(type.equals("deadline")){
            String[] deadlineArr = desc.split(" /by ");
            if(deadlineArr.length < 2){
                throw new InvalidInputException();
            }
            taskList.add(new Deadline(deadlineArr[0], deadlineArr[1]));
        }
        else if(type.equals("event")){
            String[] eventArr = desc.split(" /at ");
            if(eventArr.length < 2){
                throw new InvalidInputException();
            }
            taskList.add(new Event(eventArr[0], eventArr[1]));
        }
        return taskList.get(taskList.size() - 1);
    }

    /**
     * Print the task within the array list
     *
     */
    public static void printTask(){
        for(int i = 0; i < taskList.size(); i ++){
            System.out.print((i + 1) + ". ");
            taskList.get(i).printTask();
        }
    }

    /**
     * Marking the task as completed/incomplete
     *
     * @param userInput determine which task to be mark
     * @param completed to mark the task as complete/incomplete
     */
    public static void markTask(String userInput, boolean completed){
        int taskNo = Integer.parseInt(userInput.split(" ")[1]) - 1;
        taskList.get(taskNo).setDone(completed);
        taskList.get(taskNo).printTask();
    }

    /**
     * Delete the task
     *
     * @param no determine which task to be delete
     * @return Deleted task
     */
    public static Task deleteTask(int no){
        Task task = taskList.get(no);
        taskList.remove(no);
        return task;
    }

    /**
     * Retrieving the task from text file and store it
     *
     */
    public static void retrieveInitData(){
        taskList = readFile(taskList);
    }

    /**
     * Save the task to the text file
     *
     */
    public static void saveData(){
        writeFile(taskList);
    }

    /**
     * Get the count of the task
     *
     * @return Count of the task
     */
    public static int getTaskList(){
        return taskList.size();
    }
}
