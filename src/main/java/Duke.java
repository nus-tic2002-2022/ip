import java.util.Scanner;


public class Duke {

    //Level 1 Greet, Echo, Exit
    private static void bye(){
        System.out.println("Bye. Hope to see you again soon!");
        System.exit(0); //Exit program
    }

    //Level 2 Add, List
    private static void list(Task[] taskList, int numOfTasks){
        if(numOfTasks > 0) {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < numOfTasks; i++) {
                System.out.println(i + 1 + "." + taskList[i].toString());
            }
        }else{
            System.out.println("☹ OOPS!!! There is no task in your list");
        }
    }

    //Level 3 Mark as Done
    private static boolean mark(String userInput, boolean currentMark) {
        boolean isDone = false;
        String mark = userInput.substring(0, userInput.indexOf(' ')).trim();
        if (mark.equals("mark")) {
            if(currentMark){
                System.out.println("This task was already marked as done!:");
            }else {
                System.out.println("Nice! I've marked this task as done:");
            }
            return true;
        }else if (mark.equals("unmark")) {
            if (currentMark) {
                System.out.println("OK, I've marked this task as not done yet:");
            }else {
                System.out.println("STATUS ERROR: CANNOT UNMARK PENDING TASK");
            }
        }
        return isDone;
    }

    //Level 4 Todos, Events, Deadlines
    //Level 5 Handle Errors
    private static void addTask (Task[] taskList, int numOfTasks, String userInput) throws MissingDateError {

        String taskType;
        try {
            taskType = userInput.substring(0, userInput.indexOf(' '));
        } catch (StringIndexOutOfBoundsException s) {
            //Missing description (task)
            taskType = userInput.split(" ")[0];
            System.out.println("☹ OOPS!!! The description of a " + taskType + " cannot be empty.");
            throw new StringIndexOutOfBoundsException();
        }

        String task = userInput.replaceFirst("\\w+\\s", "");
        //Creating new todo task
        if (taskType.equals("todo")){
            taskList[numOfTasks] = new Todo(task);
        }
        //Below checks are for deadlines and events
        int delimiter = 0;
        if(taskType.equals("deadline")){
            delimiter = task.indexOf("/by");
            if(delimiter == -1){
                //Validate /by
                System.out.println("☹ OOPS!!! Please make sure /by is mentioned for " + taskType);
                throw new MissingDateError();
            }
        }else if(taskType.equals("event")){
            delimiter = task.indexOf("/at");
            if(delimiter == -1){
                //Validate /at
                System.out.println("☹ OOPS!!! Please make sure /at is mentioned for " + taskType);
                throw new MissingDateError();
            }
        }

        String date = task.substring(delimiter + 3);
        if(taskType.equals("deadline") || taskType.equals("event")) {
            //Validate if date is empty
            if (date.isBlank()) {
                System.out.println("☹ OOPS!!! The date of a " + taskType + " cannot be empty.");
                throw new MissingDateError();
            }
            //Validate task
            task = task.substring(0, delimiter);
            if (task.isBlank()) {
                System.out.println("☹ OOPS!!! The description of a " + taskType + " cannot be empty.");
                throw new StringIndexOutOfBoundsException();
            }
        }
        //Creating new deadline task
        if(taskType.equals("deadline")){
            taskList[numOfTasks] = new Deadline(task, date);
        }
        //Creating new event task
        if(taskType.equals("event")){
            taskList[numOfTasks] = new Event(task, date);
        }

        System.out.println("Got it. I've added this task:"); //Echo / add task
        System.out.println(taskList[numOfTasks].toString());
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

    //Get the first word of the user input string
    private static String command(String userInput){
        return userInput.split(" ")[0];
    }

    //Check if it's the Mark Command
    private static boolean isMark (String command){
        return (command.equals("mark") || command.equals("unmark"));
    }

    //Check if it's a Task
    private static boolean isTask (String command){
        return (command.equals("todo") || command.equals("event") || command.equals("deadline"));
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
            String userInput = scanInput.nextLine().trim(); //Read user input
            String command = Duke.command(userInput);

            //Level 1 exit
            if(command.equals("bye")){
                Duke.bye();
            }

            //Level 2 List the task
            if(command.equals("list")){
                Duke.list(taskList, numOfTasks);
                continue;
            }

            //Level 3 Mark or Unmark
            if(Duke.isMark(command)) {
                int index = Duke.index(userInput, numOfTasks) - 1;
                if(index == -1){
                    continue;
                }
                taskList[index].isDone = Duke.mark(userInput, taskList[index].isDone);
                System.out.println(taskList[index].toString());
                continue;
            }
            //Level 4 Adding tasks to list
            if(Duke.isTask(command)) {
                try{
                    Duke.addTask(taskList, numOfTasks, userInput);
                    numOfTasks++;
                    System.out.println("Now you have " + numOfTasks + " task(s) in the list.");
                    continue;
                } catch (StringIndexOutOfBoundsException s){
                    continue;
                } catch (MissingDateError m) {
                    continue;
                }
            }

            //Level 5 Else, unrecognized command
            System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

}
