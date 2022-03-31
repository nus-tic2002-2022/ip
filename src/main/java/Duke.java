import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("---------------------------------");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("---------------------------------");

        String line;
        Scanner in = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<Task>();


        boolean start = true;

        while (start) {
            line = in.nextLine();
            System.out.println("---------------------------------");

            String[] splittedLine = line.split(" ", 2);
            String firstWord = splittedLine[0];
            firstWord = firstWord.toLowerCase();
            String restOfLine = "";
            if (splittedLine.length > 1) {
                restOfLine = splittedLine[1];
            }

            switch (firstWord) {
                case "bye": {
                    System.out.println("Bye. Hope to see you again soon!");
                    start = false;
                    in.close();
                    break;
                }
                case "list": {
                    if (Task.getNumberOfTask() == 0) {
                        System.out.println("There is currently nothing in the list");
                    } else {
                        System.out.println("Here are the tasks in your list:");
                        for (int i = 0; i < taskList.size(); i++) {
                            System.out.println(taskList.get(i).getId()+ ". " + taskList.get(i));
                        }
                    }
                    break;
                }
                case "mark": {
                    if (isNumeric(restOfLine)) {
                        int taskNumber = Integer.parseInt(restOfLine);
                        if (taskNumber > Task.getNumberOfTask() || taskNumber <= 0) {
                            System.out.println("task does not exist");
                        } else {
                            if (taskList.get(taskNumber - 1).getDoneStatus()) {
                                System.out.println("task is already done");
                            } else {
                                taskList.get(taskNumber - 1).markDone();
                                System.out.println("marked task as done");
                            }
                        }
                    } else {
                        System.out.println("invalid task number");
                    }
                    break;
                }
                case "unmark": {
                    if (isNumeric(restOfLine)) {
                        int taskNumber = Integer.parseInt(restOfLine);
                        if (taskNumber > Task.getNumberOfTask() || taskNumber <= 0) {
                            System.out.println("task does not exist");
                        } else {
                            if (taskList.get(taskNumber - 1).getDoneStatus()) {
                                taskList.get(taskNumber - 1).unmarkDone();
                                System.out.println("Unmarked task");
                            } else {
                                System.out.println("task is not yet done");
                            }
                        }
                    } else {
                        System.out.println("invalid task number");
                    }
                    break;
                }
                case "todo":{
                    if(!restOfLine.isEmpty()){
                        Task newTask = new Todo(restOfLine);
                        taskList.add(newTask);
                        System.out.println("Got it. I've added this task:");
                        System.out.println("  " + newTask.toString());
                        System.out.println("Now you have " + Task.getNumberOfTask() + " in the list.");
                    }else{
                        System.out.println("Error!");
                    }
                    break;
                }
                case "deadline":{
                    if(!restOfLine.isEmpty()){
                        if(restOfLine.contains(" /by ")){
                            String[] parts = restOfLine.split(" /by ");
                            Task newTask = new Deadline(parts[0], parts[1]);
                            taskList.add(newTask);
                            System.out.println("Got it. I've added this task:");
                            System.out.println("  " + newTask.toString());
                            System.out.println("Now you have " + Task.getNumberOfTask() + " in the list.");
                        }else{
                            System.out.println("Missing by date");
                        }
                    }else{
                        System.out.println("Error!");
                    }
                    break;
                }
                case "event":{
                    if(!restOfLine.isEmpty()){
                        if(restOfLine.contains(" /at ")){
                            String[] parts = restOfLine.split(" /at ");
                            Task newTask = new Event(parts[0], parts[1]);
                            taskList.add(newTask);
                            System.out.println("Got it. I've added this task:");
                            System.out.println("  " + newTask.toString());
                            System.out.println("Now you have " + Task.getNumberOfTask() + " in the list.");
                        }else{
                            System.out.println("Missing at what date");
                        }
                    }else{
                        System.out.println("Error!");
                    }
                    break;
                }
                default:
                    System.out.println("please enter a valid command");
            }
            System.out.println("---------------------------------");
        }
    }

    public static boolean isNumeric(String str) { 
        try {  
            Integer.parseInt(str);  
            return true;
        } catch(NumberFormatException e){  
            return false;  
        }  
      }
}
