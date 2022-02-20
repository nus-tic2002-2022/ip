import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        System.out.println("Please enter an input: ");
        Scanner in = new Scanner(System.in);
        String str;
        str = in.nextLine();

        Task[] tasks = new Task[100];
        int count = 0;

        while (!str.equals("bye")){
            if(str.equals("list"))
            {
                System.out.println("________________________________");
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < count ; i++ ){
                    System.out.println(i+1 + ". " + tasks[i].toString());
                }

                System.out.println("________________________________");
            }
			else if (str.contains("unmark")) {
                System.out.println("________________________________");
                String[]input = str.split(" ");     // input[0] = mark, input[1] = "2"
                int index = Integer.parseInt(input[1]);
                tasks[index -1].markAsNotDone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(tasks[index-1].toString());
                System.out.println("________________________________");

            }
            else if (str.contains("mark")) {
                System.out.println("________________________________");
                String[]input = str.split(" ");     // input[0] = mark, input[1] = "2"
                int index = Integer.parseInt(input[1]);
                tasks[index -1].markAsDone();
                System.out.println("Nice! I've marked this as done:");
                System.out.println(tasks[index-1].toString());
                System.out.println("________________________________");

            } else if (str.contains("todo")) {
                System.out.println("________________________________");
                String[]input = str.split(" ", 2);     // input[0] = todo, input[1] = borrow book
                Task todo = new ToDo(input[1]);

                tasks[count] = todo;
                count++;

                System.out.println("Got it. I've added this task: ");
                System.out.println(todo.toString());
                System.out.println("Now you have " + count + " task in the list.");
                System.out.println("________________________________");
            } else if (str.contains("deadline")) {
                System.out.println("________________________________");
                String[]input1 = str.split(" ", 2);     // input1[0] = deadline, input1[1] = return book /by Sunday
                String[]input2 = input1[1].split(" /by ", 2);     // input2[0] = return book, input2[1] = Sunday

                Task deadline = new Deadline(input2[0], input2[1]);

                tasks[count] = deadline;
                count++;

                System.out.println("Got it. I've added this task: ");
                System.out.println(deadline.toString());
                System.out.println("Now you have " + count + " task in the list.");
                System.out.println("________________________________");
            } else if (str.contains("event")) {
                System.out.println("________________________________");
                String[]input1 = str.split(" ", 2);     // input1[0] = event, input1[1] = project meeting /at Mon 2-4pm
                String[]input2 = input1[1].split(" /at ", 2);     // input2[0] = project meeting, input2[1] = Mon 2-4pm

                Task event = new Event(input2[0], input2[1]);

                tasks[count] = event;
                count++;

                System.out.println("Got it. I've added this task: ");
                System.out.println(event.toString());
                System.out.println("Now you have " + count + " task in the list.");
                System.out.println("________________________________");
            }
            else {
                System.out.println("Please enter an input");
            }
            
            str = in.nextLine();
        }
        System.out.println("________________________________\n");
        System.out.println("Bye. Hope to see you again soon!");
    }

}
