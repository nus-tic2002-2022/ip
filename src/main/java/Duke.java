import java.util.Scanner;

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
        TaskList taskList = new TaskList();

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
                    taskList.printTask();
                    break;
                }
                case "mark": {
                    try{
                        int taskNumber = Integer.parseInt(restOfLine);
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
                        taskList.addTask(newTask);
                        System.out.println("Got it. I've added this task:");
                        System.out.println("  " + newTask);
                        System.out.println("Now you have " + taskList.getNumberOfTask() + " in the list.");
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
                            taskList.addTask(newTask);
                            System.out.println("Got it. I've added this task:");
                            System.out.println("  " + newTask);
                            System.out.println("Now you have " + taskList.getNumberOfTask() + " in the list.");
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
                            taskList.addTask(newTask);
                            System.out.println("Got it. I've added this task:");
                            System.out.println("  " + newTask);
                            System.out.println("Now you have " + taskList.getNumberOfTask() + " in the list.");
                        } catch (IndexOutOfBoundsException e){
                            System.out.println("Description or Date is missing: " + restOfLine);
                        }
                    }else{
                        System.out.println("Missing information!");
                    }
                    break;
                }
                case "delete": {
                    try {
                        int taskNumber = Integer.parseInt(restOfLine);
                        Task taskToDelete = taskList.get(taskNumber-1);
                        taskList.deleteTask(taskNumber-1);


                        System.out.println("Noted. I've removed this task:");
                        System.out.println("  " + taskToDelete);
                        System.out.println("Now you have " + taskList.getNumberOfTask() + " in the list.");

                    }catch (IndexOutOfBoundsException e){
                        System.out.println("Task does not exist: " + restOfLine);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid task number: " + restOfLine);
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
