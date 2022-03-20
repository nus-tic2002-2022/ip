import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    // separating line function:
    public static void separatingLine() {
        System.out.println("__________________________________________________________");
    }

    public static void system() {

        // codes before level-6 implementation:
        // String[] taskList = new String[100];
        // Task[] taskIcon = new Task[100];
        // String line;
        // int counter = 0;
        // Scanner in = new Scanner(System.in);

        // codes from level-6 implementation onwards:
        // using ArrayList for level-6 implementation:
        ArrayList<Task> taskIcon = new ArrayList<>();
        String line;
        Scanner in = new Scanner(System.in);

        while (true) {

            //request user to input:
            line = in.nextLine();


            //Exit system if input "bye":
            if (line.equals("bye")) {
                separatingLine();
                System.out.println("Bye. Glad to be of service to you!\n" +
                        "Hope to assist you again soon, Boss!");
                separatingLine();
                System.exit(0);
            }

            //Print the List:
            else if (line.equals("list") && taskIcon.size() != 0) {
                separatingLine();
                System.out.println("Hey Boss! I have summarised your task list as below:");
                for (int i = 0; i < taskIcon.size(); i++) {

                    // below codes are used from level-6 onwards:
                    System.out.println(i + 1 + ". " + taskIcon.get(i).toString());

                    // below codes are used before level-4 implementation:
                    //System.out.println(i + 1 + ". " +
                    //        "[" +
                    //        taskIcon[i].getStatusIcon() +
                    //        "] " +
                    //        taskList[i]);
                }
                separatingLine();

            } else if (line.equals("list") && taskIcon.size() == 0) {
                separatingLine();
                System.out.println("Your current task list is empty, Boss!\n" +
                        "Please start to add your tasks below ^<>^");
                separatingLine();
            }

            // unmark task (with error handling):
            else if (line.contains("unmark")) {

                try {
                    int index1 = Integer.parseInt(line.substring(7)) - 1;
                    Task ud = taskIcon.get(index1);
                    ud.markAsNotDone();
                    System.out.println("Great, job completed, Boss!\n" +
                            "I've marked this task as not done yet for you:");
                    System.out.println("[" + ud.getStatusIcon() + "] " + ud.description);
                    separatingLine();
                }

                // input invalid task number:
                catch (IndexOutOfBoundsException e) {

                    System.out.println("SORRY Boss! You do not have Task No. " +
                            (Integer.parseInt(line.substring(7))) +
                            " in your task list!\n" +
                            "You only have a total of " + (taskIcon.size()) + " Task(s) in your List!\n" +
                            "Please only enter an existing task number again, Boss!");
                    separatingLine();
                }
            }

            // mark task (with error handling):
            else if (line.contains("mark")) {

                try {
                    int index2 = Integer.parseInt(line.substring(5)) - 1;
                    Task d = taskIcon.get(index2);
                    d.markAsDone();
                    System.out.println("Great, job completed, Boss!\n" +
                            "I've marked this task as done for you:");
                    System.out.println("[" + d.getStatusIcon() + "] " + d.description);
                    separatingLine();
                }

                // input invalid task number:
                catch (IndexOutOfBoundsException e) {
                    System.out.println("SORRY Boss! You do not have Task No. " +
                            (Integer.parseInt(line.substring(5))) +
                            " in your task list!\n" +
                            "You only have a total of " + (taskIcon.size()) + " Task(s) in your List!\n" +
                            "Please only enter an existing task number again, Boss!");
                    separatingLine();
                }
            }

            //Add Deadline execution (with error handling):
            else if (line.contains("deadline")){

                String[] lineSplit = line.split(" ");
                String[] lineSplit2 = line.split("/");

                try {

                    // after deadline no input of task description:
                    if (lineSplit.length < 2) {
                        throw new DukeException("Invalid Input (Deadline Description)");
                    }

                    // missing of "/by" for deadline input:
                    if (!line.contains("/by")){
                        throw new DukeException("Missing Deadline");
                    }

                    String[] lineSplit3 = lineSplit2[1].split(" ");
                    // no spacing after "deadline" and "/by":
                    if (lineSplit[0].length() > 8 || lineSplit3[0].length() > 3) {
                        throw new DukeException("Invalid Format (Deadline)");
                    }

                    //Find the index number of '/'
                    int index3 = line.indexOf("/");

                    //Initiate Deadline Class
                    taskIcon.add(new Deadline(line.substring(9, index3-1),line.substring(index3+4)));
                    System.out.println("Hey Boss! Got it. I've added this task for you:");
                    System.out.println(taskIcon.get(taskIcon.size()-1).toString());
                    // counter++;
                    System.out.println("Now you have " + (taskIcon.size()) + " tasks in total within the list.");
                    separatingLine();

                }

                catch (DukeException e) {

                    if (e.getError().equals("Invalid Input (Deadline Description)")) {
                        System.out.println("SORRY Boss! You have entered an invalid input (Missing Task Descriptions)!\n" +
                                "Please enter the task description after 'deadline' for this entry!\n" +
                                "Kindly try again, Boss!");
                        separatingLine();
                    }

                    if (e.getError().equals("Missing Deadline")) {
                        System.out.println("SORRY Boss! You have entered an invalid input (Missing Deadline)!\n" +
                                "Please enter the task deadline with space before and after '/by' for this entry!\n" +
                                "Example: 'deadline clean house /by 22 March 2022'\n" +
                                "Kindly try again, Boss!");
                        separatingLine();
                    }

                    if (e.getError().equals("Invalid Format (Deadline)")) {
                        System.out.println("SORRY Boss! You have entered an invalid input (Wrong Format)!\n" +
                                "Please enter the deadline description & deadline date with space after 'deadline' & '/by' for this entry!\n" +
                                "Example: 'deadline clean house /by 22 March 2022'\n" +
                                "Kindly try again, Boss!");
                        separatingLine();
                    }

                }

            }

            //Add Tod0 execution (with error handling):
            else if (line.contains("todo")){

                String[] TestInput = line.split(" ");

                //Catch Error for T0do Class
                try {
                    // after 'tod0' no input of task description:
                    if (TestInput.length < 2) {
                        throw new DukeException("Invalid Input (todo)");
                    }

                    // after 'tod0' no spacing:
                    if (TestInput[0].length() > 4) {
                        throw new DukeException("Invalid Format (todo)");
                    }

                    //Initiate Tod0 Class
                    taskIcon.add(new Todo(line.substring(5)));
                    System.out.println("Hey Boss! Got it. I've added this task for you:");
                    System.out.println(taskIcon.get(taskIcon.size()-1).toString());
                    // counter++;
                    System.out.println("Now you have " + (taskIcon.size()) + " tasks in total within the list.");
                    separatingLine();
                }
                catch (DukeException e) {

                    if (e.getError().equals("Invalid Input (todo)")) {
                        System.out.println("SORRY Boss! You have entered an invalid input (Missing Descriptions)!\n" +
                                "Please enter the task description after 'todo' for this entry!\n" +
                                "Kindly try again, Boss!");
                        separatingLine();
                    }
                    if (e.getError().equals("Invalid Format (todo)")) {
                        System.out.println("SORRY Boss! You have entered an invalid input (Wrong Format)!\n" +
                                "Please enter the task description with space after 'todo' for this entry!\n" +
                                "Example: 'todo clean house'\n" +
                                "Kindly try again, Boss!");
                        separatingLine();

                    }
                }
            }

            //Add Event Execution (with error handling):
            else if (line.contains("event")){

                try {

                    String[] lineSplit = line.split(" ");
                    String[] lineSplit2 = line.split("/");

                    // after event no input of task description:
                    if (lineSplit.length < 2) {
                        throw new DukeException("Invalid Input (Event Description)");
                    }

                    // missing of "/at" for event input:
                    if (!line.contains("/at")){
                        throw new DukeException("Missing Event Date/Period");
                    }

                    String[] lineSplit3 = lineSplit2[1].split(" ");
                    // no spacing after "event" and "/at":
                    if (lineSplit[0].length() > 5 || lineSplit3[0].length() > 3) {
                        throw new DukeException("Invalid Format (Event)");
                    }

                    //Find Position of '/'
                    int index4 = line.indexOf("/");

                    //Initiate Event Class
                    taskIcon.add(new Event(line.substring(6, index4-1),line.substring(index4+4)));
                    System.out.println("Hey Boss! Got it. I've added this task for you:");
                    System.out.println(taskIcon.get(taskIcon.size()-1).toString());
                    // counter++;
                    System.out.println("Now you have " + (taskIcon.size()) + " tasks in total within the list.");
                    separatingLine();
                }

                catch (DukeException e) {

                    if (e.getError().equals("Invalid Input (Event Description)")) {
                        System.out.println("SORRY Boss! You have entered an invalid input (Missing Task Descriptions)!\n" +
                                "Please enter the task description after 'event' for this entry!\n" +
                                "Kindly try again, Boss!");
                        separatingLine();
                    }

                    if (e.getError().equals("Missing Event Date/Period")) {
                        System.out.println("SORRY Boss! You have entered an invalid input (Missing Date/Period)!\n" +
                                "Please enter the event date/period with space before and after '/at' for this entry!\n" +
                                "Example: 'event clean house /at 22 March 2022, 2-6pm'\n" +
                                "Kindly try again, Boss!");
                        separatingLine();
                    }

                    if (e.getError().equals("Invalid Format (Event)")) {
                        System.out.println("SORRY Boss! You have entered an invalid input (Wrong Format)!\n" +
                                "Please enter the event description & event date/period with space after 'event' & '/at' for this entry!\n" +
                                "Example: 'event clean house /at 22 March 2022, 2-6pm'\n" +
                                "Kindly try again, Boss!");
                        separatingLine();
                    }

                }

            }

            // add delete function (with error handling):
            else if (line.contains("delete")) {

                try {

                    // -1 as Array starts from 0
                    int delete = Integer.parseInt(line.substring(7)) - 1;
                    Task del = taskIcon.get(delete);
                    taskIcon.remove(delete);
                    System.out.println("Hey Boss! Got it. I've deleted this task for you:");
                    System.out.println(del.toString());
                    System.out.println("Now you have " + taskIcon.size() + " tasks in the list.");

                }

                catch (IndexOutOfBoundsException e) {
                    System.out.println("SORRY Boss! You do not have Task No. " +
                            (Integer.parseInt(line.substring(7))) +
                            " in your task list!\n" +
                            "You only have a total of " + (taskIcon.size()) + " Task(s) in your List!\n" +
                            "Please only enter an existing task number again, Boss!");
                    separatingLine();
                }

            }

            //below codes are implemented before level-5:
            //Add user input to task list:
            // else {
            //  separatingLine();
            //  System.out.println("The below task has been added to your task list:\n " +
            //          line);

                //assign to task list from input for printing out task description when request for printing lists:
                //taskList[counter] = line;

                //Add to task for separate icon tracking and print status mark:
                //Task t = new Task(line);
                //t.description = line;
                //taskIcon[counter] = t;

                //update counter to show sequence numbering when print result:
                //separatingLine();
                //counter = counter + 1;
            //}

        }

    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        separatingLine();
        System.out.println("Hello, Boss! This is Duke here!\n" +
                "Your trusted personal task assistant!\n" +
                "What can I do to assist you today, Boss?\n" +
                "(Please input your task or type 'list' to retrieve your task list)");
        separatingLine();

        // perform system logic
        system();

    }

}
