import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        System.out.println("Please enter an input: ");
        Scanner in = new Scanner(System.in);
        String str;
        str = in.nextLine();

        List<Task> tasks = new ArrayList<>();
        // int count = 0;

        while (!str.equals("bye")) {
            if(str.equals("list")) {
                System.out.println("________________________________");
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < tasks.size() ; i++ ){
                    System.out.println(i+1 + ". " + tasks.get(i).toString());
                }

                System.out.println("________________________________");
            } else if (str.contains("unmark")) {
                System.out.println("________________________________");
                try {
                    String[]input = str.split(" ");     // input[0] = mark, input[1] = "2"
                    
                    int index = Integer.parseInt(input[1]);
                    tasks.get(index-1).markAsNotDone();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(tasks.get(index-1).toString());
                    System.out.println("________________________________");
                } catch (Exception e) {
                    System.err.println("☹ OOPS!!! The description of a unmark cannot be empty.");
                    System.out.println("________________________________");
                }
            } else if (str.contains("mark")) {
                System.out.println("________________________________");
                try {
                    String[]input = str.split(" ");     // input[0] = mark, input[1] = "2"
                    int index = Integer.parseInt(input[1]);
                    tasks.get(index-1).markAsDone();
                    System.out.println("Nice! I've marked this as done:");
                    System.out.println(tasks.get(index-1).toString());
                    System.out.println("________________________________");
                } catch (Exception e) {
                    System.err.println("☹ OOPS!!! The description of a mark cannot be empty.");
                    System.out.println("________________________________");
                }

            } else if (str.contains("todo")) {
                System.out.println("________________________________");
                try {
                    String[]input = str.split(" ", 2);     // input[0] = todo, input[1] = borrow book
                    Task todo = new ToDo(input[1]);

                    tasks.add(todo);
                    // tasks[count] = todo;
                    // count++;

                    System.out.println("Got it. I've added this task: ");
                    System.out.println(todo.toString());
                    System.out.println("Now you have " + tasks.size() + " task in the list.");
                    System.out.println("________________________________");
                } catch (Exception e) {
                    System.err.println("☹ OOPS!!! The description of a todo cannot be empty.");
                    System.out.println("________________________________");
                }
                
            } else if (str.contains("deadline")) {
                System.out.println("________________________________");
                try {
                    String[]input1 = str.split(" ", 2);     // input1[0] = deadline, input1[1] = return book /by Sunday
                    String[]input2 = input1[1].split(" /by ", 2);     // input2[0] = return book, input2[1] = Sunday

                    Task deadline = new Deadline(input2[0], input2[1]);

                    tasks.add(deadline);
                    // tasks[count] = deadline;
                    // count++;

                    System.out.println("Got it. I've added this task: ");
                    System.out.println(deadline.toString());
                    System.out.println("Now you have " + tasks.size() + " task in the list.");
                    System.out.println("________________________________");
                } catch (Exception e) {
                    System.err.println("☹ OOPS!!! The description of a deadline cannot be empty.");
                    System.out.println("________________________________");
                }

            } else if (str.contains("event")) {
                System.out.println("________________________________");
                try {
                    String[]input1 = str.split(" ", 2);     // input1[0] = event, input1[1] = project meeting /at Mon 2-4pm
                    String[]input2 = input1[1].split(" /at ", 2);     // input2[0] = project meeting, input2[1] = Mon 2-4pm

                    Task event = new Event(input2[0], input2[1]);

                    tasks.add(event);
                    // tasks[count] = event;
                    // count++;

                    System.out.println("Got it. I've added this task: ");
                    System.out.println(event.toString());
                    System.out.println("Now you have " + tasks.size() + " task in the list.");
                    System.out.println("________________________________");
                } catch (Exception e) {
                    System.err.println("☹ OOPS!!! The description of a event cannot be empty.");
                    System.out.println("________________________________");
                }
            } else if (str.contains("delete")) {
                System.out.println("________________________________");
                try {
                    String[]input = str.split(" ");     // input[0] = mark, input[1] = "2"
                    
                    int index = Integer.parseInt(input[1]);
                    Task deletedTask = tasks.get(index-1);

                    tasks.remove(index-1);

                    System.out.println("Noted. I've removed this task:");
                    System.out.println("    " + deletedTask.toString());
                    System.out.println("Now you have " + tasks.size() + " task in the list.");
                    System.out.println("________________________________");                   
                } catch (Exception e) {
                    System.err.println("☹ OOPS!!! The description of a delete cannot be empty.");
                    System.out.println("________________________________");
                }
            } else {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                System.out.println("________________________________");
            }
            
            str = in.nextLine();
        }
        System.out.println("________________________________\n");
        System.out.println("Bye. Hope to see you again soon!");
    }

}
