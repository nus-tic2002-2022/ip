import java.util.*;
public class TaskList extends Task {

    String line = "____________________________________________________________";
    ArrayList<Task> taskList = new ArrayList<>();

    public void addTask(String description) {
       taskList.add(new Task(description));
        System.out.println("added: " + description);
        System.out.println(line);

    }

    public void markAsDone(int index) {
        taskList.get(index-1).markAsDone();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println("[" + taskList.get(index-1).getStatusIcon() + "] " + taskList.get(index-1).getDescription());
        System.out.println(line);
    }

    public void markAsNotDone(int index) {
        taskList.get(index-1).markAsNotDone();
        System.out.println("OK, I've marked this task as not done yet: ");
        System.out.println("[" + taskList.get(index-1).getStatusIcon() + "] " + taskList.get(index-1).getDescription());
        System.out.println(line);
    }

    public void printList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            Task print = taskList.get(i);
            System.out.println(i+1 + ". [" + print.getStatusIcon() + "] " + print.getDescription());
        }
    }
}
