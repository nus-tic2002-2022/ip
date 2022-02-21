import java.util.*;

public class Task {

    //ArrayList<Todo> Task = new ArrayList<>(); // ArrayList of Tasks

    protected String description;
    protected boolean isDone;
    //protected String taskType;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    //public String getTaskType() { return (taskType); }

    public String getDescription() {
        return description;
    }

    public boolean getIsDone() { return isDone; }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsNotDone() {
        isDone = false;
    }

    public void getTask () {
        System.out.println("[" + getStatusIcon() + "] " + getDescription());
    }

    ////waittttt

/*
    public void addTask (String description) {
        Task.add(new Todo(description));
    }
*/
    /*public void addDeadline (String description) {
        Task.add(new Deadline(description));
    }*/

/*  public void getNewTask () { //changed to getTask()
        System.out.println("Nice! I've added this task: ");
        System.out.println("[" + Task.get(Task.size()-1).getTaskType() + "] [" + Task.get(Task.size()-1).getStatusIcon() + "] " + Task.get(Task.size()-1).getDescription());
        System.out.println("Now you have " + Task.size() + " task(s) in the list.");
    }

    public void markAsDone (int index) {
        Task.get(index-1).markAsDone();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println("[" + Task.get(Task.size()-1).getTaskType() + "] [" +Task.get(index-1).getStatusIcon() + "] " + Task.get(index-1).getDescription());
    }

    public void markAsNotDone (int index) {
        Task.get(index-1).markAsNotDone();
        System.out.println("OK, I've marked this task as not done yet: ");
        System.out.println("[" + Task.get(Task.size()-1).getTaskType() + "] [" + Task.get(index-1).getStatusIcon() + "] " + Task.get(index-1).getDescription());
    }

    public void getTodoList() {
        // Iterate over Task ArrayList
        if (Task.size() == 0) {
            System.out.println("There is nothing on the list! :)");
        } else {
            for (int i = 0; i < Task.size(); i++) {
                System.out.println(i + 1 + ".[" + Task.get(Task.size() - 1).getTaskType() + "] [" + Task.get(i).getStatusIcon() + "] " + Task.get(i).getDescription());
            }
        }
        System.out.println("Now you have " + Task.size() + " task(s) in the list.");
    }*/

}
