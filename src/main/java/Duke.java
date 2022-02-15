import java.util.Scanner;
public class Duke {

    //Code Starts here!
    
    //Aesthetic Function
    public static void printLine() {
        System.out.println("__________________________________________________________");
    }

    //Level-2 Added List Array
    public static void system() {
        Task[] tasks = new Task[100];
        String line;
        int counter= 0;
        Scanner in = new Scanner(System.in);

        //Keep the System Running
        while (true) {
            //Ask User for Input
            line = in.nextLine();

            //Print the List
            if (line.equals("list")) {
                printLine();
                System.out.println("Here are the taks in your lists:");
                for (int x = 0; x < counter; x++) {
                    //Amended to Use toString()
                    System.out.println(x + 1 + ". " + tasks[x].toString());
                }
                printLine();
            }

            //UnMark the Box
            else if (line.contains("unmark")){
                // -1 as Array starts from 0
                int num2 = Integer.parseInt(line.substring(7)) - 1;
                Task um = tasks[num2];
                um.markAsNotDone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("[" + um.getStatusIcon() + "] " + um.description);
            }

            //Mark the Box
            else if (line.contains("mark")){
                // -1 as Array starts from 0
                int num = Integer.parseInt(line.substring(5)) - 1;
                Task m = tasks[num];
                m.markAsDone();
                System.out.println("Nice! I've marked this task as done");
                System.out.println("[" + m.getStatusIcon() + "] " + m.description);
            }

            //Run Deadline Class
            else if (line.contains("deadline")){

                //Find Position of '/'
                int slash = line.indexOf("/");

                //Initiate Deadline Class
                tasks[counter] = new Deadline(line.substring(9, slash-1),line.substring(slash+4));
                System.out.println("Got it. I've added this task:");
                System.out.println(tasks[counter].toString());
                counter++;
                System.out.println("Now you have " + (counter) + " tasks in the list.");
            }

            //Run Tod0 Class
            else if (line.contains("todo")){

                //Initiate Tod0 Class
                tasks[counter] = new Todo(line.substring(5));
                System.out.println("Got it. I've added this task:");
                System.out.println(tasks[counter].toString());
                counter++;
                System.out.println("Now you have " + (counter) + " tasks in the list.");
            }

            //Run Event Class
            else if (line.contains("event")){

                //Find Position of '/'
                int slash = line.indexOf("/");

                //Initiate Event Class
                tasks[counter] = new Deadline(line.substring(6, slash-1),line.substring(slash+4));
                System.out.println("Got it. I've added this task:");
                System.out.println(tasks[counter].toString());
                counter++;
                System.out.println("Now you have " + (counter) + " tasks in the list.");
            }

            //End the System
            else if (line.equals("bye")) {
                printLine();
                System.out.println("Bye. Hope to see you again soon!");
                printLine();
                System.exit(0);
            }

            //Add to the List
            else {
                printLine();
                System.out.println("added: " + line);

                //Add to Task > Description
                Task t = new Task(line);
                t.description = line;
                tasks[counter] = t;

                printLine();
                counter = counter + 1;
            }
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        printLine();
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        printLine();

        //Trigger Code
        system();
    }
}