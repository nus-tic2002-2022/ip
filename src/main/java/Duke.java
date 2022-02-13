import java.util.Scanner;

public class Duke {

    // separating line function:
    public static void separatingLine() {
        System.out.println("__________________________________________________________");
    }

    public static void system() {

        String[] taskList = new String[100];
        String line;
        int counter= 0;
        Scanner in = new Scanner(System.in);


        while (true) {

            //request user to input:
            line = in.nextLine();

            if (line.equals("bye")) {
                separatingLine();
                System.out.println("Bye. Glad to be of service to you!\n" +
                        "Hope to assist you again soon!");
                separatingLine();
                System.exit(0);
            }

            //Print the List:
            if (line.equals("list") && taskList[0] != null) {
                separatingLine();
                System.out.println("Hey, I have summarised your task list as below:");
                for (int i = 0; i < counter; i++) {
                    System.out.println(i + 1 + ". " +
                            taskList[i]);
                }
                separatingLine();
            } else if (line.equals("list") && taskList[0] == null) {
                separatingLine();
                System.out.println("Your current task list is empty!\n" +
                        "Please start to add your tasks below ^<>^");
                separatingLine();
            }

            //Add user input to task list:
            else {
                separatingLine();
                System.out.println("The below task has been added to your task list:\n " +
                        line);

                //assign to task list from input:
                taskList[counter] = line;

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
