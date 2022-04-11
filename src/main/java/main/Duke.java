package main;

import exception.InvalidDateException;
import exception.InvalidInputException;

import java.util.ArrayList;
import java.util.Scanner;

import static input.User.*;

public class Duke {
    /**
     * Main Program
     */
    public static void main(String[] args) {
        retrieveInitData();
        System.out.println("-______________________-||");
        System.out.println("Hello! I am the most superior AI~~ Friday");
        System.out.println("What can I do for you?");
        Scanner in = new Scanner(System.in);
        String input;
        input = in.nextLine();

        while(!input.equals("bye")){
            try {
                processInput(input);
            } catch (InvalidInputException | InvalidDateException e) {
                System.out.println("=========================================" );
                System.out.println(e.getMessage());
            }
            System.out.println("=========================================" );
            System.out.println("What can I do for you?");
            input = in.nextLine();
        }
        System.out.println("=========================================" );
        System.out.println("Bye. Hope to see you again! Love you 3000 <3");
        in.close();
    }

    /**
     * Process the input provided by user.
     *
     * @param input user input
     */
    public static void processInput(String input) throws InvalidInputException, InvalidDateException {
        if(input.equals("list")){
            System.out.println("=========================================" );
            if(getTaskList() == 0){
                System.out.println("You are good to go! No task is added.");
            }
            printTask();
            return;
        } else if(input.startsWith("mark")){
            System.out.println("=========================================" );
            System.out.println("That's pretty fast");
            markTask(input, true);
        } else if(input.startsWith("unmark")){
            System.out.println("=========================================" );
            System.out.println("Oh, it is not done?");
            markTask(input, false);
        } else if(input.startsWith("todo") || input.startsWith("event") || input.startsWith("deadline") || input.startsWith("duration")){
            String[] userInputArr = input.split(" ", 2);
            if(userInputArr.length < 2 || userInputArr[1].trim().isEmpty()){
                System.out.println("=========================================" );
                System.out.println("No task is provided.");
                return;
            }
            addSpecificTask(userInputArr[1], userInputArr[0]).printTask();
            System.out.println("=========================================" );
            System.out.println("Mission added!");
            System.out.println("Now you have " + getTaskList() + " missions in the list.");
        } else if(input.startsWith("delete")){
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
        } else if(input.startsWith("viewtask")){
            String[] userInputArr = input.split(" ", 2);
            if(userInputArr.length < 2 || userInputArr[1].trim().isEmpty()){
                System.out.println("=========================================" );
                System.out.println("No date is provided.");
                return;
            }
            ArrayList<String> taskStringList = viewTaskByDate(userInputArr[1]);
            System.out.println("=========================================" );
            if(taskStringList.size() == 0){
                System.out.println("No task being schedule for the date.");
                return;
            }
            System.out.println("The task deadline being scheduled before: " );
            for(int i = 0; i < taskStringList.size(); i ++) {
                System.out.print((i + 1) + ". ");
                System.out.println(taskStringList.get(i));
            }
        } else if(input.startsWith("find")){
            String[] userInputArr = input.split(" ", 2);
            if(userInputArr.length < 2 || userInputArr[1].trim().isEmpty()){
                System.out.println("=========================================" );
                System.out.println("No keyword is provided.");
                return;
            }
            ArrayList<String> taskStringList = viewTaskByKeyword(userInputArr[1]);
            System.out.println("=========================================" );
            if(taskStringList.size() == 0){
                System.out.println("No task being found.");
                return;
            }
            System.out.println("These are the matching task: " );
            for(int i = 0; i < taskStringList.size(); i ++) {
                System.out.print((i + 1) + ". ");
                System.out.println(taskStringList.get(i));
            }
        } else {
            throw new InvalidInputException();
        }
        saveData();
    }
}
