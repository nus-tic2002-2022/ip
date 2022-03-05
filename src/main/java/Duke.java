import java.util.Scanner;
import java.util.ArrayList;
import model.*;
import exception.*;

public class Duke {
    private static ArrayList<Task> taskList = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("-______________________-||");
        System.out.println("Hello! I am the most superior AI~~ Friday");
        System.out.println("What can I do for you?");
        Scanner in = new Scanner(System.in);
        String input = "";
        input = in.nextLine();

        while(!input.equals("bye")){
            try {
                processInput(input);
            } catch (InvalidInputException e) {
                System.out.println("=========================================" );
                System.out.println("What are you trying to here?? Please review your input.");
            }
            System.out.println("=========================================" );
            System.out.println("What can I do for you?");
            input = in.nextLine();
        }
        System.out.println("=========================================" );
        System.out.println("Bye. Hope to see you again! Love you 3000 <3");
    }

    public static void addTask(String desc){
        taskList.add(new Task(desc));
    }

    public static void addSpecificTask(String desc, String type) throws InvalidInputException{
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
    }

    public static void printTask(){
        for(int i = 0; i < taskList.size(); i ++){
            System.out.print((i + 1) + ". ");
            taskList.get(i).printTask();
        }
    }

    public static void markTask(String userInput, boolean completed){
        int taskNo = Integer.parseInt(userInput.split(" ")[1]) - 1;
        taskList.get(taskNo).setDone(completed);
        taskList.get(taskNo).printTask();
    }

    public static void deleteTask(int no){
        taskList.remove(no);
    }

    public static void processInput(String input) throws InvalidInputException{
        if(input.equals("list")){
            System.out.println("=========================================" );
            printTask();
        }
        else if(input.startsWith("mark")){
            System.out.println("=========================================" );
            System.out.println("That's pretty fast");
            markTask(input, true);
        }
        else if(input.startsWith("unmark")){
            System.out.println("=========================================" );
            System.out.println("Oh, it is not done?");
            markTask(input, false);
        }
        else if(input.startsWith("todo") || input.startsWith("event") || input.startsWith("deadline")){
            String[] userInputArr = input.split(" ", 2);
            if(userInputArr.length < 2 || userInputArr[1].trim().isEmpty()){
                System.out.println("=========================================" );
                System.out.println("No task is provided.");
                return;
            }
            addSpecificTask(userInputArr[1], userInputArr[0]);
            System.out.println("=========================================" );
            System.out.println("Mission added!");
            taskList.get(taskList.size() - 1).printTask();
            System.out.println("Now you have " + taskList.size() + " missions in the list.");

        }
        else if(input.startsWith("delete")){
            String[] userInputArr = input.split(" ", 2);
            if(userInputArr.length < 2 || userInputArr[1].trim().isEmpty()){
                System.out.println("=========================================" );
                System.out.println("No task number is provided.");
                return;
            }
            System.out.println("=========================================" );
            System.out.println("Mission deleted!");
            taskList.get(Integer.parseInt(userInputArr[1]) - 1).printTask();
            deleteTask(Integer.parseInt(userInputArr[1]) - 1);
            System.out.println("Now you have " + taskList.size() + " missions in the list.");
        }
        else {
            throw new InvalidInputException();
        }
    }
}
