package Duke;
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
            //list = list + number + "." + "[" + TaskList.getTaskLists().get(i).getIsDone() + "] " + TaskList.getTaskLists().get(i).description + "\n";
            list = list + number + TaskList.getTaskLists().get(i) + "\n";
        }
        return tasks + "\n" + list;
    }
    public String mark(String t) {
        String[] v1 = t.split(" ");
        TaskList.getTaskLists().get(Integer.parseInt(v1[1]) - 1).markAsDone();
        return mark + "\n" + TaskList.getTaskLists().get(Integer.parseInt(v1[1]) - 1).toString();
        //return mark + "\n" + "[" + TaskList.getTaskLists().get(Integer.parseInt(v1[1]) - 1).getIsDone()+ "] " + TaskList.getTaskLists().get(Integer.parseInt(v1[1]) - 1).description + "\n";
    }
    public String unmark(String t) {
        String[] v1 = t.split(" ");
        TaskList.getTaskLists().get(Integer.parseInt(v1[1]) - 1).unmarkAsDone();
        return unmark + "\n" + TaskList.getTaskLists().get(Integer.parseInt(v1[1]) - 1).toString();
        //return unmark + "\n" + "[" + TaskList.getTaskLists().get(Integer.parseInt(v1[1]) - 1).getIsDone()+ "] " + TaskList.getTaskLists().get(Integer.parseInt(v1[1]) - 1).description + "\n";
    }
    public String todo(String t) {
        String[] v1 = t.split(" ",2);
        if (v1[1].length() == 0){
            System.out.println("Empty Todo Task..");
        }
        Todo todo = new Todo(v1[1]);
        TaskList.getTaskLists().add(todo);
        return printTask(todo);
    }
    public String deadline(String t) {
        String[] v1 = t.split(" ",2);
        if (v1[1].length() == 0){
            System.out.println("Empty Deadline Task..");
        }
        String[] strArr = v1[1].split("/by",2);
        Deadline deadline = new Deadline(strArr[0], strArr[1]);
        TaskList.getTaskLists().add(deadline);
        return printTask(deadline);
    }
    public String event(String t) {
        String[] v1 = t.split(" ",2);
        if (v1[1].length() == 0){
            System.out.println("Empty Event Task..");
        }
        String[] strArr = v1[1].split("/at",2);
        Event event = new Event(strArr[0], strArr[1]);
        TaskList.getTaskLists().add(event);
        return printTask(event);
    }
    public String delete(String t) {
        String[] v1 = t.split(" ");
        if (v1[1].length() == 0){
            System.out.println("Unknown input..");
        }
        int v =Integer.valueOf(v1[1]);
        String task =  TaskList.getTaskLists().get(v) + "\n";
        TaskList.getTaskLists().remove(v);
        return "Noted. I've removed this task:" + "\n" +task + "Now you have " + TaskList.getTaskLists().size() + " tasks in the list.";
    }
    public String printTask(Task task){
        return "Got it, I've added this task:" + "\n"
                + "  " + task + "\n"
                + "Now you have " + TaskList.getTaskLists().size() + " tasks in the list." + "\n";
    }
}
