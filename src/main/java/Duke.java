import java.util.Scanner;
import exception.*;
import static input.User.*;

public class Duke {
    public static void main(String[] args) {
        retrieveInitData();
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
        in.close();
    }

    public static void processInput(String input) throws InvalidInputException{
        if(input.equals("list")){
            System.out.println("=========================================" );
            printTask();
            return;
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
            System.out.println("=========================================" );
            System.out.println("Mission added!");
            addSpecificTask(userInputArr[1], userInputArr[0]).printTask();
            System.out.println("Now you have " + getTaskList() + " missions in the list.");

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
            deleteTask(Integer.parseInt(userInputArr[1]) - 1).printTask();
            System.out.println("Now you have " + getTaskList() + " missions in the list.");
        }
        else {
            throw new InvalidInputException();
        }
        saveData();
    }
}
