import java.util.*;

public class Duke {

    private static ArrayList<Task> list = new ArrayList<>(); // ArrayList of Tasks

    public static void main(String[] args) {

        String line;
        Scanner in = new Scanner(System.in);
        int status = 0;
        //String[] list = new String[100];
        int counter = 0;
        //Task task;
        //Task task = new Task();

        int index = 0;

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);

        do {

            System.out.print("What can I do for you? ");

            line = in.nextLine();

            if(line.toLowerCase().equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                status = 1; // can escape
            } else if(line.toLowerCase().equals("list")){
                //Print list -- TODO can put into a function
                if (list.size() == 0) {
                    System.out.println("There is nothing on the list! :)");
                } else {
                    for(int i = 0; i < list.size(); i++){
                        //System.out.println(i + 1 + ".[" + list.get(list.size() - 1).getTaskType() + "] [" + list.get(i).getStatusIcon() + "] " + list.get(i).getDescription());
                        System.out.println(i + 1 + "."); //+ list.get(list.size()-1).getTask()

                    }
                }
                System.out.println("Now you have " + list.size() + " task(s) in the list.");
                continue;
            } else if (line.startsWith("todo ")) {
                //list.addTask(line); //to delete
                list.add(new Todo(line));
                //list.getNewTask(); //to delete
                // print newly added tasks -- TODO Perhaps can make as a function
                System.out.println("Nice! I've added this task: ");
                //System.out.println("[" + list.get(list.size()-1).getTaskType() + "] [" + list.get(list.size()-1).getStatusIcon() + "] " + list.get(list.size()-1).getDescription()); //to delete
                list.get(list.size()-1).getTask();
                System.out.println("Now you have " + list.size() + " task(s) in the list.");
                counter += 1;
                continue;
            } else if (line.startsWith("deadline ")) {
                list.add(new Deadline(line));
                // print newly added tasks -- TODO Perhaps can make as a function
                System.out.println("Nice! I've added this task: ");
                list.get(list.size()-1).getTask();
                System.out.println("Now you have " + list.size() + " task(s) in the list.");
                counter += 1;
                continue;
            } else if (line.startsWith("event ")) {
                list.add(new Event(line));
                // print newly added tasks -- TODO Perhaps can make as a function
                System.out.println("Nice! I've added this task: ");
                list.get(list.size()-1).getTask();
                System.out.println("Now you have " + list.size() + " task(s) in the list.");
                counter += 1;
                continue;
            } else if (line.startsWith("mark ")) {
                index = Integer.parseInt(line.substring(line.indexOf("mark ") + 5, line.length()));
                //list.markAsDone(index); //wait
                continue;
            } else if (line.startsWith("unmark ")) {
                index = Integer.parseInt(line.substring(line.indexOf("unmark ") + 7, line.length()));
                //list.markAsNotDone(index); //wait
                continue;
            }
            else {
                System.out.println("Sorry, I don't understand. Please try the following commands:");
                System.out.println("bye, list, todo <add task here>");
            }

        } while (status == 0);

    }

    /*public void getTodoList() {
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
