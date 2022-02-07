import java.util.Scanner;

public class TaskHandler {
    private static final String greeting = "Hello! I'm Duke, What can I do for you?";
    private static final String bye = "Bye. Hope to see you again soon!";
    private static final String tasks = "Here are the tasks in your list:";
    private static final String mark = "Nice! I've marked this task as done:";
    private static final String unmark = "OK, I've marked this task as not done yet:";
    private Scanner sc;
    String[] task = new String[100];

    public TaskHandler(){
        this.sc = new Scanner(System.in);
    }
    public String getCommand(){
        return sc.nextLine();
    }
    public String hello(){
        return greeting;
    }
    public String bye(){
        return bye + "\n";
    }
    public String showList() {
        if (TaskList.getTaskLists().size() == 0) {
            return "you do not have any task in the list.";
        }
        String list = "";
        for (int i = 0; i < TaskList.getTaskLists().size(); i++) {
            int number = i + 1;
            list = list + number + "." + "[" + TaskList.getTaskLists().get(i).getIsDone() + "] " + TaskList.getTaskLists().get(i).description + "\n";
        }
        return tasks + "\n" + list;
    }
    public String mark(String t) {
        String[] v1 = t.split(" ");
        TaskList.getTaskLists().get(Integer.parseInt(v1[1]) - 1).markAsDone();
        return mark + "\n" + "[" + TaskList.getTaskLists().get(Integer.parseInt(v1[1]) - 1).getIsDone()+ "] " + TaskList.getTaskLists().get(Integer.parseInt(v1[1]) - 1).description + "\n";
    }
    public String unmark(String t) {
        String[] v1 = t.split(" ");
        TaskList.getTaskLists().get(Integer.parseInt(v1[1]) - 1).unmarkAsDone();
        return unmark + "\n" + "[" + TaskList.getTaskLists().get(Integer.parseInt(v1[1]) - 1).getIsDone()+ "] " + TaskList.getTaskLists().get(Integer.parseInt(v1[1]) - 1).description + "\n";
    }
}
