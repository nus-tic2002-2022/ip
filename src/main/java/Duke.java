import java.util.Scanner;


public class Duke {

    //Level 1 Greet, Echo, Exit
    private static void bye(){
        System.out.println("Bye. Hope to see you again soon!");
        System.exit(0); //Exit program
    }

    //Level 2 Add, List
    private static void list(Task[] taskList, int numOfTasks){
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < numOfTasks; i++) {
            System.out.println(i+1 + "." + taskList[i].toString());
        }
    }

    //Level 3 Mark as Done
    private static boolean mark(String userInput) {
        boolean isDone = false;
        String mark = userInput.substring(0, userInput.indexOf(' ')).trim();
        if (mark.equals("mark")) {
            System.out.println("Nice! I've marked this task as done:");
            isDone = true;
        }else if (mark.equals("unmark")) {
            System.out.println("OK, I've marked this task as not done yet:");
        }
        return isDone;
    }

    //Level 4 Todos, Events, Deadlines
    private static void addTask(Task[] taskList, int numOfTasks, String userInput){
        String task;
        String date;
        int delimiter;
        if(userInput.contains("/by")){
            delimiter = userInput.indexOf("/by");
            task = userInput.substring(0, delimiter);
            date = userInput.substring(delimiter+3);
            taskList[numOfTasks] = new Deadline(task, date);
        }else if(userInput.contains("/at")){
            delimiter = userInput.indexOf("/at");
            task = userInput.substring(0, delimiter);
            date = userInput.substring(delimiter+3);
            taskList[numOfTasks] = new Event(task, date);
        }else {
            taskList[numOfTasks] = new Todo(userInput);
        }
    }

    //Finding index of taskList
    private static int index(String userInput, int numOfTasks){
        if(numOfTasks == 0){
            System.out.println("There is no task in the list!");
            return 0;
        }
        String listIndexer = userInput;
        listIndexer = listIndexer.replaceAll("[^\\d]","");
        int index = Integer.parseInt(listIndexer);
        if(index > numOfTasks){
            System.out.println("ERROR: The number entered exceeds the number of task(s) in the list!");
            System.out.println( numOfTasks + " task(s) in the list.");
            return 0;
        }
        return index;
    }

    public static void main(String[] args) {

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo + "Hello! I'm Duke\n" + "What can I do for you?");

        Task[] taskList = new Task[100];
        int numOfTasks = 0;

        while(true) {
            Scanner scanInput = new Scanner(System.in); //Scan user input
            String userInput = scanInput.nextLine(); //Read user input

            //Level 1
            if(userInput.equals("bye")){
                Duke.bye();
            }

            //Level 2
            if(userInput.equals("list")){
                Duke.list(taskList, numOfTasks);
                continue;
            }

            //Level 3
            if(userInput.contains("mark")) {
                int index = Duke.index(userInput, numOfTasks) - 1;
                if(index == -1){
                    continue;
                }
                taskList[index].isDone = Duke.mark(userInput);
                System.out.println(taskList[index].toString());
                continue;
            }

            //Adding tasks to list
            Duke.addTask(taskList, numOfTasks, userInput);
            System.out.println("Got it. I've added this task:"); //Echo / add task
            System.out.println(taskList[numOfTasks].toString());
            numOfTasks++;
            System.out.println("Now you have " + numOfTasks + " task(s) in the list.");
        }
    }

}
