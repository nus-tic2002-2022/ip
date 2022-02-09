import java.util.*;

public class Todo {

    ArrayList<Task> Todo = new ArrayList<>(); // ArrayList of Tasks

    public void addTask (String description) {
        Todo.add(new Task(description));
    }

    public void markAsDone (int index) {
        Todo.get(index-1).markAsDone();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println("[" + Todo.get(index-1).getStatusIcon() + "] " + Todo.get(index-1).getDescription());
    }

    public void markAsNotDone (int index) {
        Todo.get(index-1).markAsNotDone();
        System.out.println("OK, I've marked this task as not done yet: ");
        System.out.println("[" + Todo.get(index-1).getStatusIcon() + "] " + Todo.get(index-1).getDescription());
    }


    public void getTodoList() {
        // Iterate over Todo ArrayList
        for (int i = 0; i < Todo.size(); i++) {
            System.out.println(i+1 + ". [" + Todo.get(i).getStatusIcon() + "] " + Todo.get(i).getDescription());
        }
    }


}
