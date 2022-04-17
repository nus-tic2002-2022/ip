package duke;

public class CommandList {

    private static String[] list = {
            "Help - Display a list of accepted commands",
            "Bye - Terminate the Programe",
            "List - Display Tasklist",
            "Find - Return matched task(s) based on input",
            "Mark <index of task> - Mark the task as completed",
            "Unmark <index of task> - Mark the task as incomplete",
            "Todo <description of task> - Create a new task (Todo)",
            "Deadline <description of task> /by <date/time> - Create a new task (deadline)",
            "DoAfter <description of task> /after <Task> - Create a new task (doafter)",
            "Event <description of task> /at <date/time> - Create a new task (event)",
            "Remind <no. of day> - Remind user for the deadline task",
            "Delete <index of task> - Delete the task from the Tasklist",
    };

    /**
     * Printing out the contents of the list via iteration
     */
    public static void help(){
        System.out.println("Below are the list of accepted commands:");
        for(String str : list){
            System.out.println(str);
        }
    }
}