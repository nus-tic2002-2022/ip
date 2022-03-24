package duke.utils;

public class CommandList {

    private static final String[] list = {
            "help - display this menu",
            "bye - terminate this program",
            "list - list your tasks",
            "mark <index of task> - mark the task as done",
            "unmark <index of task> - unmark the task as not done",
            "delete <index of task> - delete the task from the list",
            "todo <description of task> - create a new todo task",
            "deadline <description of task> /by <date/time> - create a new deadline task",
            "event <description of task> /at <date/time> - create a new event task"
    };

    public static void help(){
        System.out.println("Below are commands I currently understand :)");
            for(String str : list){
                System.out.println(str);
            }
        System.out.println("---End of Command List---");
    }
}
