package duke.utils;

/**
 * Displays useful things on the screen
 */
public class Ui {

    public Ui() {
        showWelcome();
    }

    private void showWelcome(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo + "Hello! I'm Duke\n" + "What can I do for you?");
    }

    /**
     * Lists the days of the week.
     * Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday.
     */
    protected static void showDays(){
        System.out.println("Check out the list of valid days below: ");
        System.out.println("monday\ntuesday\nwednesday\nthursday\nfriday\nsaturday\nsunday");
    }

    /**
     * Lists the months of the year.
     * January, February, March, April, May, June, July, August, September, October, November, December.
     */
    protected static void showMonths(){
        System.out.println("Check out the list of valid months below: ");
        System.out.println("january\nfebruary\nmarch\napril\nmay\njune");
        System.out.println("july\naugust\nseptember\noctober\nnovember\ndecember");
    }

    /**
     * Displays the available commands that Duke understands when the user inputs "help" or "?".
     *
     * @see duke.task.TaskList#list
     */
    protected static void help(){
        System.out.println("Below are commands I currently understand :)");
        for(String str : list){
            System.out.println(str);
        }
        System.out.println("---End of Command List---");
    }

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
            "search <month> - displays the tasks that are happening or have deadline in that month",
            "after <index of task> - displays the tasks that are to be done after the queried task",
            "find <keyword> - finds task(s) based on a keyword"
    };
}
