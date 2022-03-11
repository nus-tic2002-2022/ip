import java.util.Scanner;
import java.util.ArrayList;
public class Duke {

    //Code Starts here!
    
    //Aesthetic Function
    public static void printLine() {
        System.out.println("__________________________________________________________");
    }

    //Level-2 Added List Array
    public static void system() {
        //Converting to ArrayList
        ArrayList<Task> tasks = new ArrayList<>();
        String line;
        //int counter = 0;
        Scanner in = new Scanner(System.in);

        //Keep the System Running
        while (true) {

            //Ask User for Input
            line = in.nextLine();

                //Print the List
                if (line.toLowerCase().contains("list")) {
                    printLine();
                    System.out.println("Here are the task in your lists:");
                    for (int x = 0; x < tasks.size(); x++) {
                        //Amended to Use toString()
                        System.out.println(x + 1 + ". " + tasks.get(x).toString());
                    }
                    printLine();
                }

                //UnMark the Box
                else if (line.toLowerCase().contains("unmark")) {
                    try {
                        // -1 as Array starts from 0
                        int num2 = Integer.parseInt(line.substring(7)) - 1;
                        Task um = tasks.get(num2);
                        um.markAsNotDone();
                        System.out.println("OK, I've marked this task as not done yet:");
                        System.out.println("[" + um.getStatusIcon() + "] " + um.description);
                    } catch (NullPointerException e) {
                        System.out.println("☹ OOPS!!! Please enter a valid task number.");
                    }
                }

                //Mark the Box
                else if (line.toLowerCase().contains("mark")) {
                    try {
                        // -1 as Array starts from 0
                        int num = Integer.parseInt(line.substring(5)) - 1;
                        Task m = tasks.get(num);
                        m.markAsDone();
                        System.out.println("Nice! I've marked this task as done");
                        System.out.println("[" + m.getStatusIcon() + "] " + m.description);

                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("☹ OOPS!!! Please enter a valid task number.");
                    }
                }

                //Run Deadline Class
                else if (line.toLowerCase().contains("deadline")) {

                    String[] lineSplit = line.split(" ");

                    try {
                        if (lineSplit.length < 2) {
                            throw new DukeException("Missing Deadline Description");
                        }

                        if (!line.contains("/by")){
                            throw new DukeException("Missing Deadline /by");
                        }

                        //Find Position of '/'
                        int slash = line.indexOf("/");

                        //Initiate Deadline Class
                        tasks.add(new Deadline(line.substring(9, slash - 1), line.substring(slash + 4)));
                        System.out.println("Got it. I've added this task:");
                        System.out.println(tasks.get(tasks.size()-1).toString());
                        //counter++;
                        System.out.println("Now you have " + (tasks.size()) + " tasks in the list.");

                    } catch (DukeException e){

                        if (e.getError().equals("Missing Deadline Description")) {
                            System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
                        }

                        if (e.getError().equals("Missing Deadline /by")) {
                            System.out.println("☹ OOPS!!! The /by of a deadline is missing.");
                        }

                    } catch (StringIndexOutOfBoundsException e){
                System.out.println("☹ OOPS!!! The description after /by cannot be missing.");
            }
                }

                //Run Tod0 Class
                else if (line.toLowerCase().contains("todo")) {

                    String[] lineSplit = line.split(" ");

                    //Catch Error for T0do Class
                    try {
                        if (lineSplit.length < 2) {
                            throw new DukeException("Missing Todo Description");
                        }

                        //Initiate Tod0 Class
                        tasks.add(new Todo(line.substring(5)));
                        System.out.println("Got it. I've added this task:");
                        System.out.println(tasks.get(tasks.size()-1).toString());
                        //counter++;
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");

                    } catch (DukeException e) {
                        if (e.getError().equals("Missing Todo Description")) {
                            System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                        }
                    }
                }

                //Run Event Class
                else if (line.toLowerCase().contains("event")) {

                    String[] lineSplit = line.split(" ");

                    try {
                        if (lineSplit.length < 2) {
                            throw new DukeException("Missing Event Description");
                        }

                        if (!line.contains("/at")) {
                            throw new DukeException("Missing Event /at");
                        }

                        //Find Position of '/'
                        int slash = line.indexOf("/");

                        //Initiate Event Class
                        tasks.add(new Event(line.substring(6, slash - 1), line.substring(slash + 4)));
                        System.out.println("Got it. I've added this task:");
                        System.out.println(tasks.get(tasks.size()-1).toString());
                        //counter++;
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");

                    } catch (DukeException e) {
                        if (e.getError().equals("Missing Event Description")) {
                            System.out.println("☹ OOPS!!! The description of an event cannot be empty.");
                        }

                        if (e.getError().equals("Missing Event /at")) {
                            System.out.println("☹ OOPS!!! The /at of an event is missing.");
                        }
                    } catch (StringIndexOutOfBoundsException e){
                        System.out.println("☹ OOPS!!! The description after /at cannot be empty.");
                    }
                }

                //Delete the Task
                else if (line.toLowerCase().contains("delete")){

                    try {
                        // -1 as Array starts from 0
                        int delete = Integer.parseInt(line.substring(7)) - 1;
                        Task d = tasks.get(delete);
                        tasks.remove(delete);
                        System.out.println("Noted. I've removed this task:\n" + d.toString());
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");

                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("☹ OOPS!!! Please enter a valid task number.");
                    }
                }

                //End the System
                else if (line.equals("bye")) {
                    printLine();
                    System.out.println("Bye. Hope to see you again soon!");
                    printLine();
                    System.exit(0);
                }

                //Command Not Recognised
                else {
                    printLine();
                    System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");

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