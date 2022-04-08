package input;

import exception.InvalidDateException;
import exception.InvalidInputException;
import model.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import static file.Data.readFile;
import static file.Data.writeFile;

public class User {
    private static final ArrayList<Task> taskList = new ArrayList<>();

    /**
     * Adding task to the array list based on the type.
     *
     * @param desc Description of the task
     * @param type Type of the task
     * @return Created new task
     */
    public static Task addSpecificTask(String desc, String type) throws InvalidInputException, InvalidDateException {
        LocalDate inputDate;
        switch (type) {
        case "todo":
            taskList.add(new Todo(desc));
            break;
        case "deadline":
            String[] deadlineArr = desc.split(" /by ");

            if (deadlineArr.length < 2) {
                throw new InvalidInputException();
            }

            try {
                inputDate = LocalDate.parse(deadlineArr[1]);
            } catch (DateTimeParseException e) {
                throw new InvalidDateException();
            }

            taskList.add(new Deadline(deadlineArr[0], inputDate));
            break;
        case "event":
            String[] eventArr = desc.split(" /at ");

            if (eventArr.length < 2) {
                throw new InvalidInputException();
            }

            try {
                inputDate = LocalDate.parse(eventArr[1]);
            } catch (DateTimeParseException e) {
                throw new InvalidDateException();
            }

            taskList.add(new Event(eventArr[0], inputDate));
            break;
        case "duration":
            String[] durationArr = desc.split(" /for ");

            if (durationArr.length < 2) {
                throw new InvalidInputException();
            }

            taskList.add(new Duration(durationArr[0], durationArr[1]));
            break;
        default:
            assert false;
        }
        return taskList.get(taskList.size() - 1);
    }

    /**
     * Print the task within the array list.
     *
     */
    public static void printTask(){
        for(int i = 0; i < taskList.size(); i ++){
            System.out.print((i + 1) + ". ");
            taskList.get(i).printTask();
        }
    }

    /**
     * Marking the task as completed/incomplete.
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
     * Delete the task.
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
     * Retrieve the list of task based on the date.
     *
     * @param userInput user input field
     * @return List of task
     */
    public static ArrayList<String> viewTaskByDate(String userInput) throws InvalidDateException, InvalidInputException {
        ArrayList<String> result = new ArrayList<>();
        LocalDate date;
        String[] viewTaskArr = userInput.split("/for ");
        if(viewTaskArr.length < 2){
            throw new InvalidInputException();
        }

        try {
            date = LocalDate.parse(viewTaskArr[1]);
        } catch (DateTimeParseException e) {
            throw new InvalidDateException();
        }

        for (Task task : taskList) {
            if (task instanceof Deadline) {
                LocalDate taskDeadline = ((Deadline) task).getBy();
                if (taskDeadline.compareTo(date) < 1) {
                    result.add(task.toString());
                }
            } else if(task instanceof Event){
                LocalDate taskPeriod = ((Event) task).getPeriod();
                if(taskPeriod.compareTo(date) == 0){
                    result.add(task.toString());
                }
            }
        }
        return result;
    }

    /**
     * Retrieve the list of task based on the keyword.
     *
     * @param userInput user input field
     * @return List of task
     */
    public static ArrayList<String> viewTaskByKeyword(String userInput) throws InvalidInputException {
        ArrayList<String> result = new ArrayList<>();
        if(userInput.isEmpty()){
            throw new InvalidInputException();
        }

        for (Task task : taskList) {
            if (task.getDescription().contains(userInput)) {
                result.add(task.toString());
            }
        }
        return result;
    }

    /**
     * Retrieving the task from text file and store it.
     *
     */
    public static void retrieveInitData(){
        readFile(taskList);
    }

    /**
     * Save the task to the text file.
     *
     */
    public static void saveData(){
        writeFile(taskList);
    }

    /**
     * Get the count of the task.
     *
     * @return Count of the task
     */
    public static int getTaskList(){
        return taskList.size();
    }
}
