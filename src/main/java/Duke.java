import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
       /* String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);*/
        String line;
        Scanner in = new Scanner(System.in);
        System.out.println("======================");
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        System.out.println("======================");
        while(in.hasNext()) {
            String reply=in.nextLine();
            if (reply.equalsIgnoreCase("bye")) {
                System.out.println("\t================================");
                System.out.println("\tBye. Hope to see you again soon!");
                System.out.println("\t================================");
                break;
            } else {
                System.out.println("\t==================");
                System.out.println("\t"+reply);
                System.out.println("\t==================");

            }
        }
    }
}
