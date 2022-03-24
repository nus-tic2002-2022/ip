package duke.utils;

public class CommandList {

    private static final String[] list = {
            "help/? - display this menu",
            "bye - terminate this program",
            "list - list your tasks",
            "mark <index of task> - mark the task as done",
            "unmark <index of task> - unmark the task as not done",
            "delete <index of task> - delete the task from the list",
            "todo <description of task> - create a new todo task",
            "deadline <description of task> /by <date/time> - create a new deadline task",
            "event <description of task> /at <date/time> - create a new event task",
            "ascend - sorts the tasks in ascending chronological order",
            "descend - sorts the tasks in descending chronological order",
            "expired - displays the expired tasks",
            "upcoming - displays the upcoming tasks",
            "search <day> - displays the tasks that are happening or have deadline on that day",
            "search <month> - displays the tasks that are happening or have deadline in that month"
    };
    /**
     * Displays the available commands that Duke understands when the user inputs "help" or "?"
     * @see {@link #list}
     */
    public static void help(){
        System.out.println("Below are commands I currently understand :)");
            for(String str : list){
                System.out.println(str);
            }
        System.out.println("---End of Command List---");
    }
}
