import java.util.Scanner;

public class Duke {

    private static Task[] tasks = new Task[100];
    private static int taskCount = 0;

    public static void main(String[] args) {
        System.out.println("-______________________-||");
        System.out.println("Hello! I am the most superior AI~~ Friday");
        System.out.println("What can I do for you?");
        Scanner in = new Scanner(System.in);
        String input = "";
        input = in.nextLine();

        while(!input.equals("bye")){
            processInput(input);
            input = in.nextLine();
        }
        System.out.println("=========================================" );
        System.out.println("Bye. Hope to see you again! Love you 3000 <3");
    }

    public static void addTask(String desc){
        tasks[taskCount] = new Task(desc);
        taskCount++;
    }

    public static void addSpecificTask(String desc, String type){
        if(type.equals("todo")){
            tasks[taskCount] = new Todo(desc);
            taskCount++;
        }
        else if(type.equals("deadline")){
            String[] deadlineArr = desc.split(" /by ");
            tasks[taskCount] = new Deadline(deadlineArr[0], deadlineArr[1]);
            taskCount++;
        }
        else if(type.equals("event")){
            String[] eventArr = desc.split(" /at ");
            tasks[taskCount] = new Event(eventArr[0], eventArr[1]);
            taskCount++;
        }
    }

    public static void printTask(){
        for(int i = 0; i < taskCount; i ++){
            System.out.print((i + 1) + ". ");
            tasks[i].printTask();
        }
    }

    public static void markTask(String userInput, boolean completed){
        int taskNo = Integer.parseInt(userInput.split(" ")[1]) - 1;
        tasks[taskNo].setDone(completed);
        tasks[taskNo].printTask();
    }

    public static void processInput(String input){
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
            System.out.println("=========================================" );
            addSpecificTask(userInputArr[1], userInputArr[0]);
            System.out.println("Mission added!");
            tasks[taskCount - 1].printTask();
            System.out.println("Now you have " + taskCount + " missions in the list.");

        }
        else {
            System.out.println("=========================================" );
            addTask(input);
            System.out.println("Mission added!");
            tasks[taskCount - 1].printTask();
            System.out.println("Now you have " + taskCount + " missions in the list.");
        }
    }
}
