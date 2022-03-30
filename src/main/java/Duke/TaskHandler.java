package Duke;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * Task handler to different tasks
 * added check function to avoid duplicate record insert (individual feature - 1)
 */
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
        if(!checkExist(v1[1])){
            Todo todo = new Todo(v1[1]);
            TaskList.getTaskLists().add(todo);
            return printTask(todo);
        }
        return "Task already added";
    }
    public String deadline(String t) {
        String[] v1 = t.split(" ",2);
        if (v1[1].length() == 0){
            System.out.println("Empty Deadline Task..");
        }
        String[] strArr = v1[1].split("/by",2);
        if(!checkExist(strArr[0])) {
            LocalDate d1 = LocalDate.parse(strArr[1].trim());
            Deadline deadline = new Deadline(strArr[0], d1.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
            TaskList.getTaskLists().add(deadline);
            return printTask(deadline);
        }
        return "Task already added";
    }
    public String event(String t) {
        String[] v1 = t.split(" ",2);
        if (v1[1].length() == 0){
            System.out.println("Empty Event Task..");
        }
        String[] strArr = v1[1].split("/at",2);
        if(!checkExist(strArr[0])) {
            Event event = new Event(strArr[0], strArr[1]);
            TaskList.getTaskLists().add(event);
            return printTask(event);
        }
        return "Task already added";
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
    public String find(String t) {
        String[] v1 = t.split(" ");
        if (v1[1].length() == 0) {
            System.out.println("Unknown input..");
        }
        String list = "";
        for (int i = 0; i < TaskList.getTaskLists().size(); i++) {
            if (TaskList.getTaskLists().get(i).description.toString().contains(v1[1])) {
                int number = i + 1;
                list = list + number + TaskList.getTaskLists().get(i) + "\n";
            }
        }
        if(list != ""){
            return list;
        }
        return "no task found at this moment";
    }

    /**
     *
     * @return
     * /**
     *  * added check function to show tasks completed (individual feature - 2)
     */
    public String show() {
        String list = "";
        for (int i = 0; i < TaskList.getTaskLists().size(); i++) {
            if (TaskList.getTaskLists().get(i).isDone == true) {
                int number = i + 1;
                list = list + number + TaskList.getTaskLists().get(i) + "\n";
            }
        }
        if(list != ""){
            return "Tasks completed so far: \n" + list;
        }
        return "no task complete at this moment";
    }
    public boolean checkExist(String t){
        for (int i = 0; i < TaskList.getTaskLists().size(); i++) {
            if(TaskList.getTaskLists().get(i).description.equals(t)){
                return true;
            }
        }
        return false;
    }
    public String printTask(Task task){
        return "Got it, I've added this task:" + "\n"
                + "  " + task + "\n"
                + "Now you have " + TaskList.getTaskLists().size() + " tasks in the list." + "\n";
    }
}
