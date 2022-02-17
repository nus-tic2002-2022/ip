import java.util.*;

public class TaskList {

    String line = "____________________________________________________________";
    ArrayList<Task> taskList = new ArrayList<>();

    public void addToDo(String description) {
        System.out.println( "Got it. I've added this task: ");
        taskList.add(new ToDo(description));
        System.out.println("[T][ ]:" + description);
        System.out.println( "Now you have "+ taskList.size() +" tasks in the list.");
        System.out.println(line);
    }

    public void addDeadline(String description, String date) {
        System.out.println( "Got it. I've added this task: ");
        taskList.add(new Deadline(description, date));
        System.out.println("[D][ ]:" + description + "(by:" + date + ")");
        System.out.println( "Now you have "+ taskList.size() +" tasks in the list.");
        System.out.println(line);
    }

    public void addEvent(String description, String date) {
        System.out.println( "Got it. I've added this task: ");
        taskList.add(new Event(description, date));
        System.out.println("[E][ ]:" + description + "(at:" + date + ")");
        System.out.println( "Now you have "+ taskList.size() +" tasks in the list.");
        System.out.println(line);
    }

    public void markAsDone(int index) {
        Task i = taskList.get(index-1);
        i.markAsDone();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println("[" + i.getType()+ "]"+"[" + i.getStatusIcon() + "] " + i.getDescription());
        System.out.println(line);
    }

    public void markAsNotDone(int index) {
        Task i = taskList.get(index-1);
        i.markAsNotDone();
        System.out.println("OK, I've marked this task as not done yet: ");
        System.out.println("[" + i.getType()+ "]"+"[" + i.getStatusIcon() + "] " + i.getDescription());
        System.out.println(line);
    }

    public void print() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            Task l = taskList.get(i);
            l.print(i);

        }
    }

}
