import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
       /* String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);*/
        Print p ;
        String line;
        Scanner in = new Scanner(System.in);
        System.out.println("======================");
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        System.out.println("======================");
        while(in.hasNext()) {
            String reply=in.nextLine();
            p = new Print(reply);
            if (reply.equalsIgnoreCase("bye")) {
                break;
            }
        }
    }
}
}
