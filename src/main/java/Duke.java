import java.util.Scanner;

public class Duke {

    // separating line function:
    public static void separatingLine() {
        System.out.println("__________________________________________________________");
    }

    public static void system() {

        String[] taskList = new String[100];
        Task[] taskIcon = new Task[100];
        String line;
        int counter = 0;
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
            else if (line.equals("list") && taskIcon[0] != null) {
                separatingLine();
                System.out.println("Hey Boss! I have summarised your task list as below:");
                for (int i = 0; i < counter; i++) {

                    // below codes are used from level-4 onwards:
                    System.out.println(i + 1 + ". " + taskIcon[i].toString());

                    // below codes are used before level-4 implementation:
                    //System.out.println(i + 1 + ". " +
                    //        "[" +
                    //        taskIcon[i].getStatusIcon() +
                    //        "] " +
                    //        taskList[i]);
                }
                separatingLine();

            } else if (line.equals("list") && taskIcon[0] == null) {
                separatingLine();
                System.out.println("Your current task list is empty, Boss!\n" +
                        "Please start to add your tasks below ^<>^");
                separatingLine();
            }

            // unmark task
            else if (line.contains("unmark")) {

                int index1 = Integer.parseInt(line.substring(7)) - 1;
                Task ud = taskIcon[index1];
                ud.markAsNotDone();
                System.out.println("Great, job completed, Boss!\n" +
                        "I've marked this task as not done yet for you:");
                System.out.println("[" + ud.getStatusIcon() + "] " + ud.description);
                separatingLine();

            }

            // mark task
            else if (line.contains("mark")) {

                int index2 = Integer.parseInt(line.substring(5)) - 1;
                Task d = taskIcon[index2];
                d.markAsDone();
                System.out.println("Great, job completed, Boss!\n" +
                        "I've marked this task as done for you:");
                System.out.println("[" + d.getStatusIcon() + "] " + d.description);
                separatingLine();

            }

            //Add Deadline execution:
            else if (line.contains("deadline")){

                //Find the index number of '/'
                int index3 = line.indexOf("/");

                //Initiate Deadline Class
                taskIcon[counter] = new Deadline(line.substring(9, index3-1),line.substring(index3+4));
                System.out.println("Hey Boss! Got it. I've added this task for you:");
                System.out.println(taskIcon[counter].toString());
                counter++;
                System.out.println("Now you have " + (counter) + " tasks in total within the list.");
            }

            //Add Tod0 execution:
            else if (line.contains("todo")){

                //Initiate Tod0 Class
                taskIcon[counter] = new Todo(line.substring(5));
                System.out.println("Hey Boss! Got it. I've added this task for you:");
                System.out.println(taskIcon[counter].toString());
                counter++;
                System.out.println("Now you have " + (counter) + " tasks in total within the list.");
            }

            //Add Event Execution:
            else if (line.contains("event")){

                //Find Position of '/'
                int index4 = line.indexOf("/");

                //Initiate Event Class
                taskIcon[counter] = new Event(line.substring(6, index4-1),line.substring(index4+4));
                System.out.println("Hey Boss! Got it. I've added this task for you:");
                System.out.println(taskIcon[counter].toString());
                counter++;
                System.out.println("Now you have " + (counter) + " tasks in total within the list.");
            }

            //Add user input to task list:
            else {
                separatingLine();
                System.out.println("The below task has been added to your task list:\n " +
                        line);

                //assign to task list from input for printing out task description when request for printing lists:
                taskList[counter] = line;

                //Add to task for separate icon tracking and print status mark:
                Task t = new Task(line);
                t.description = line;
                taskIcon[counter] = t;

                //update counter to show sequence numbering when print result:
                separatingLine();
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
