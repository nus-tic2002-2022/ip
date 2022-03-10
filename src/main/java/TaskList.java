public class TaskList {

    private static Task[] taskList = new Task[100];
    private static int numOfTasks;

    //Constructor of TaskList
    public TaskList() {
        numOfTasks = 0;
    }

    //Finding index of taskList
    private static int indexer(String userInput){
        if(numOfTasks == 0){
        System.out.println("☹ OOPS!!! There is no task in your list!");
        return -1;
    }
        String listIndexer = userInput;
        listIndexer = listIndexer.replaceAll("[^\\d]","");
        int index = Integer.parseInt(listIndexer);
        if(index > numOfTasks){
            System.out.println("ERROR: The number entered exceeds the number of task(s) in the list!");
            System.out.println( numOfTasks + " task(s) in the list.");
            return -1;
        }
        return index-1;
    }

    //Level 2 List
    public static void list(){
        if(numOfTasks > 0) {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < numOfTasks; i++) {
                System.out.println(i + 1 + "." + taskList[i].toString());
            }
        }else{
            System.out.println("☹ OOPS!!! There is no task in your list!");
        }
    }

    //Level 3 Mark as Done
    public static void mark(String userInput) {
        int index = indexer(userInput);
        if(index == -1){
            return;
        }
        if (taskList[index].isDone) {
            System.out.println("This task was already marked as done:");
        }else {
            taskList[index].isDone = true;
            System.out.println("Nice! I've marked this task as done:");
        }
        System.out.println(taskList[index].toString());
    }

    public static void unmark(String userInput) {
        int index = indexer(userInput);
        if(index == -1){
            return;
        }
        if (taskList[index].isDone) {
            System.out.println("STATUS ERROR: CANNOT UNMARK PENDING TASK");
        }else {
            taskList[index].isDone = false;
            System.out.println("OK, I've marked this task as not done yet:");
        }
        System.out.println(taskList[index].toString());
    }

    //Level 4 Todos, Events, Deadlines
    public static void todo(String userInput){
        String description = userInput.replaceFirst("\\w+\\s", "");
        //Creating new todo task
        taskList[numOfTasks] = new Todo(description);
        numOfTasks++;
        System.out.println("Now you have " + numOfTasks + " task(s) in the list.");
    }

    public static void deadline(String userInput){
        //Creating new deadline task
        String description = userInput.replaceFirst("\\w+\\s", "");
        String date = description.substring(description.indexOf("/by")+3);
        description = description.substring(0,description.indexOf("/by"));
        taskList[numOfTasks] = new Deadline(description, date);
        numOfTasks++;
        System.out.println("Now you have " + numOfTasks + " task(s) in the list.");
    }

    public static void event(String userInput){
        //Creating new event task
        String description = userInput.replaceFirst("\\w+\\s", "");
        String date = description.substring(description.indexOf("/at")+3);
        description = description.substring(0,description.indexOf("/at"));
        taskList[numOfTasks] = new Event(description, date);
        numOfTasks++;
        System.out.println("Now you have " + numOfTasks + " task(s) in the list.");
    }

    //Level 6 Delete
    public static void delete(String userInput){}

}
