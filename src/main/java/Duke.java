import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class Duke {
    public static class Task {
        protected String description;
        protected boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public String getStatusIcon() {
            return (isDone ? "X" : " "); // mark done task with X
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
        public void setIsDone() {
            this.isDone = true;
        }
        public void setNotDone() {
            this.isDone = false;
        }
        public void showTaskStatus() {
            System.out.println("        " + "[" + getStatusIcon() + "] " + getDescription());
        }
    }
    public static void showTaskList(Task[] taskList, int taskCounter){
        int counter = 1;
        for (int i = 0; i < taskCounter; i++) {
            System.out.println("    " + counter + ".[" + taskList[i].getStatusIcon() + "] " + taskList[i].getDescription());
            counter++;
        }
    }
    public static void main(String[] args) {
        /*
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
         */
        Task[] taskList = new Task[100];
        String userInput = "notBye";
        int taskCounter = 0;
        //ArrayList<Task> taskList = new ArrayList<>();
        Scanner in = new Scanner(System.in);

        System.out.println("    ____________________________________________________________");
        System.out.println("    What up! I'm D. Spawn");
        System.out.println("    Something need doing?");
        System.out.println("    ____________________________________________________________");

        do {
            userInput = in.nextLine();
            if(userInput.startsWith("mark")){
                taskList[Integer.parseInt(userInput.substring(5))-1].setIsDone();
                System.out.println("    Job's done:");
                taskList[Integer.parseInt(userInput.substring(5))-1].showTaskStatus();
            } else if(userInput.startsWith("unmark")){
                taskList[Integer.parseInt(userInput.substring(7))-1].setNotDone();
                System.out.println("    Job IS NOT done:");
                taskList[Integer.parseInt(userInput.substring(7))-1].showTaskStatus();
            } else if(userInput.startsWith("list")){
                showTaskList(taskList, taskCounter);
            } else {
                taskList[taskCounter] = new Task(userInput);
                System.out.println("    added: " + userInput); //do stuff here after input
                taskCounter++;
            }

            System.out.println("    ____________________________________________________________");

 /*           switch (userInput) {
                case "list": showTaskList(taskList, taskCounter);
                    break;
                case "mark": userInput
                    break;

                default: taskList[taskCounter] = new Task(userInput);
                    System.out.println("    added: " + userInput); //do stuff here after input
                    taskCounter++;
                    break;
            }*/
            System.out.println("    ____________________________________________________________");


        } while (!userInput.equals("bye"));


        System.out.println("    Ciao!");
        System.out.println("    ____________________________________________________________");
    }
}
