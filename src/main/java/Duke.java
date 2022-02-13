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

            if (line.equals("bye")) {
                separatingLine();
                System.out.println("Bye. Glad to be of service to you!\n" +
                        "Hope to assist you again soon, Boss!");
                separatingLine();
                System.exit(0);
            }

            //Print the List:
            else if (line.equals("list") && taskList[0] != null) {
                separatingLine();
                System.out.println("Hey Boss! I have summarised your task list as below:");
                for (int i = 0; i < counter; i++) {
                    System.out.println(i + 1 + ". " +
                            "[" +
                            taskIcon[i].getStatusIcon() +
                            "] " +
                            taskList[i]);
                }
                separatingLine();

            } else if (line.equals("list") && taskList[0] == null) {
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
        System.out.println("Hello! This is Duke here!\n" +
                "Your trusted personal task assistant!\n" +
                "What can I do to assist you today?\n" +
                "(Please input your task or type 'list' to retrieve your task list)");
        separatingLine();

        // perform system logic
        system();

    }

}
