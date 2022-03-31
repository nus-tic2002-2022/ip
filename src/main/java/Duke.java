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
        ArrayList<Task> taskList = new ArrayList<>();


        boolean start = true;

        while (start) {
            line = in.nextLine();
            System.out.println("---------------------------------");

            String[] splitLine = line.split(" ", 2);
            String firstWord = splitLine[0];
            firstWord = firstWord.toLowerCase();
            String restOfLine = "";
            if (splitLine.length > 1) {
                restOfLine = splitLine[1];
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
                        for (Task task : taskList) {
                            System.out.println(task.getId()+ ". " + task);
                        }
                    }
                    break;
                }
                case "mark": {
                    try{
                        int taskNumber = Integer.parseInt(restOfLine);
//                        if (taskNumber > Task.getNumberOfTask() || taskNumber <= 0) {
//                            System.out.println("task does not exist");
//                        } else {
                        if (taskList.get(taskNumber - 1).getDoneStatus()) {
                            System.out.println("task is already done");
                        } else {
                            taskList.get(taskNumber - 1).markDone();
                            System.out.println("marked task as done");
                        }
                    } catch (IndexOutOfBoundsException e){
                            System.out.println("Task does not exist: " + restOfLine);
                    } catch(NumberFormatException e) {
                        System.out.println("Invalid task number: " + restOfLine);
                    }
                    break;
                }
                case "unmark": {
                    try {
                        int taskNumber = Integer.parseInt(restOfLine);
//                        if (taskNumber > Task.getNumberOfTask() || taskNumber <= 0) {
//                            System.out.println("task does not exist");
//                        } else {
                        if (taskList.get(taskNumber - 1).getDoneStatus()) {
                            taskList.get(taskNumber - 1).unmarkDone();
                            System.out.println("Unmarked task");
                        } else {
                            System.out.println("Task is not yet done");
                        }
                    } catch (IndexOutOfBoundsException e){
                        System.out.println("Task does not exist: " + restOfLine);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid task number: " + restOfLine);
                    }
                    break;
                }
                case "todo":{
                    if(!restOfLine.isEmpty()){
                        Task newTask = new Todo(restOfLine);
                        taskList.add(newTask);
                        System.out.println("Got it. I've added this task:");
                        System.out.println("  " + newTask);
                        System.out.println("Now you have " + Task.getNumberOfTask() + " in the list.");
                    }else{
                        System.out.println("Missing information!");
                    }
                    break;
                }
                case "deadline":{
                    if(!restOfLine.isEmpty()){
                        try{
                            String[] parts = restOfLine.split(" /by ");
                            Task newTask = new Deadline(parts[0], parts[1]);
                            taskList.add(newTask);
                            System.out.println("Got it. I've added this task:");
                            System.out.println("  " + newTask);
                            System.out.println("Now you have " + Task.getNumberOfTask() + " in the list.");
                        } catch(IndexOutOfBoundsException e) {
                            System.out.println("Description or Date is missing: " + restOfLine);
                        }
                    }else{
                        System.out.println("Missing information!");
                    }
                    break;
                }
                case "event":{
                    if(!restOfLine.isEmpty()){
                        try{
                            String[] parts = restOfLine.split(" /at ");
                            Task newTask = new Event(parts[0], parts[1]);
                            taskList.add(newTask);
                            System.out.println("Got it. I've added this task:");
                            System.out.println("  " + newTask);
                            System.out.println("Now you have " + Task.getNumberOfTask() + " in the list.");
                        } catch (IndexOutOfBoundsException e){
                            System.out.println("Description or Date is missing: " + restOfLine);
                        }
                    }else{
                        System.out.println("Missing information!");
                    }
                    break;
                }
                default:
                    System.out.println("please enter a valid command");
            }
            System.out.println("---------------------------------");
        }
    }
}
