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

        while (start){
            line = in.nextLine();
            System.out.println("---------------------------------");

            String[] splittedLine = line.split(" ",2);
            String firstWord = splittedLine[0];
            firstWord = firstWord.toLowerCase();
            String restOfLine = "";
            if(splittedLine.length> 1) {
                restOfLine = splittedLine[1];
            }

            if(firstWord.equals("bye")){
                System.out.println("Bye. Hope to see you again soon!");
                start = false;
                in.close();
            }else if(firstWord.equals("list")){
                if(Task.getNumberOfTask() == 0){
                    System.out.println("There is currently nothing in the list");
                }
                for(int i = 0; i < taskList.size(); i++){
                    System.out.println(taskList.get(i));
                }
            }else if (firstWord.equals("mark")){
                if(isNumeric(restOfLine)){
                    int taskNumber = Integer.parseInt(restOfLine);
                    if(taskNumber > Task.getNumberOfTask() || taskNumber <= 0){
                        System.out.println("task does not exist");
                    }else{
                        if(taskList.get(taskNumber - 1).getDoneStatus()){
                            System.out.println("task is already done");
                        }else {
                            taskList.get(taskNumber - 1).markDone();
                            System.out.println("marked task as done");
                        }
                    }
                }else{
                    System.out.println("invalid task number");
                }
            }else if(firstWord.equals("unmark")){
                if(isNumeric(restOfLine)){
                    int taskNumber = Integer.parseInt(restOfLine);
                    if(taskNumber > Task.getNumberOfTask() || taskNumber <= 0){
                        System.out.println("task does not exist");
                    }else{
                        if(taskList.get(taskNumber - 1).getDoneStatus()){
                            taskList.get(taskNumber - 1).unmarkDone();
                            System.out.println("Unmarked task");
                        }else {
                            System.out.println("task is not yet done");
                        }
                    }
                }else{
                    System.out.println("invalid task number");
                }
            }else{
                Task newTask = new Task(line);
                taskList.add(newTask);
                System.out.println("added: " + line);
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
