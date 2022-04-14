package duke.task;

import duke.importer.TaskFile;
import java.io.IOException;
import java.util.ArrayList;

public class TaskList {

    protected static ArrayList<Task> taskList = new ArrayList<>();
    protected static int numOfTasks;

    /**
     * TaskList Constructor.
     * Maintains the list of tasks.
     * Initializes with 0 task.
     */
    public TaskList() {
        numOfTasks = 0;
    }

    private static int indexer(String userInput){
        assert numOfTasks >= 0 : "number of tasks cannot be less than 0";
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

    /**
     * getNumOfTasks returns number of tasks in the task list.
     *
     * @return number of tasks in the task list
     */
    public static int getNumOfTasks(){
        assert numOfTasks >= 0 : "number of tasks cannot be less than 0";
        return numOfTasks;
    }

    /**
     * list method lists all the task in the task list
     */
    public static void list(){
        assert numOfTasks >= 0 : "number of tasks cannot be less than 0";
        try {
            taskList.clear();
            numOfTasks = 0;
            TaskFile.loadFile();
        } catch (IOException f) {
            System.out.println("Unable to find the save file, loading last known list instead.");
        }
        if(numOfTasks > 0) {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < numOfTasks; i++) {
                System.out.println(i + 1 + "." + taskList.get(i).toString());
            }
        }else{
            System.out.println("☹ OOPS!!! There is no task in your list!");
        }
    }

    /**
     * Mark methods changes the isDone status of the task to "done".
     *
     * @param userInput index of the task according to the task list
     * @see TaskList#list()
     */
    public static void mark(String userInput) {
        int index = indexer(userInput);
        if(index == -1){
            return;
        }
        if (taskList.get(index).isDone) {
            System.out.println("This task was already marked as done:");
        }else {
            taskList.get(index).isDone = true;
            overwriteTaskFile();
            System.out.println("Nice! I've marked this task as done:");
        }
        System.out.println(taskList.get(index).toString());
    }

    /**
     * Mark methods changes the isDone status of the task to not done.
     *
     * @param userInput index of the task according to the task list
     * @see TaskList#list()
     */
    public static void unmark(String userInput) {
        int index = indexer(userInput);
        if(index == -1){
            return;
        }
        if (taskList.get(index).isDone) {
            taskList.get(index).isDone = false;
            overwriteTaskFile();
            System.out.println("OK, I've marked this task as not done yet:");
        }else {
            System.out.println("STATUS ERROR: CANNOT UNMARK PENDING TASK");
        }
        System.out.println(taskList.get(index).toString());
    }

    /**
     * todo method adds a new Todo task to the task list.
     *
     * @param userInput the description of the task
     */
    public static void todo(String userInput){
        String description = userInput.replaceFirst("\\w+\\s", "");
        Todo task = new Todo(description);
        taskList.add(task);
        numOfTasks++;
        System.out.println("Now you have " + numOfTasks + " task(s) in the list.");
    }

    /**
     * deadline method adds a new Deadline task to the task list.
     *
     * @param userInput the description and date/time of the task
     */
    public static void deadline(String userInput){
        String description = userInput.replaceFirst("\\w+\\s", "");
        String date = description.substring(description.indexOf("/by")+3).trim();
        description = description.substring(0,description.indexOf("/by"));
        Deadline task = new Deadline(description, date);
        taskList.add(task);
        numOfTasks++;
        System.out.println("Now you have " + numOfTasks + " task(s) in the list.");
    }

    /**
     * event method adds a new Event task to the task list.
     *
     * @param userInput the description and date/time of the task
     */
    public static void event(String userInput){
        String description = userInput.replaceFirst("\\w+\\s", "");
        String date = description.substring(description.indexOf("/at")+3).trim();
        description = description.substring(0,description.indexOf("/at"));
        Event task = new Event(description, date);
        taskList.add(task);
        numOfTasks++;
        System.out.println("Now you have " + numOfTasks + " task(s) in the list.");
    }

    /**
     * delete method Deletes a task in the task list.
     *
     * @param userInput index of the task according to the task list
     * @see TaskList#list()
     * @see TaskList#overwriteTaskFile()
     * @see TaskFile#overwriteTask()
     */
    public static void delete(String userInput){
        int index = indexer(userInput);
        if(index == -1){
            return;
        }
        System.out.println("Noted. I've removed this task: ");
        System.out.println(taskList.get(index).toString());
        taskList.remove(index);
        numOfTasks--;
        overwriteTaskFile();
        System.out.println("Now you have " + numOfTasks + " task(s) in the list.");
    }

    /**
     * Search for tasks to be done after a given tasks
     * @see DateFunctions#after(Task t)
     * @param userInput is the index of the task in the Task List
     */
    public static void doAfter(String userInput){
        int index = indexer(userInput);
        if(index == -1){
            return;
        }

        if(!taskList.get(index).getTaskType().equals("T")) {
            System.out.println("Tasks to be done after" + taskList.get(index) + "are: ");
            DateFunctions.after(taskList.get(index));
        }else{
            System.out.println("Please query for Deadline or Event tasks that have timestamps!");
        }
    }

    /**
     * Returns tasks that have description matching the user's query
     * @param userInput string of text in the description of the tasks that user wants to find
     */
    public static void find(String userInput){
        String query = userInput.substring(userInput.indexOf(' ')+1);
        ArrayList<Task> results = new ArrayList<>();
        int numOfResults = 0;

        for (Task t : taskList){
            if(t.toString().contains(query)){
                results.add(t);
                numOfResults++;
            }
        }
        if(numOfResults == 0){
            System.out.println("There are no matching results!");
        }else{
            System.out.println("I have found "+numOfResults+" result(s):");
            for(Task t : results){
                System.out.println(t.toString());
            }
        }
    }

    /**
     * importTodo Imports a Todo task from the task file to the task list.
     *
     * @param fileInputDescription the description of the task
     * @param fileInputMark the status of the task
     */
    public static void importTodo(String fileInputDescription, boolean fileInputMark){
        Todo task = new Todo(fileInputDescription, fileInputMark);
        taskList.add(task);
        numOfTasks++;
    }

    /**
     * importDeadline imports a Deadline task from the task file to the task list.
     *
     * @param fileInputDescription the description of the task
     * @param fileInputDate the date/time of the task
     * @param fileInputMark the status of the task
     */
    public static void importDeadline(String fileInputDescription, String fileInputDate, boolean fileInputMark){
        Deadline task = new Deadline(fileInputDescription, fileInputDate, fileInputMark);
        taskList.add(task);
        numOfTasks++;
    }

    /**
     * importEvent imports an Event task from the task file to the task list.
     *
     * @param fileInputDescription the description of the task
     * @param fileInputDate the date/time of the task
     * @param fileInputMark the status of the task
     */
    public static void importEvent(String fileInputDescription, String fileInputDate, boolean fileInputMark){
        Event task = new Event(fileInputDescription, fileInputDate, fileInputMark);
        taskList.add(task);
        numOfTasks++;
    }

    private static void overwriteTaskFile(){
        try {
            TaskFile.overwriteTask();
            for (int i=0;i<numOfTasks;i++) {
                TaskFile.appendTask(taskList.get(i).addToFile());
            }
        }catch(IOException i){
            System.out.println("Unable to make changes to the save file");
        }
    }
}
